package ru.gb.diplom.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.diplom.client.Client;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String streetNumber;
    private String additionalNumber;

    @Pattern(regexp = "\\d{2}-\\d{3}")
    private String postalCode;

    @NotBlank
    @Column(nullable = false)
    private String city;

    @Column
    private String country;
    @OneToOne(mappedBy = "address",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Client client;



}
