package desafio.backend.autoscore.exception;

public record ErrorResponse(
        int status,
        String message
) {}
