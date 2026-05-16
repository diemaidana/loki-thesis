package com.loki.tesis.users.mapper;

import com.loki.tesis.users.dto.UserRequestDTO;
import com.loki.tesis.users.dto.UserResponseDTO;
import com.loki.tesis.users.dto.UserUpdateDTO;
import com.loki.tesis.users.entity.User;
import org.mapstruct.*;

@Mapper (
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
        )
public interface UserMapper {

    @Mapping(source = "uuid", target = "id")
    UserResponseDTO toUserResponseDTO(User user);

    @Mapping(target = "id", ignore = true) // Ignoramos el ID
    @Mapping(target = "estado", ignore = true) // Ignoramos el estado
    @Mapping(target = "fechaCreacion", ignore = true) // Ignoramos la fecha de creación
    @Mapping(target = "uuid", ignore = true) // Ignoramos el UUID
    User toUserEntity(UserRequestDTO userRequestDTO);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            ignoreByDefault = true
    )
    // Dice que ignore los datos que no estas en el DTO.
    // Hacemos una whiteList en vez de una blacklist como arriba.
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "apellido", source = "apellido")
    @Mapping(target = "numeroTelefono", source = "numeroTelefono")
    @Mapping(target = "direccion", source = "direccion")
    void updateUserFromDTO(UserUpdateDTO userUpdateDTO, @MappingTarget User user);
}
