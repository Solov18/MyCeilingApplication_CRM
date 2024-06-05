package ru.gb.diplom.document;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import ru.gb.diplom.client.Client;
import ru.gb.diplom.user.User;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="documents")
public class Document {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @NotBlank
    @Column(nullable = false, unique = true )
    private String title;


    @NotNull
    @Column(nullable = false)
    private Timestamp created;

    @Enumerated(EnumType.STRING)
    private DocumentCategory documentCategory;

    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;

    @ManyToOne
    private Client client;

    private String description;

    private String filename;

    @Column(precision=10, scale=2)
    private Double value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    private User acceptedBy;

    private Boolean acceptedItern;

    private Boolean acceptedByClient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public DocumentCategory getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentType(DocumentCategory documentCategory) {
        this.documentCategory = documentCategory;
    }

    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(User acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public Boolean getAcceptedItern() {
        return acceptedItern;
    }

    public void setAcceptedItern(Boolean acceptedItern) {
        this.acceptedItern = acceptedItern;
    }

    public Boolean getAcceptedByClient() {
        return acceptedByClient;
    }

    public void setAcceptedByClient(Boolean acceptedByClient) {
        this.acceptedByClient = acceptedByClient;
    }
}
