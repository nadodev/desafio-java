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

    @Transactional
    public void save(Person person) {
        if (personRepository.existsByCpf(person.getCpf())) {
            throw new DuplicateResourceException("CPF já existe: " + person.getCpf());
        }
        if (personRepository.existsByEmail(person.getEmail())) {
            throw new DuplicateResourceException("E-mail já existe: " + person.getEmail());
        }
        personRepository.save(person);
    }

    public Person findById(Long id) {
        return (Person) personRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

}