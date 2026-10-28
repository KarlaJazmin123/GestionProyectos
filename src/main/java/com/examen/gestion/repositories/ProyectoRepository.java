package com.examen.gestion.repositories;

import com.examen.gestion.models.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByUsuarioId(Long usuarioId);
    long countByUsuarioId(Long usuarioId);
    Optional<Proyecto> findByIdAndUsuarioId(Long id, Long usuarioId);
}
