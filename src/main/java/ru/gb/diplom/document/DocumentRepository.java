package ru.gb.diplom.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.DocumentType;

import java.util.List;
import java.util.Optional;
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findById(long id);

    List<Document> findAllByDocumentCategory(DocumentCategory documentCategory);

    List<Document> findAllByDocumentStatus(DocumentStatus documentStatus);

    List<Document> findAllByDocumentCategoryAndDocumentStatus(DocumentCategory documentCategory,DocumentStatus documentStatus);

}
