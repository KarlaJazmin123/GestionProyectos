package com.examen.gestion.services;

import com.examen.gestion.dto.ProyectoRequest;
import com.examen.gestion.dto.ProyectoResponse;
import com.examen.gestion.exceptions.RecursoNoEncontradoException;
import com.examen.gestion.exceptions.ReglaNegocioException;
import com.examen.gestion.models.Proyecto;
import com.examen.gestion.models.Usuario;
import com.examen.gestion.repositories.ProyectoRepository;
import com.examen.gestion.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;

    @Value("${app.proyectos.limite-por-usuario:3}")
    private int limiteProyectos;

    @Transactional(readOnly = true)
    public List<ProyectoResponse> listarDelUsuario(String username) {
        Usuario usuario = buscarUsuario(username);
        return proyectoRepository.findByUsuarioId(usuario.getId()).stream()
                .map(ProyectoResponse::de)
                .toList();
    }

    @Transactional
    public ProyectoResponse crear(String username, ProyectoRequest request) {
        Usuario usuario = buscarUsuario(username);

        // Regla de negocio: máximo 3 proyectos por usuario
        if (proyectoRepository.countByUsuarioId(usuario.getId()) >= limiteProyectos) {
            throw new ReglaNegocioException(
                    "Alcanzaste el límite de " + limiteProyectos + " proyectos por usuario");
        }

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(request.nombre());
        proyecto.setDescripcion(request.descripcion());
        proyecto.setUsuario(usuario); // se asigna automáticamente al usuario logueado
        return ProyectoResponse.de(proyectoRepository.save(proyecto));
    }

    private Usuario buscarUsuario(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));
    }
}
