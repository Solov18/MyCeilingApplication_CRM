package ru.gb.diplom.document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DocumentType;
import ru.gb.diplom.client.Client;
import ru.gb.diplom.exception.ModelNotFound;
import ru.gb.diplom.user.User;
import ru.gb.diplom.util.CSVUtils;
import ru.gb.diplom.util.EntityUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DocumentService {



    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);


    @Autowired
    DocumentRepository repository;



    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }


    Long createDocument(CreateDocumentDto createDocumentDto){
        createDocumentDto.setCreated(Timestamp.from(Instant.now()));
        Document document = repository.save(DocumentMapper.toEntity(createDocumentDto));
        return document.getId();
    }


    DocumentDto getDocument(long id){
        return repository.findById(id)
                .map(DocumentMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("Document", id));
    }


    List<DocumentDto> getDocuments(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }


    List<DocumentDto> getDocumentCategory(DocumentCategory documentCategory){
        return StreamSupport.stream(repository.findAllByDocumentCategory(documentCategory).spliterator(), false)
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }


    List<DocumentDto> getDocumentsStatus(DocumentStatus documentStatus) {
        return StreamSupport.stream(repository.findAllByDocumentStatus(documentStatus).spliterator(), false)
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }

    void deleteDocument(Long id){
        Document document = repository.findById(id)
                .orElseThrow(() -> new ModelNotFound("Document", id));
        repository.delete(document);
    }



    void update(DocumentDto documentDto){
        Document document = repository.findById(documentDto.getId())
                .orElseThrow(() -> new ModelNotFound("Document", documentDto.getId()));
        EntityUtils.setter(documentDto.getTitle(), t -> document.setTitle(t));
        EntityUtils.setter(documentDto.getDocumentCategory(), t -> document.setDocumentCategory(t));
        EntityUtils.setter(documentDto.getDocumentStatus(), t -> document.setDocumentStatus(t));
        EntityUtils.setter(documentDto.getDescription(), t -> document.setDescription(t));
        EntityUtils.setter(documentDto.getClient(), t -> document.setClient(t));
        EntityUtils.setter(documentDto.getValue(), t -> document.setValue(t));
        EntityUtils.setter(documentDto.getAuthor(), t -> document.setAuthor(t));
        EntityUtils.setter(documentDto.getAcceptedBy(), t -> document.setAcceptedBy(t));
        EntityUtils.setter(documentDto.getAcceptedItern(), t -> document.setAcceptedItern(t));
        EntityUtils.setter(documentDto.getAcceptedByClient(), t -> document.setAcceptedByClient(t));
        repository.save(document);
    }


    public List<DocumentDto> getDocumentsSearch(DocumentCategory documentCategory, DocumentStatus documentStatus) {
        List<Document> list;
        if(documentCategory != null && documentStatus != null) {
            list = repository.findAllByDocumentCategoryAndDocumentStatus(documentCategory, documentStatus);
        } else if(documentCategory != null){
            list = repository.findAllByDocumentCategory(documentCategory);
        } else {
            list = repository.findAllByDocumentStatus(documentStatus);
        }
        return StreamSupport.stream(list.spliterator(), false)
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }


    List<DocumentDto> getDocumentsSearch(DocumentCategory documentCategory, DocumentStatus documentStatus, Boolean acceptedItern,
                                         Boolean acceptedByClient, Client client, User author, User acceptedBy) {
        List<DocumentDto> dtos = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
        return filter(dtos, documentCategory, documentStatus, acceptedItern,
                acceptedByClient, client, author, acceptedBy);
    }

    private static List<DocumentDto> filter(List<DocumentDto> dtos, DocumentCategory type,
                                            DocumentStatus status, Boolean acceptedItern, Boolean acceptedByClient,
                                            Client client, User author, User acceptedBy){
        return dtos.stream()
                .filter(addFilter(type, documentDto -> type.equals(documentDto.getDocumentCategory())))
                .filter(addFilter(status, documentDto -> status.equals(documentDto.getDocumentStatus())))
                .filter(addFilter(acceptedItern, documentDto -> acceptedItern.equals(documentDto.getAcceptedItern())))
                .filter(addFilter(acceptedByClient, documentDto -> acceptedByClient.equals(documentDto.getAcceptedByClient())))
                .filter(addFilter(client, documentDto -> client.equals(documentDto.getClient())))
                .filter(addFilter(author, documentDto -> author.equals(documentDto.getAuthor())))
                .filter(addFilter(acceptedBy, documentDto -> acceptedBy.equals(documentDto.getAcceptedBy())))
                .collect(Collectors.toList());
    }

    private static Predicate<DocumentDto> addFilter(Object value, Predicate<DocumentDto> predicate) {
        if(value != null){
            return predicate;
        }
        return documentDto -> true;
    }



    void saveToDB(List<Document> documents) {
        repository.saveAll(documents);
    }


    void importDataFromCSV(String filename) {
        List<Document> documents = CSVUtils.buildListFromCSV(filename, Document.class);
        saveToDB(documents);
    }


    void exportDataToCSV(String filename) {
        List<Document> documents = buildListFromDB();
        CSVUtils.exportListToCSV(filename, documents);
    }

    List<Document> buildListFromDB() {
        return repository.findAll();
    }





}
