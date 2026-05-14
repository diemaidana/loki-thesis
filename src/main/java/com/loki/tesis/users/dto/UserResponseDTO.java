package com.loki.tesis.users.dto;

import com.loki.tesis.shared.address.dto.AddressDTO;
import com.loki.tesis.users.enums.SocialNumberType;
import com.loki.tesis.users.enums.StateAccount;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String nombre,
        String apellido,
        String email,
        SocialNumberType tipoDocumento,
        String numeroDocumento,
        AddressDTO direccion,
        StateAccount estado,
        Instant fechaCreacion
) {}
