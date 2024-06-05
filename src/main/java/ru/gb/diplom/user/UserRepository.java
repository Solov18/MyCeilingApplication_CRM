package ru.gb.diplom.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.diplom.position.Position;
import ru.gb.diplom.role.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    User findByUsername(String username);

    List<User> findByPosition(Position position);

    List<User> findByRolesIn(Role role);



}
