package com.loki.tesis.users.service;

import com.loki.tesis.users.dto.UserRequestDTO;
import com.loki.tesis.users.dto.UserResponseDTO;
import com.loki.tesis.users.dto.UserUpdateDTO;
import com.loki.tesis.users.entity.User;
import com.loki.tesis.users.exception.EmailAlreadyExistsException;
import com.loki.tesis.users.exception.UserNotFoundException;
import com.loki.tesis.users.mapper.UserMapper;
import com.loki.tesis.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /* Busco los datos del usuario. */
    @Transactional(readOnly = true) // esto basicamente es para que SOLO sea de Lectura.
    public UserResponseDTO getUserByUuid(UUID uuid) {
        User user = findUserEntityByUuid(uuid);
        return userMapper.toUserResponseDTO(user);
    }

    /* Creo un nuevo usuario. */
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if(userRepository.existsByEmail(userRequestDTO.email())) {
            throw new EmailAlreadyExistsException("El email ya esta registrado");
        }

        User user = userMapper.toUserEntity(userRequestDTO);

        /* TODO deberia hacer un password encoder cuando tengamos el auth/ funcionando. */

        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    /* hago un Update del Usuario */
    public UserResponseDTO updateUser(UUID uuid, UserUpdateDTO  userUpdateDTO) {
        User user = findUserEntityByUuid(uuid);
        userMapper.updateUserFromDTO(userUpdateDTO, user);
        return userMapper.toUserResponseDTO(user);
    }

    /* Funciones privadas */

    /* Lo utilizo cuando se cree el auth/ */
    private User findUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("El usuario con email " + email + " no fue encontrado"));
    }

    private User findUserEntityByUuid(UUID uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("El usuario con UUID " + uuid + " no fue encontrado"));
    }
}

