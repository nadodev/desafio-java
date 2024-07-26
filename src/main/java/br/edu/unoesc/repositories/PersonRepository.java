package br.edu.unoesc.repositories;

import br.edu.unoesc.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByTeamId(Long teamId);

    @Query("SELECT e FROM Education e WHERE e.person.id = :personId")
    List<Person> findByPersonId(Long personId);

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}
