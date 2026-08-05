package br.com.alura.codechella.domain.entities.user;

import br.com.alura.codechella.domain.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
public class User {
    private String cpf;
    private String name;
    private LocalDate birthDate;
    private String email;
    private Address address;

    public User (String cpf, String name, LocalDate birthDate, String email) {
        if (cpf == null || !cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("Invalid Age");
        }

        this.cpf = cpf;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }
}
