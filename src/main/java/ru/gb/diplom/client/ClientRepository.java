package ru.gb.diplom.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.diplom.representative.Representative;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByRepresentatives(Representative representative);

    Optional<Client> findByName(String name);

    Optional<Client> findById(long id);

    List<Client> findByIndustry(Industry industry);

    List<Client> findByResponsibleId(Long id);

    List<Client> findByResponsibleLastname(String lastname);

    List<Client> findByAddressCityIgnoreCase(String city);

    List<Client> findByActiveTrue();

    List<Client> findByActiveFalse();

}
