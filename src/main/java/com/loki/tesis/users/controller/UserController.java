package com.loki.tesis.users.controller;

import com.loki.tesis.users.dto.UserRequestDTO;
import com.loki.tesis.users.dto.UserResponseDTO;
import com.loki.tesis.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me") // Esto es una prueba a ver si funcionaba Postman (resultado? NO funciona)
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok("Hello world. El endpoint funciona bien.");
    }


    // 201 - Created
    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
