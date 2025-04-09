package com.ecommerce.application.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
    int estado,
    String error,
    String mensaje,
    String ruta,
    LocalDateTime marcaDeTiempo
) {}