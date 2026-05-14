package com.loki.tesis.users.dto;

import com.loki.tesis.shared.address.dto.AddressDTO;
import com.loki.tesis.users.enums.SocialNumberType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 50, message = "El apellido no puede superar los 50 caracteres")
        String apellido,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del email no es válido")
        @Size(max = 100, message = "El email no puede superar los 100 caracteres")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        @Size(max = 16, message = "La contraseña no puede superar los 16 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
                message = "La contraseña debe contener al menos una mayúscula, un número y un carácter especial"
        )
        String password,

        @NotNull(message = "El tipo de documento es obligatorio")
        SocialNumberType tipoDocumento,

        @NotBlank(message = "El número de documento es obligatorio")
        @Pattern(regexp = "^\\d+$", message = "El número de documento solo puede contener dígitos")
        String numeroDocumento,

        @Size(max = 20, message = "El número de teléfono no puede superar los 20 caracteres")
        @Pattern(
                regexp = "^$|^\\+?[0-9 \\-]{6,20}$",
                message = "El número de teléfono solo puede contener dígitos, espacios, guiones y opcionalmente un '+' al inicio"
        )
        String numeroTelefono,

        @Valid
        AddressDTO direccion

) {

    @AssertTrue(message = "El DNI debe tener 7 u 8 dígitos; el CUIT/CUIL debe tener exactamente 11")
    public boolean isLongDocumentValid() {
        if (tipoDocumento == null || numeroDocumento == null) {
            return true; // dejamos que @NotNull / @NotBlank reporten el error real
        }
        int longitud = numeroDocumento.length();
        return switch (tipoDocumento) {
            case DNI -> longitud == 8;
            case CUIT, CUIL -> longitud == 11;
        };
    }
}
