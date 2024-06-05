package ru.gb.diplom.client;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.diplom.address.AddressRepository;
import ru.gb.diplom.exception.ModelNotFound;
import ru.gb.diplom.representative.Representative;
import ru.gb.diplom.util.CSVUtils;
import ru.gb.diplom.util.EntityUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {


    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    ClientRepository repository;

    @Autowired
    AddressRepository ar;

    Long createClient(CreateClientDto dto) {
        return save(ClientMapper.toEntity(dto)).getId();
    }

    Client save(Client client) {
        client.setActive(true);
        if (client.getAddress() != null) {
            ar.save(client.getAddress());
        }
        return repository.save(client);
    }


    ClientDto getClient(long id) {
        return jsonNullReference(repository.findById(id).map(ClientMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("Client", id)));
    }


    ClientDto findByRepresentatives(Representative representative) {
        return jsonNullReference(repository.findByRepresentatives(representative).map(ClientMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("Client", "")));
    }

    ClientDto findByName(String name) {
        return jsonNullReference(repository.findByName(name).map(ClientMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("Client", "")));
    }

    List<ClientDto> getClients(){
        return jsonListNullReference(repository.findAll().stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList()));
    }


    void deleteClient(long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ModelNotFound("Client", id));
        repository.delete(client);
    }


    //найти клиента по идентификатору, изменить его состояние и затем сохранить изменения.
    void remove(long id) {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setActive(false);
            repository.save(client);
        } else {
            // Обработка ситуации, когда клиент не найден
            throw new EntityNotFoundException("Client with id " + id + " not found");
        }
    }



    public void update(ClientDto clientDto) {
        Client client = repository.findById(clientDto.getId())
                .orElseThrow(() -> new ModelNotFound("Client", clientDto.getId()));
        EntityUtils.setter(clientDto.getName(), t -> client.setName(t));
        EntityUtils.setter(clientDto.getAbbr(), t -> client.setAbbr(t));
        EntityUtils.setter(clientDto.getAddress(), t -> client.setAddress(t));
        EntityUtils.setter(clientDto.getActive(), t -> client.setActive(t));
        EntityUtils.setter(clientDto.getIndustry(), t -> client.setIndustry(t));
        EntityUtils.setter(clientDto.getRepresentatives(), t -> client.setRepresentatives(t));
        EntityUtils.setter(clientDto.getResponsible(), t -> client.setResponsible(t));
        repository.save(client);

    }

    public ClientDto findOne(Long id) {
        return jsonNullReference(ClientMapper
                .toDto(repository.findById(id)
                        .orElseThrow(() -> new ModelNotFound("Client", id))));
    }

    ClientDto jsonNullReference(ClientDto client) {
        if (client != null) {
            for (Representative r : client.getRepresentatives()) {
                r.setClient(null);
            }
            if (client.getAddress() != null)
                client.getAddress().setClient(null);
        }
        return client;
    }


    List<ClientDto> jsonListNullReference(List<ClientDto> list) {
        for (ClientDto dto : list) {
            dto = jsonNullReference(dto);
        }
        return list;
    }


    List<ClientDto> findAll() {
        return jsonListNullReference(repository.findAll()
                .stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList()));
    }

    public void saveToDB(List<Client> clients) {
        repository.saveAll(clients);
    }


    public void importDataFromCSV(String filename) {
        List<Client> clients = CSVUtils.buildListFromCSV(filename, Client.class);
        saveToDB(clients);
    }


    public void exportDataToCSV(String filename) {
        List<Client> clients = buildListFromDB();
        for (Client client: clients) {
            client.setAddress(null);
            client.setRepresentatives(null);
            client.setResponsible(null);
        }

        CSVUtils.exportListToCSV(filename, clients);
    }

    public List<Client> buildListFromDB() {
        return repository.findAll();
    }


    public List<ClientDto> findByIndustry(Industry industry) {
        return jsonListNullReference(repository.findByIndustry(industry)
                .stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList()));
    }



    public List<ClientDto> findByResponsibleId(Long id) {
        return jsonListNullReference(repository.findByResponsibleId(id)
                .stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList()));
    }



    public List<ClientDto> findByResponsibleLastname(String lastname) {
        return jsonListNullReference(repository.findByResponsibleLastname(lastname)
                .stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList()));
    }



    public List<ClientDto> findByActiveTrue() {
        return jsonListNullReference(repository.findByActiveTrue()
                .stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList()));
    }



    public List<ClientDto> findByActiveFalse() {
        return jsonListNullReference(repository.findByActiveFalse()
                .stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList()));
    }



    public List<ClientDto> findByAddressCityIgnoreCase(String city) {
        return jsonListNullReference(repository.findByAddressCityIgnoreCase(city)
                .stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList()));
    }


}
