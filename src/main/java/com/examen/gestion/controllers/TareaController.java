package com.examen.gestion.controllers;

import com.examen.gestion.dto.EstadoTareaRequest;
import com.examen.gestion.dto.TareaRequest;
import com.examen.gestion.dto.TareaResponse;
import com.examen.gestion.services.TareaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    @PostMapping
    public ResponseEntity<TareaResponse> crear(Authentication authentication,
                                               @Valid @RequestBody TareaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tareaService.crear(authentication.getName(), request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaResponse> actualizarEstado(Authentication authentication,
                                                          @PathVariable Long id,
                                                          @Valid @RequestBody EstadoTareaRequest request) {
        return ResponseEntity.ok(tareaService.actualizarEstado(authentication.getName(), id, request));
    }
}
