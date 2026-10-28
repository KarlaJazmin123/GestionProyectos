package com.examen.gestion.controllers;

import com.examen.gestion.dto.ProyectoRequest;
import com.examen.gestion.dto.ProyectoResponse;
import com.examen.gestion.services.ProyectoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;


    @GetMapping
    public ResponseEntity<List<ProyectoResponse>> listar(Authentication authentication) {
        return ResponseEntity.ok(proyectoService.listarDelUsuario(authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<ProyectoResponse> crear(Authentication authentication,
                                                  @Valid @RequestBody ProyectoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(proyectoService.crear(authentication.getName(), request));
    }
}
