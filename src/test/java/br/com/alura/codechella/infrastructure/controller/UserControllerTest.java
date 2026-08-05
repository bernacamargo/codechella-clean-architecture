package br.com.alura.codechella.infrastructure.controller;

import br.com.alura.codechella.infrastructure.persistance.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    void setUp() {
        userJpaRepository.deleteAll();
    }

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        String userJson = """
                {
                    "cpf": "123.456.789-00",
                    "name": "John Doe",
                    "birthDate": "1995-05-15",
                    "email": "john.doe@example.com"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("123.456.789-00"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.birthDate").value("1995-05-15"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void shouldNotRegisterUserWithInvalidCpf() throws Exception {
        String userJson = """
                {
                    "cpf": "12345678900",
                    "name": "John Doe",
                    "birthDate": "1995-05-15",
                    "email": "john.doe@example.com"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotRegisterUserUnderage() throws Exception {
        String userJson = """
                {
                    "cpf": "123.456.789-00",
                    "name": "Young Person",
                    "birthDate": "2015-05-15",
                    "email": "young@example.com"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateUserSuccessfully() throws Exception {
        // Register a user first
        shouldRegisterUserSuccessfully();

        String updateJson = """
                {
                    "cpf": "123.456.789-00",
                    "name": "John Doe Updated",
                    "birthDate": "1995-05-15",
                    "email": "john.updated@example.com"
                }
                """;

        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe Updated"))
                .andExpect(jsonPath("$.email").value("john.updated@example.com"));
    }

    @Test
    void shouldListAllUsers() throws Exception {
        shouldRegisterUserSuccessfully();

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cpf").value("123.456.789-00"));
    }

    @Test
    void shouldSearchUserByCpf() throws Exception {
        shouldRegisterUserSuccessfully();

        mockMvc.perform(get("/users/123.456.789-00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("123.456.789-00"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        shouldRegisterUserSuccessfully();

        mockMvc.perform(delete("/users/123.456.789-00"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
