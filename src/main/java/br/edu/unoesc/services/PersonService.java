package br.edu.unoesc.services;

import br.edu.unoesc.entities.Person;
import br.edu.unoesc.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public Person findById(Long id) {
        return (Person) personRepository.findById(id).orElse(null);
    }

}