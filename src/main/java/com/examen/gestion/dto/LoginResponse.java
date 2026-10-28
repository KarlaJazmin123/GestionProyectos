package com.examen.gestion.dto;

public record LoginResponse(String token, String tipo, long expiraEnSegundos) {
}
