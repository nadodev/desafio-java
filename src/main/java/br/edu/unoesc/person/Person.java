package br.edu.unoesc.person;

import br.edu.unoesc.education.Education;
import br.edu.unoesc.team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity(name = "Person")
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter

    private Long id;

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String cpf;
    @Setter
    @Getter
    private String birth_date;
    @Setter
    @Getter
    private String gender;
    @Setter
    @Getter
    private String phone;
    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Education> education;

}
