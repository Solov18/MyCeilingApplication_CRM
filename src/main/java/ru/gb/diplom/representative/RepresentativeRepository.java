package ru.gb.diplom.representative;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.diplom.client.Client;

import java.util.List;
import java.util.Optional;
@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long> {

    Optional<Representative> findByClient(Client client);

    Optional<Representative> findById(long id);

    List<Representative> findAllByActiveTrue();

    Page<Representative> findAllByActiveTrue(Pageable pageable);

}
