package ru.gb.diplom.document;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.diplom.client.Client;
import ru.gb.diplom.exception.ModelNotFound;
import ru.gb.diplom.user.User;
import ru.gb.diplom.util.CSVUtils;

@RequestMapping("/documents")
@RestController
public class DocumentResource {

    @Autowired
    DocumentRepository repository;


    private static final Logger log = LoggerFactory.getLogger(DocumentResource.class);


    private final DocumentService service;

    public DocumentResource(DocumentService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    ResponseEntity getDocument(@PathVariable long id){
        DocumentDto documentDto = service.getDocument(id);
        return ResponseEntity.ok(documentDto);
    }


    @GetMapping("/type/{documentCategory}")
    ResponseEntity getDocuments(@PathVariable DocumentCategory documentCategory) {
        return ResponseEntity.ok(service.getDocumentCategory(documentCategory));
    }


    @GetMapping("/status/{documentStatus}")
    ResponseEntity getDocuments(@PathVariable DocumentStatus documentStatus) {
        return ResponseEntity.ok(service.getDocumentsStatus(documentStatus));
    }



    @GetMapping("/search")
    ResponseEntity getAdditionalSearchRequest(@RequestParam(name="documentCategory", required = false) DocumentCategory documentCategory,
                                              @RequestParam(name="documentStatus", required = false) DocumentStatus documentStatus,
                                              @RequestParam(name="acceptedItern", required = false) Boolean acceptedItern,
                                              @RequestParam(name="acceptedByClient", required = false) Boolean acceptedByClient,
                                              @RequestParam(name="client", required = false) Client client,
                                              @RequestParam(name="author", required = false) User author,
                                              @RequestParam(name="acceptedBy", required = false) User acceptedBy) {
        return ResponseEntity.ok(service.getDocumentsSearch(documentCategory, documentStatus,
                acceptedItern, acceptedByClient, client, author, acceptedBy));
    }





    @GetMapping
    ResponseEntity getDocuments(){
        return ResponseEntity.ok(service.getDocuments());
    }


    @PostMapping
    ResponseEntity createDocument(@RequestBody @Valid CreateDocumentDto createDocumentDto){
        long id = service.createDocument(createDocumentDto);
        log.info("Document created with id: " + id);
        return ResponseEntity.ok(id);
    }


    @DeleteMapping("/{id}")
    ResponseEntity deleteDocument(@PathVariable long id){
        service.deleteDocument(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    ResponseEntity updateDocument(@Valid @RequestBody DocumentDto documentDto){
        service.update(documentDto);
        return ResponseEntity.accepted().build();
    }


    @GetMapping("/import")
    ResponseEntity getImportListFromCSV(@RequestParam(name = "filename", required = true) String filename) {
        return ResponseEntity.ok(CSVUtils.buildListFromCSV(filename, Document.class));
    }

    @PostMapping("/import")
    ResponseEntity addEntitiesFromCSV(@RequestParam(name = "filename", required = true) String filename) {
        new DocumentService(repository).importDataFromCSV(filename);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/export")
    ResponseEntity exportDBtoFile(@RequestParam(name = "filename", required = true) String filename) {
        new DocumentService(repository).exportDataToCSV(filename);
        return ResponseEntity.accepted().build();
    }


    @ExceptionHandler(ModelNotFound.class)
    ResponseEntity handleException(ModelNotFound e){
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }





}
