package com.examen.gestion.controllers;

import com.examen.gestion.dto.CredencialesRequest;
import com.examen.gestion.dto.LoginResponse;
import com.examen.gestion.dto.UsuarioResponse;
import com.examen.gestion.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registro(@Valid @RequestBody CredencialesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody CredencialesRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
