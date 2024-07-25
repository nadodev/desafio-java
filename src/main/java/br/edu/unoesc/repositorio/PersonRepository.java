package br.edu.unoesc.repositorio;

import br.edu.unoesc.models.Education;
import br.edu.unoesc.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByTeamId(Long teamId);

    @Query("SELECT e FROM Education e WHERE e.person.id = :personId")
    List<Person> findByPersonId(Long personId);

}
