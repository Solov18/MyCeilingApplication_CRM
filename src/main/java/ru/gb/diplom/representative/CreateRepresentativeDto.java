package ru.gb.diplom.representative;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ru.gb.diplom.client.Client;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRepresentativeDto {

    private String firstname;

    @NotBlank
    private String lastname;

    private String position;

    @Pattern(regexp="\\d{3}-\\d{3}-\\d{3}|\\d{2}-\\d{3}-\\d{2}-\\d{2}") // tel. pattern XXX-XXX-XXX or XX-XXX-XX-XX
    private String phone;

    @Email
    private String email;

    private boolean active;

    private Client client;


}
