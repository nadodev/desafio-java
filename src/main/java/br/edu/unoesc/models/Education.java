package br.edu.unoesc.models;

import br.edu.unoesc.models.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "Education")
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String courseName;

    @Getter
    @Setter
    private String courseLevel;

    @Getter
    @Setter
    private String completionDate;

    @Getter
    @Setter
    private String institution;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "person_id")
    private Person person;

}
