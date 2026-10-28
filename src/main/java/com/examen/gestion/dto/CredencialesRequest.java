package com.examen.gestion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CredencialesRequest(
        @NotBlank(message = "El username es obligatorio")
        @Size(max = 50, message = "El username no puede pasar de 50 caracteres")
        String username,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
        String password) {
}
