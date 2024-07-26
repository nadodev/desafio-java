package br.edu.unoesc.entities;

import br.edu.unoesc.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "Education")
@Table(name = "education")
public class Education {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String courseName;

    @Getter
    @Setter
    private String course_level;

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
