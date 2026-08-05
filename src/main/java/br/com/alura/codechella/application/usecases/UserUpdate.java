package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.domain.entities.user.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUpdate {
    private final UserRepository userRepository;

    public User update(User user) {
        User foundUser = userRepository.findByCpf(user.getCpf());
        foundUser.setEmail(user.getEmail());
        foundUser.setName(user.getName());
        foundUser.setCpf(user.getCpf());
        foundUser.setBirthDate(user.getBirthDate());
        foundUser.setAddress(user.getAddress());
        return userRepository.update(foundUser);
    }

    public User updateOnlyEmail(User user) {
        User foundUser = userRepository.findByCpf(user.getCpf());
        foundUser.setEmail(user.getEmail());
        return userRepository.update(foundUser);
    }
}
