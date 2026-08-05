package br.com.alura.codechella.domain.entities.user;

import br.com.alura.codechella.domain.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class UserTest {

    @Test
    void shouldNotRegisterUserWithInvalidCPF() {
        final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("123456790", "Ber", LocalDate.parse("1990-09-08"), "email@email.com"));
        Assertions.assertEquals("Invalid CPF", exception.getMessage());
    }

    @Test
    void shouldRegisterUserWithValidCPF() {
        Assertions.assertDoesNotThrow(
                () -> new User("123.123.123-43", "Ber", LocalDate.parse("1990-09-08"), "email@email.com"));
    }

    @Test
    void shouldCreateUserUsingUserFactory() {
        UserFactory factory = new UserFactory();
        User user = factory.withNameCpfBirthDate("Ber", "123.123.123-12", LocalDate.parse("1990-09-08"));
        user = factory.includeAddress(new Address("cep", 123, "complemento"));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Ber", user.getName());
        Assertions.assertNotNull(user.getAddress());
    }

    @Test
    void shoudNotCreateUserWithLessThan18years() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("123.123.123-12", "Ber", LocalDate.parse("2010-04-03"), "email"));

        Assertions.assertEquals("Invalid Age", exception.getMessage());
    }
}
