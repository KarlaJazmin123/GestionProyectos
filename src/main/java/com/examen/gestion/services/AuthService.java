package com.examen.gestion.services;

import com.examen.gestion.dto.CredencialesRequest;
import com.examen.gestion.dto.LoginResponse;
import com.examen.gestion.dto.UsuarioResponse;
import com.examen.gestion.exceptions.CredencialesInvalidasException;
import com.examen.gestion.exceptions.ReglaNegocioException;
import com.examen.gestion.models.Usuario;
import com.examen.gestion.repositories.UsuarioRepository;
import com.examen.gestion.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public UsuarioResponse registrar(CredencialesRequest request) {
        if (usuarioRepository.existsByUsername(request.username())) {
            throw new ReglaNegocioException("El username '" + request.username() + "' ya está en uso");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setRol("USER");
        return UsuarioResponse.de(usuarioRepository.save(usuario));
    }

    @Transactional(readOnly = true)
    public LoginResponse login(CredencialesRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .filter(u -> passwordEncoder.matches(request.password(), u.getPassword()))
                .orElseThrow(() -> new CredencialesInvalidasException("Credenciales inválidas"));
        return new LoginResponse(jwtService.generarToken(usuario), "Bearer", jwtService.getExpiracionSegundos());
    }
}
