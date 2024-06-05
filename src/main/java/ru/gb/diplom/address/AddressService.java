package ru.gb.diplom.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.diplom.client.Client;
import ru.gb.diplom.exception.ModelNotFound;
import ru.gb.diplom.util.CSVUtils;
import ru.gb.diplom.util.EntityUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AddressService {

    private static final Logger log = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    AddressRepository repository;

    Long createAddress(CreateAddressDto dto) {
        Address address = repository.save(AddressMapper.toEntity(dto));
        return address.getId();
    }

    AddressDto getAddress(long id) {
        return jsonNullReference(repository.findById(id).map(AddressMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("Address", id)));
    }

    List<AddressDto> getAddresses() {
        return jsonListNullReference(StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(AddressMapper::toDto)
                .collect(Collectors.toList()));
    }

    void deleteAddress(long id) {
        Address address = repository.findById(id).orElseThrow(() -> new ModelNotFound("Address", id));
        repository.delete(address);
    }

    void update(AddressDto addressDto) {
        Address address = repository.findById(addressDto.getId())
                .orElseThrow(() -> new ModelNotFound("Address", addressDto.getId()));
        EntityUtils.setter(addressDto.getStreet(), t -> address.setStreet(t));
        EntityUtils.setter(addressDto.getStreetNumber(), t -> address.setStreetNumber(t));
        EntityUtils.setter(addressDto.getAdditionalNumber(), t -> address.setAdditionalNumber(t));
        EntityUtils.setter(addressDto.getPostalCode(), t -> address.setPostalCode(t));
        EntityUtils.setter(addressDto.getClient(), t -> address.setClient(t));
        EntityUtils.setter(addressDto.getCity(), t -> address.setCity(t));
        EntityUtils.setter(addressDto.getCountry(), t -> address.setCountry(t));
        repository.save(address);
    }

    List<AddressDto> getAddressesSearch(String street, String streetNumber, String additionalNumber, String postalCode,
                                        String city, String country, Client client) {
        List<AddressDto> dtos = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(AddressMapper::toDto).collect(Collectors.toList());
        return jsonListNullReference(filter(dtos, street, streetNumber, additionalNumber, postalCode, city, country, client));
    }

    private static List<AddressDto> filter(List<AddressDto> dtos, String street, String streetNumber,
                                           String additionalNumber, String postalCode, String city, String country, Client client) {
        return dtos.stream()
                .filter(addFilter(street,
                        addressDto -> addressDto.getStreet().toLowerCase().contains(street.toLowerCase())))
                .filter(addFilter(streetNumber,
                        addressDto -> addressDto.getStreetNumber().contains(streetNumber.toLowerCase())))
                .filter(addFilter(additionalNumber,
                        addressDto -> addressDto.getAdditionalNumber().contains(additionalNumber.toLowerCase())))
                .filter(addFilter(postalCode,
                        addressDto -> addressDto.getPostalCode().contains(postalCode)))
                .filter(addFilter(city,
                        addressDto -> addressDto.getCity().toLowerCase().contains(city.toLowerCase())))
                .filter(addFilter(country,
                        addressDto -> addressDto.getCountry().toLowerCase().contains(country.toLowerCase())))
                .filter(addFilter(client,
                        addressDto -> client.equals(addressDto.getClient())))
                .collect(Collectors.toList());
    }

    private static Predicate<AddressDto> addFilter(Object value, Predicate<AddressDto> predicate) {
        if (value != null) {
            return predicate;
        }
        return AddressDto -> true;
    }

    AddressDto jsonNullReference(AddressDto addressDto) {
        if (addressDto != null) {
            if (addressDto.getClient() != null) {
                addressDto.getClient().setAddress(null);
                addressDto.getClient().setRepresentatives(null);;
                addressDto.getClient().setResponsible(null);
            }
        }
        return addressDto;
    }

    List<AddressDto> jsonListNullReference(List<AddressDto> addresses){
        for (AddressDto addressDto: addresses) {
            addressDto = jsonNullReference(addressDto);
        }
        return addresses;
    }


    void exportDataToCSV(String filename) {
        List<Address> addresses = repository.findAll();
        for (Address address: addresses) {
            address.setClient(null);
        }
        CSVUtils.exportListToCSV(filename, addresses);
    }



}