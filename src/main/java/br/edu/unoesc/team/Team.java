package br.edu.unoesc.team;

import br.edu.unoesc.person.Person;
import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "Team")
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Person> person;

    // Getters and Setters
}
