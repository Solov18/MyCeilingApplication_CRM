package ru.gb.diplom.client;

import org.springframework.beans.BeanUtils;

final class ClientMapper {

    private ClientMapper() {}

    static ClientDto toDto(Client client) {
        ClientDto dto = new ClientDto();
        BeanUtils.copyProperties(client, dto);
        return dto;
    }

    static Client toEntity(CreateClientDto dto) {
        Client client = new Client();
        BeanUtils.copyProperties(dto, client);
        return client;
    }


}
