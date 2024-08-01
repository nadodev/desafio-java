package br.edu.unoesc.repositories;


import br.edu.unoesc.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {

    List<Education> findByPersonId(Long personId);
}
