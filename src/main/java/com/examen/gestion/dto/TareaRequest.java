package com.examen.gestion.dto;

import com.examen.gestion.models.EstadoTarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TareaRequest(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 100, message = "El título no puede pasar de 100 caracteres")
        String titulo,

        // Opcional: si no se envía, la tarea queda PENDIENTE
        EstadoTarea estado,

        @NotNull(message = "El proyectoId es obligatorio")
        Long proyectoId) {
}
