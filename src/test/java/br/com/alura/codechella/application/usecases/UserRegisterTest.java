package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.domain.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserRegisterTest {

    private UserRepository userRepository;
    private UserRegister userRegister;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userRegister = new UserRegister(userRepository);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        User user = new User("123.456.789-00", "John Doe", LocalDate.parse("1995-05-15"), "john.doe@example.com");
        when(userRepository.save(user)).thenReturn(user);

        User registeredUser = userRegister.register(user);

        assertEquals(user, registeredUser);
        verify(userRepository, times(1)).save(user);
    }
}
