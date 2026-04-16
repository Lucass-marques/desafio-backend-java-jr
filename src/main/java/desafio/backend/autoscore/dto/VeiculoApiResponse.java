package desafio.backend.autoscore.dto;

public record VeiculoApiResponse(
     String marca,
     String modelo,
     String chassi,
     boolean licenciado
) {}
