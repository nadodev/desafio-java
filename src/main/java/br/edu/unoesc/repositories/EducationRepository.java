package br.edu.unoesc.repositories;


import br.edu.unoesc.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {

}
