package br.com.alura.codechella.config;

import br.com.alura.codechella.application.gateways.UserRepository;
import br.com.alura.codechella.application.usecases.UserRegister;
import br.com.alura.codechella.application.usecases.UserUpdate;
import br.com.alura.codechella.application.usecases.UsersList;
import br.com.alura.codechella.application.usecases.UserDelete;
import br.com.alura.codechella.application.usecases.UserSearch;
import br.com.alura.codechella.infrastructure.controller.UserDtoMapper;
import br.com.alura.codechella.infrastructure.gateways.UserJpaRepositoryAdapter;
import br.com.alura.codechella.infrastructure.mappers.UserEntityMapper;
import br.com.alura.codechella.infrastructure.persistance.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    UserRegister userRegister(UserRepository userRepository) {
        return new UserRegister(userRepository);
    }

    @Bean
    UserUpdate userUpdate(UserRepository userRepository) {
        return new UserUpdate(userRepository);
    }

    @Bean
    UsersList usersList(UserRepository userRepository) {
        return new UsersList(userRepository);
    }

    @Bean
    UserDelete userDelete(UserRepository userRepository) {
        return new UserDelete(userRepository);
    }

    @Bean
    UserSearch userSearch(UserRepository userRepository) {
        return new UserSearch(userRepository);
    }

    @Bean
    UserJpaRepositoryAdapter createUserRepositoryAdapter(UserJpaRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserJpaRepositoryAdapter(userRepository, userEntityMapper);
    }

    @Bean
    UserEntityMapper createUserEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDtoMapper createUserDtoMapper() {
        return new UserDtoMapper();
    }
}
