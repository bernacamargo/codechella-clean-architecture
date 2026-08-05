package br.com.alura.codechella.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByCpf(String cpf);
}
