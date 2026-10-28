package com.examen.gestion.exceptions;

// Se traduce a 400 Bad Request
public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
