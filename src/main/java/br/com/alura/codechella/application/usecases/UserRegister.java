package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.domain.entities.user.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRegister {
    private final UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

}
