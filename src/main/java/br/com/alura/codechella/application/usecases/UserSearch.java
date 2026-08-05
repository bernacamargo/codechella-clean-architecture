package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.domain.entities.user.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserSearch {
    private final UserRepository userRepository;

    public User searchByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }
}
