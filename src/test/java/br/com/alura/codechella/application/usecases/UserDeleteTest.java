package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class UserDeleteTest {

    private UserRepository userRepository;
    private UserDelete userDelete;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userDelete = new UserDelete(userRepository);
    }

    @Test
    void shouldCallDeleteOnRepository() {
        String cpf = "123.456.789-00";

        userDelete.delete(cpf);

        verify(userRepository, times(1)).delete(cpf);
    }
}
