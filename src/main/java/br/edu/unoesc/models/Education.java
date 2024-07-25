package br.edu.unoesc.models;

import br.edu.unoesc.models.Person;
import jakarta.persistence.*;


@Entity(name = "Education")
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String courseLevel;
    private String completionDate;
    private String institution;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
