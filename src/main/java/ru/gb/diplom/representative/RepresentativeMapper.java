package ru.gb.diplom.representative;

import org.springframework.beans.BeanUtils;

final class RepresentativeMapper {

    private RepresentativeMapper() {}

    static RepresentativeDto toDto(Representative r) {
        RepresentativeDto dto = new RepresentativeDto();
        BeanUtils.copyProperties(r, dto);
        return dto;
    }

    static Representative toEntity(CreateRepresentativeDto dto) {
        Representative r = new Representative();
        BeanUtils.copyProperties(dto, r);
        return r;
    }

}
