package ru.gb.diplom.document;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.diplom.client.Client;
import ru.gb.diplom.user.User;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {

    @NotNull
    private long id;

    private String title;

    private Timestamp created;

    @Enumerated(EnumType.STRING)
    private DocumentCategory documentCategory;

    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;

    private Client client;

    private String description;

    private Double value;

    private User author;

    private User acceptedBy;

    private Boolean acceptedItern;

    private Boolean acceptedByClient;





}
