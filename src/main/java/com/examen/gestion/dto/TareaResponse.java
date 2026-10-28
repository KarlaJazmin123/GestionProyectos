package com.examen.gestion.dto;

import com.examen.gestion.models.EstadoTarea;
import com.examen.gestion.models.Tarea;

public record TareaResponse(Long id, String titulo, EstadoTarea estado, Long proyectoId) {
    public static TareaResponse de(Tarea t) {
        return new TareaResponse(t.getId(), t.getTitulo(), t.getEstado(), t.getProyecto().getId());
    }
}
