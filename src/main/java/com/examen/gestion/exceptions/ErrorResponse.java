package com.examen.gestion.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(LocalDateTime fecha, int status, String error, String mensaje, String ruta,
                            Map<String, String> detalles) {
}
