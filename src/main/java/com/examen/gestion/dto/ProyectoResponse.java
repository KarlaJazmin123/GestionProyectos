package com.examen.gestion.dto;

import com.examen.gestion.models.Proyecto;

import java.util.List;

public record ProyectoResponse(Long id, String nombre, String descripcion, List<TareaResponse> tareas) {
    public static ProyectoResponse de(Proyecto p) {
        return new ProyectoResponse(p.getId(), p.getNombre(), p.getDescripcion(),
                p.getTareas().stream().map(TareaResponse::de).toList());
    }
}
