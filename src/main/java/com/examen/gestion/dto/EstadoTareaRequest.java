package com.examen.gestion.dto;

import com.examen.gestion.models.EstadoTarea;
import jakarta.validation.constraints.NotNull;

public record EstadoTareaRequest(
        @NotNull(message = "El estado es obligatorio (PENDIENTE, EN_PROGRESO o COMPLETADA)")
        EstadoTarea estado) {
}
