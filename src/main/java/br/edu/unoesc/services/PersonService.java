package br.edu.unoesc.services;

import br.edu.unoesc.entities.Person;
import br.edu.unoesc.exceptions.DuplicateResourceException;
import br.edu.unoesc.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return (Person) personRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    @Transactional
    public void save(Person person) {
        // Verifica se o CPF está em uso por outra pessoa
        Person personWithCpf = personRepository.findByCpf(person.getCpf());
        if (personWithCpf != null && !personWithCpf.getId().equals(person.getId())) {
            throw new DuplicateResourceException("CPF já existe: " + person.getCpf());
        }

        // Verifica se o e-mail está em uso por outra pessoa
        Person personWithEmail = personRepository.findByEmail(person.getEmail());
        if (personWithEmail != null && !personWithEmail.getId().equals(person.getId())) {
            throw new DuplicateResourceException("E-mail já existe: " + person.getEmail());
        }

        // Salva a pessoa e suas educações
        personRepository.save(person);
    }

    public boolean isCpfInUseByAnotherPerson(String cpf, Long id) {
        Person personWithCpf = personRepository.findByCpf(cpf);
        return personWithCpf != null && !personWithCpf.getId().equals(id);
    }

    public boolean isEmailInUseByAnotherPerson(String email, Long id) {
        Person personWithEmail = personRepository.findByEmail(email);
        return personWithEmail != null && !personWithEmail.getId().equals(id);
    }

}