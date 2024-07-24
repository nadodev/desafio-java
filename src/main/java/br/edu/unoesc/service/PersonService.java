package br.edu.unoesc.service;

import br.edu.unoesc.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    // Methods for creating, updating, deleting, and finding People
}