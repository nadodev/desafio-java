package br.edu.unoesc.person;

import br.edu.unoesc.education.Education;
import br.edu.unoesc.team.Team;
import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "Person")
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private String birthDate;
    private String gender;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Education> education;

}
