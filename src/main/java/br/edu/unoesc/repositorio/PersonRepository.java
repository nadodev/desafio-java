package br.edu.unoesc.repositorio;

import br.edu.unoesc.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByTeamId(Long teamId);
}
