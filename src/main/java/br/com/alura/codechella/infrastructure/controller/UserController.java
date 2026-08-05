package br.com.alura.codechella.infrastructure.controller;

import br.com.alura.codechella.application.usecases.UserRegister;
import br.com.alura.codechella.application.usecases.UserUpdate;
import br.com.alura.codechella.application.usecases.UsersList;
import br.com.alura.codechella.application.usecases.UserDelete;
import br.com.alura.codechella.application.usecases.UserSearch;
import br.com.alura.codechella.domain.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRegister userRegister;
    private final UserUpdate userUpdate;
    private final UsersList usersList;
    private final UserDelete userDelete;
    private final UserSearch userSearch;

    private final UserDtoMapper dtoMapper;

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        User createdUser = userRegister.register(dtoMapper.toDomain(userDto));
        return ResponseEntity.ok(dtoMapper.toDto(createdUser));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        User updatedUser = userUpdate.update(dtoMapper.toDomain(userDto));
        return ResponseEntity.ok(dtoMapper.toDto(updatedUser));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> listAll() {
        List<User> users = usersList.retrieveAll();
        return ResponseEntity.ok(users.stream().map(dtoMapper::toDto).toList());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UserDto> searchByCpf(@PathVariable String cpf) {
        User user = userSearch.searchByCpf(cpf);
        return ResponseEntity.ok(dtoMapper.toDto(user));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        userDelete.delete(cpf);
        return ResponseEntity.noContent().build();
    }
}
