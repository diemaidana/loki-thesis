package com.loki.tesis.shared.address.dto;

import com.loki.tesis.shared.address.enums.Provinces;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(

        @NotBlank(message = "La calle es obligatoria.")
        @Size(max=100)
        String calle,
        @Size(max=20)
        String altura,

        @Size(max=10)
        String piso,

        @Size(max=10)
        String departamento,

        @Size(max=100)
        String entreCalleUno,

        @Size(max=100)
        String entreCalleDos,

        @NotBlank(message = "La ciudad es obligatoria.")
        @Size(max=100)
        String ciudad,

        @NotNull(message = "La provincia es obligatoria.")
        Provinces provincia,

        @NotBlank(message = "El codigo postal es obligatorio.")
        @Pattern(regexp = "^\\d{4}$|^[A-Z]\\d{4}[A-Z]{3}$",
                message = "El código postal debe ser 4 dígitos o formato CPA (ej: B7600ABC)")
        @Size(max=20)
        String codigoPostal,

        @Size(max=255)
        String referencias
) {

}
