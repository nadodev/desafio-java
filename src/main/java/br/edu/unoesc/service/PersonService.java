package br.edu.unoesc.service;

import br.edu.unoesc.models.Person;
import br.edu.unoesc.repositorio.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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


    //get by id
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

}