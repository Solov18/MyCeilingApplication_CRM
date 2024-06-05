package ru.gb.diplom.workflow;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import ru.gb.diplom.client.Client;
import ru.gb.diplom.document.Document;
import ru.gb.diplom.representative.Representative;
import ru.gb.diplom.user.User;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="workflows")
public class Workflow {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;

    private String description;

    private Timestamp created;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="representative_id")
    private Representative representative;

    @OneToMany
    private List<Document> documents;






}
