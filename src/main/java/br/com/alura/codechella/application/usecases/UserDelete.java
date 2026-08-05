package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDelete {
    private final UserRepository userRepository;

    public void delete(String cpf) {
        userRepository.delete(cpf);
    }
}
