package br.edu.unoesc.service;

import br.edu.unoesc.person.Person;
import br.edu.unoesc.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    //get all
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    //save
    public void save(Person person) {
        personRepository.save(person);
    }

    // Methods for creating, updating, deleting, and finding People
}