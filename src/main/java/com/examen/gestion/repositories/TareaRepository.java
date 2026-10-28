package com.examen.gestion.repositories;

import com.examen.gestion.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    // Solo devuelve la tarea si su proyecto pertenece al usuario indicado
    Optional<Tarea> findByIdAndProyectoUsuarioId(Long id, Long usuarioId);
}
