package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.domain.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserSearchTest {

    private UserRepository userRepository;
    private UserSearch userSearch;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userSearch = new UserSearch(userRepository);
    }

    @Test
    void shouldFindUserByCpf() {
        String cpf = "123.456.789-00";
        User user = new User(cpf, "John Doe", LocalDate.parse("1995-05-15"), "john@example.com");
        when(userRepository.findByCpf(cpf)).thenReturn(user);

        User result = userSearch.searchByCpf(cpf);

        assertEquals(user, result);
        verify(userRepository, times(1)).findByCpf(cpf);
    }
}
