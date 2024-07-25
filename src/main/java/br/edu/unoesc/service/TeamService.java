package br.edu.unoesc.service;

import br.edu.unoesc.person.Person;
import br.edu.unoesc.person.PersonRepository;
import br.edu.unoesc.team.Team;
import br.edu.unoesc.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public void save(Team team) {
        teamRepository.save(team);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    public void update(Team team) {
        teamRepository.save(team);
    }
    public List<Person> findMembersByTeamId(Long teamId) {
        return personRepository.findByTeamId(teamId);
    }

}