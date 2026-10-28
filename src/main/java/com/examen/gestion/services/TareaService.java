package com.examen.gestion.services;

import com.examen.gestion.dto.EstadoTareaRequest;
import com.examen.gestion.dto.TareaRequest;
import com.examen.gestion.dto.TareaResponse;
import com.examen.gestion.exceptions.RecursoNoEncontradoException;
import com.examen.gestion.models.EstadoTarea;
import com.examen.gestion.models.Proyecto;
import com.examen.gestion.models.Tarea;
import com.examen.gestion.models.Usuario;
import com.examen.gestion.repositories.ProyectoRepository;
import com.examen.gestion.repositories.TareaRepository;
import com.examen.gestion.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TareaService {

    private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public TareaResponse crear(String username, TareaRequest request) {
        Usuario usuario = buscarUsuario(username);


        Proyecto proyecto = proyectoRepository.findByIdAndUsuarioId(request.proyectoId(), usuario.getId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Proyecto con id " + request.proyectoId() + " no encontrado"));

        Tarea tarea = new Tarea();
        tarea.setTitulo(request.titulo());
        tarea.setEstado(request.estado() != null ? request.estado() : EstadoTarea.PENDIENTE);
        tarea.setProyecto(proyecto);
        return TareaResponse.de(tareaRepository.save(tarea));
    }

    @Transactional
    public TareaResponse actualizarEstado(String username, Long id, EstadoTareaRequest request) {
        Usuario usuario = buscarUsuario(username);

        Tarea tarea = tareaRepository.findByIdAndProyectoUsuarioId(id, usuario.getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Tarea con id " + id + " no encontrada"));

        tarea.setEstado(request.estado());
        return TareaResponse.de(tareaRepository.save(tarea));
    }

    private Usuario buscarUsuario(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));
    }
}
