package br.com.alura.codechella.infrastructure.controller;

import br.com.alura.codechella.domain.entities.user.User;

public class UserDtoMapper {
    public User toDomain(UserDto userDto) {
        return new User(userDto.cpf(), userDto.name(), userDto.birthDate(), userDto.email());
    }

    public UserDto toDto(User user) {
        return new UserDto(user.getCpf(), user.getName(), user.getBirthDate(), user.getEmail());
    }
}
