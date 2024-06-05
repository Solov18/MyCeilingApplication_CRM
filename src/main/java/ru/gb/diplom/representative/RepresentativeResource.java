package ru.gb.diplom.representative;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.diplom.util.CSVUtils;

@RequestMapping("/representatives")
@RestController
public class RepresentativeResource {


    @Autowired
    RepresentativeService service;


    @GetMapping("/{id}")
    ResponseEntity getRepresentative(@PathVariable Long id){
        RepresentativeDto  dto = service.findOne(id);
        return ResponseEntity.ok(dto);
    }


    @GetMapping
    ResponseEntity getRepresentatives(){
        return ResponseEntity.ok(service.findAllByActiveTrue());
    }


    @PostMapping
    ResponseEntity createRepresentative(@RequestBody @Valid Representative representative){
        service.save(representative);
        return ResponseEntity.ok(representative.getId());
    }


    @DeleteMapping("/{id}")
    ResponseEntity deleteRepresentative(@PathVariable Long id){
        service.remove(id);
        return ResponseEntity.accepted().build();
    }


    @PutMapping
    ResponseEntity updateRepresentative(@RequestBody @Valid Representative representative){
        service.save(representative);
        return ResponseEntity.accepted().build();
    }


    @GetMapping("/import")
    ResponseEntity getImportListFromCSV(@RequestParam(name = "filename", required = true) String filename) {
        return ResponseEntity.ok(CSVUtils.buildListFromCSV(filename, Representative.class));
    }

    @PostMapping("/import")
    ResponseEntity addEntitiesFromCSV(@RequestParam(name = "filename", required = true) String filename) {
        service.importDataFromCSV(filename);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/export")
    ResponseEntity exportDBtoFile(@RequestParam(name = "filename", required = true) String filename) {
        service.exportDataToCSV(filename);
        return ResponseEntity.accepted().build();
    }


}
