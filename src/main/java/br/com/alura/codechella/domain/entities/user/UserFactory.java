package br.com.alura.codechella.domain.entities.user;

import br.com.alura.codechella.domain.Address;

import java.time.LocalDate;

public class UserFactory {
    private User user;

    public User withNameCpfBirthDate(String name, String cpf, LocalDate birthDate) {
        this.user = new User(cpf, name, birthDate, "");
        return this.user;
    }

    public User includeAddress(Address address) {
        this.user.setAddress(address);
        return this.user;
    }
}
