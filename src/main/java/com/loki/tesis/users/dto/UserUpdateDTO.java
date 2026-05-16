package com.loki.tesis.users.dto;

import com.loki.tesis.shared.address.dto.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(

        @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
        String nombre,

        @Size(max = 50, message = "El apellido no puede superar los 50 caracteres")
        String apellido,

        @Size(max = 20, message = "El numero de telefono no puede superar los 20 caracteres")
        @Pattern(
                regexp = "^$|^\\+?[0-9 \\-]{6,20}$",
                message = "El numero de telefono solo puede contener dígitos, espacios, guiones y opcionalmente un '+' al inicio"
        )
        String numeroTelefono,

        @Valid
        AddressDTO direccion
) {
}
