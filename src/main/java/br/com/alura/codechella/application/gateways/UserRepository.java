package br.com.alura.codechella.application.gateways;

import br.com.alura.codechella.domain.entities.user.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User update(User user);
    User findByCpf(String cpf);
    List<User> findAll();
    void delete(String cpf);
}
