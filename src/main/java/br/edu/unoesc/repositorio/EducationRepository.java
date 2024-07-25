package br.edu.unoesc.repositorio;


import br.edu.unoesc.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
