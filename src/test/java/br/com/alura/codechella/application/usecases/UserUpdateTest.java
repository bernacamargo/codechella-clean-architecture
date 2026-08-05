package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.domain.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserUpdateTest {

    private UserRepository userRepository;
    private UserUpdate userUpdate;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userUpdate = new UserUpdate(userRepository);
    }

    @Test
    void shouldUpdateAllFieldsSuccessfully() {
        User existingUser = new User("123.456.789-00", "John Doe", LocalDate.parse("1995-05-15"), "john.doe@example.com");
        User updatedInfo = new User("123.456.789-00", "John Doe Updated", LocalDate.parse("1995-05-15"), "john.updated@example.com");

        when(userRepository.findByCpf("123.456.789-00")).thenReturn(existingUser);
        when(userRepository.update(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userUpdate.update(updatedInfo);

        assertEquals("John Doe Updated", result.getName());
        assertEquals("john.updated@example.com", result.getEmail());
        verify(userRepository, times(1)).findByCpf("123.456.789-00");
        verify(userRepository, times(1)).update(any(User.class));
    }

    @Test
    void shouldUpdateOnlyEmailSuccessfully() {
        User existingUser = new User("123.456.789-00", "John Doe", LocalDate.parse("1995-05-15"), "john.doe@example.com");
        User updatedInfo = new User("123.456.789-00", "John Doe", LocalDate.parse("1995-05-15"), "john.updated@example.com");

        when(userRepository.findByCpf("123.456.789-00")).thenReturn(existingUser);
        when(userRepository.update(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userUpdate.updateOnlyEmail(updatedInfo);

        assertEquals("john.updated@example.com", result.getEmail());
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).findByCpf("123.456.789-00");
        verify(userRepository, times(1)).update(any(User.class));
    }
}
