package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.domain.entities.user.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UsersList {
    private final UserRepository userRepository;

    public List<User> retrieveAll() {
        return userRepository.findAll();
    }
}
