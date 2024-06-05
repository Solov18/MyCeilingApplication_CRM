package ru.gb.diplom.representative;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gb.diplom.client.Client;
import ru.gb.diplom.exception.ModelNotFound;
import ru.gb.diplom.util.CSVUtils;
import ru.gb.diplom.util.EntityUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepresentativeService {


    private static final Logger log = LoggerFactory.getLogger(RepresentativeService.class);

    @Autowired
    RepresentativeRepository repository;


    Long createRepresentative(CreateRepresentativeDto dto) {
        return save(RepresentativeMapper.toEntity(dto)).getId();
    }

    Representative save(Representative r) {
        r.setActive(true);
        return repository.save(r);
    }



    RepresentativeDto findByClient(Client client) {
        return jsonNullReference(repository.findByClient(client)
                .map(RepresentativeMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("Client", client.getId())));
    }

    public RepresentativeDto findOne(long id) {
        return jsonNullReference(repository.findById(id)
                .map(RepresentativeMapper::toDto)
                .orElseThrow(()-> new ModelNotFound("Representative", id)));

    }


    /**
     * Деактивирует представителя, устанавливая флаг active в false, и сохраняет изменения.
     *
     * @param id идентификатор представителя
     * @throws EntityNotFoundException если представитель с заданным идентификатором не найден
     */
    public void remove(long id) {
        // Поиск представителя по идентификатору
        Optional<Representative> optionalRep = repository.findById(id);

        if (optionalRep.isPresent()) {
            // Если представитель найден, устанавливаем флаг active в false
            Representative rep = optionalRep.get();
            rep.setActive(false);

            // Сохраняем изменения в базе данных
            repository.save(rep);
        } else {
            // Обработка ситуации, когда представитель не найден
            throw new EntityNotFoundException("Representative with id " + id + " not found");
        }
    }



    public void update(RepresentativeDto dto) {
        Representative r = repository.findById(dto.getId())
                .orElseThrow(()-> new ModelNotFound("Representative", dto.getId()));
        EntityUtils.setter(dto.getFirstname(), t->r.setFirstname(t));
        EntityUtils.setter(dto.getLastname(), t->r.setLastname(t));
        EntityUtils.setter(dto.getEmail(), t->r.setEmail(t));
        EntityUtils.setter(dto.getPhone(), t->r.setPhone(t));
        EntityUtils.setter(dto.getPosition(), t->r.setPosition(t));
        EntityUtils.setter(dto.getClient(), t->r.setClient(t));
        repository.save(r);
    }


    public List<RepresentativeDto> findAllByActiveTrue() {
        return jsonListNullReference(repository.findAllByActiveTrue()
                .stream()
                .map(RepresentativeMapper::toDto)
                .collect(Collectors.toList()));
    }



    public Page<Representative> findAll(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable);
    }






    RepresentativeDto jsonNullReference(RepresentativeDto representative) {
        if (representative != null) {
            if (representative.getClient() != null) {
                representative.getClient().setRepresentatives(null);
                representative.getClient().setResponsible(null);
                if (representative.getClient().getAddress() != null) {
                    representative.getClient().getAddress().setClient(null);
                }
            }
        }
        return representative;
    }

    List<RepresentativeDto> jsonListNullReference(List<RepresentativeDto> list){
        for (RepresentativeDto dto : list) {
            dto = jsonNullReference(dto);
        }
        return list;
    }

    public void saveToDB(List<Representative> list) {
        repository.saveAll(list);
    }


    public void importDataFromCSV(String filename) {
        List<Representative> representatives = CSVUtils.buildListFromCSV(filename, Representative.class);
        saveToDB(representatives);
    }


    public void exportDataToCSV(String filename) {
        List<Representative> representatives = buildListFromDB();
        CSVUtils.exportListToCSV(filename, representatives);
    }

    public List<Representative> buildListFromDB() {
        return repository.findAll();
    }



    public Collection<Representative> findAll() {
        return repository.findAll();
    }


}