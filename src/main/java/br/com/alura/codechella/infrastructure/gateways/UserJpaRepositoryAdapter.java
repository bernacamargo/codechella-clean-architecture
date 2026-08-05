package br.com.alura.codechella.infrastructure.gateways;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.domain.entities.user.User;
import br.com.alura.codechella.infrastructure.mappers.UserEntityMapper;
import br.com.alura.codechella.infrastructure.persistance.UserEntity;
import br.com.alura.codechella.infrastructure.persistance.UserJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaRepositoryAdapter implements UserRepository {

    private final UserJpaRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.toEntity(user);
        return mapper.toDomain(repository.save(userEntity));
    }

    @Override
    public User update(User user) {
        final UserEntity userEntity = repository.findByCpf(user.getCpf());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setBirthDate(user.getBirthDate());
        return mapper.toDomain(repository.save(userEntity));
    }

    @Override
    public User findByCpf(String cpf) {
        return mapper.toDomain(repository.findByCpf(cpf));
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> userEntities = repository.findAll();
        return userEntities
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(String cpf) {
        UserEntity userEntity = repository.findByCpf(cpf);
        if (userEntity != null) {
            repository.delete(userEntity);
        }
    }
}
