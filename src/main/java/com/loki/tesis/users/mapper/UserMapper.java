package com.loki.tesis.users.mapper;

import com.loki.tesis.users.dto.UserRequestDTO;
import com.loki.tesis.users.dto.UserResponseDTO;
import com.loki.tesis.users.entity.User;
import org.mapstruct.*;

@Mapper (
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
        )
public interface UserMapper {

    @Mapping(source = "uuid", target = "id")
    UserResponseDTO toUserResponseDTO(User user);

    @Mapping(target = "id", ignore = true) // Ignoramos el ID, se generará automáticamente
    @Mapping(target = "estado", ignore = true) // Ignoramos el estado, se establecerá por defecto
    @Mapping(target = "fechaCreacion", ignore = true) // Ignoramos la fecha de creación, se establecerá automáticamente
    @Mapping(target = "uuid", ignore = true) // Ignoramos el UUID, se generará automáticamente
    User toUserEntity(UserRequestDTO userRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "tipoDocumento", ignore = true)
    @Mapping(target = "numeroDocumento", ignore = true)
    void updateUserFromDTO(UserRequestDTO userRequestDTO, @MappingTarget User user);
}
