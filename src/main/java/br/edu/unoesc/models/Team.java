package br.edu.unoesc.models;

import br.edu.unoesc.models.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity(name = "Team")
@Table(name = "team")
public class Team {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String department;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Person> person;



    // Getters and Setters

}
