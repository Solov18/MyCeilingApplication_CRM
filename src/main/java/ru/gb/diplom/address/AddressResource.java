package ru.gb.diplom.address;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/addresses")
public class AddressResource {
//    @Autowired
//    AddressService service;

    private static final Logger log = LoggerFactory.getLogger(AddressResource.class);

//    @GetMapping("/{id}")
//    ResponseEntity getAddress(@PathVariable("id") Long id) { return ResponseEntity.ok(service.getAddress(id)); }

//    @GetMapping
//    ResponseEntity getAddresses() {return ResponseEntity.ok(service.getAddresses());}

    /*
     * Обрабатывает GET-запрос для поиска адресов на основе различных параметров.
     */

     //    @GetMapping("/search")
//    ResponseEntity getSearchRequestAddress (@RequestParam(name = "street", required=false) String street,
//        @RequestParam(name ="streetNumber", required=false) String streetNumber,
//        @RequestParam(name = "additionalNumber", required=false) String additionalNumber,
//        @RequestParam(name = "postalCode", required=false) String postalCode,
//        @RequestParam(name = "city", required=false) String city,
//        @RequestParam(name = "country", required=false) String country,
//        @RequestParam(name = "client", required=false) Client client) {
//        return ResponseEntity.ok(service.getAddressesSearch(street,
//                streetNumber, additionalNumber, postalCode, city, country, client));
//    }
    /*
     * Обрабатывает POST-запрос для создания нового адреса на основе данных, переданных в теле запроса.
     */
//@PostMapping
//ResponseEntity createAddress(@org.springframework.web.bind.annotation.RequestBody @Valid CreateAddressDto dto) {
//    long id = service.createAddress(dto);
//    log.info("Address with id: "+id+" created");
//    return ResponseEntity.ok(id);
//}
    /*
     * Обрабатывает PUT-запрос для обновления адреса на основе данных, переданных в теле запроса.
     */
//    @PutMapping
//    ResponseEntity updateAddress(@RequestBody @Valid AddressDto dto) {
//        service.update(dto);
//        log.info("Address with id: "+ dto.getId() +" updated");
//        return ResponseEntity.accepted().build();
//    }

    /*
     * Обрабатывает DELETE-запрос для удаления адреса по заданному идентификатору.
     */
//    @DeleteMapping("/{id}")
//    ResponseEntity deleteAddress(@PathVariable("id") long id) {
//        service.deleteAddress(id);
//        log.info("Address with id: "+id+" deleted");
//        return ResponseEntity.accepted().build();
//    }

    /*
     * Обрабатывает GET-запрос для экспорта данных в CSV-файл.
     */
//    @GetMapping("/export")
//    ResponseEntity exportTableToDB(@RequestParam(name = "filename", required = true) String filename) {
//        service.exportDataToCSV(filename);
//        return ResponseEntity.accepted().build();
//    }
//
//
//
//
//
}