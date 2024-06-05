package ru.gb.diplom.address;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

final class AddressMapper {

    // Приватный конструктор предотвращает создание экземпляров этого класса.
    private AddressMapper() { }

    static AddressDto toDto(Address address) {
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address, addressDto);
        return addressDto;
    }
    static Address toEntity(CreateAddressDto dto) {
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);
        return address;
    }
}
