package ru.gb.diplom.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.diplom.exception.ModelNotFound;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository repository;



    Long createTeam(Team team) {
        repository.save(team);
        return team.getId();
    }


    Team getTeam(Long id) {
        if (id != null) {
            return repository.findById(id).orElse(null);
        }
        return null;
    }

    List<Team> getTeams() {
        return repository.findAll();
    }


    void updateTeam(Team team) {
        repository.save(team);
    }

    void deleteTeam(Long id) {
        if (id != null) {
            Team team = repository.findById(id).orElse(null);
            if (team != null) {
                repository.delete(team);
            }
        }
    }

    List<Team> getTeams(String name) {
        return repository.findAllByNameIgnoreCaseContaining(name);
    }

    Team getTeamByLeaderId(Long id) {
        return repository.findByLeaderId(id)
                .orElseThrow(() -> new ModelNotFound("Leader", id));
    }



}
