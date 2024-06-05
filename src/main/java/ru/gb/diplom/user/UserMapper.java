package ru.gb.diplom.user;

import org.springframework.beans.BeanUtils;

final class UserMapper {

    private UserMapper() {}


    static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }



    static User toEntity(CreateUserDto createUserDto) {
        return new User().builder()
                .username(createUserDto.getUsername())
                .firstname(createUserDto.getFirstname())
                .lastname(createUserDto.getLastname())
                .password(createUserDto.getPassword())
                .email(createUserDto.getEmail())
                .phone(createUserDto.getPhone())
                .active(createUserDto.getActive())
                .created(createUserDto.getCreated())
                .roles(createUserDto.getRoles())
                .position(createUserDto.getPosition())
                .build();

    }


}
