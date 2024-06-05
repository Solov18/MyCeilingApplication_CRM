package ru.gb.diplom.team;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/teams")
@RestController
public class TeamResource {

    @Autowired
    TeamService service;





    @GetMapping("/{id}")
    ResponseEntity getTeam(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTeam(id));
    }

    @GetMapping
    ResponseEntity getTeams() {
        return ResponseEntity.ok(service.getTeams());
    }


    @GetMapping("/name/{name}")
    ResponseEntity getTeams(@PathVariable("name") String name) {
        return ResponseEntity.ok(service.getTeams(name));
    }

    @GetMapping("/leader/{id}")
    ResponseEntity getTeamByLeader(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getTeamByLeaderId(id));
    }


    @PostMapping
    ResponseEntity createTeam(@RequestBody @Valid Team team) {
        Long id = service.createTeam(team);
        return ResponseEntity.ok(id);
    }

    @PutMapping
    ResponseEntity updateTeam(@RequestBody @Valid Team team) {
        service.updateTeam(team);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteTeam(@PathVariable Long id) {
        service.deleteTeam(id);
        return ResponseEntity.accepted().build();
    }








}
