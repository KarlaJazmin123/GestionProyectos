package com.examen.gestion.dto;

import com.examen.gestion.models.Usuario;

public record UsuarioResponse(Long id, String username, String rol) {
    public static UsuarioResponse de(Usuario u) {
        return new UsuarioResponse(u.getId(), u.getUsername(), u.getRol());
    }
}
