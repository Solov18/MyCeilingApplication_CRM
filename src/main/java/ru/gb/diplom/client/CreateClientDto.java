package ru.gb.diplom.client;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import ru.gb.diplom.address.Address;
import ru.gb.diplom.representative.Representative;
import ru.gb.diplom.user.User;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientDto {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    private String abbr;

    private Boolean active;

    private User responsible;

    private Collection<Representative> representatives;

    private Address address;

    private Industry industry;

}
