package br.com.alura.codechella.infrastructure.mappers;

import br.com.alura.codechella.domain.entities.user.User;
import br.com.alura.codechella.infrastructure.persistance.UserEntity;

public class UserEntityMapper {
    public UserEntity toEntity(User user) {
        return new UserEntity(user.getCpf(), user.getName(), user.getBirthDate(), user.getEmail());
    }

    public User toDomain(UserEntity userEntity) {
        return new User(userEntity.getCpf(), userEntity.getName(), userEntity.getBirthDate(), userEntity.getEmail());
    }
}
