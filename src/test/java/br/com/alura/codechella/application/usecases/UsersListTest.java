package br.com.alura.codechella.application.usecases;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.domain.entities.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsersListTest {

    private UserRepository userRepository;
    private UsersList usersList;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        usersList = new UsersList(userRepository);
    }

    @Test
    void shouldRetrieveAllUsers() {
        User user1 = new User("123.456.789-00", "John Doe", LocalDate.parse("1995-05-15"), "john@example.com");
        User user2 = new User("321.654.987-00", "Jane Doe", LocalDate.parse("1996-06-16"), "jane@example.com");
        List<User> list = List.of(user1, user2);

        when(userRepository.findAll()).thenReturn(list);

        List<User> result = usersList.retrieveAll();

        assertEquals(list, result);
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }
}
