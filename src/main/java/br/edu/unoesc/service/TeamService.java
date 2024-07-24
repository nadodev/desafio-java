package br.edu.unoesc.service;

import br.edu.unoesc.team.Team;
import br.edu.unoesc.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;


    // Methods for creating, updating, deleting, and finding Teams

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public void save(Team team) {
        teamRepository.save(team);
    }


    // Additional methods for updating and deleting teams

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    public void update(Team team) {
        teamRepository.save(team);
    }


}