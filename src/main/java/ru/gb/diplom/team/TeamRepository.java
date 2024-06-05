package ru.gb.diplom.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {


    Optional<Team> findByLeaderId(Long id);

    List<Team> findAllByNameIgnoreCaseContaining(String name);

}
