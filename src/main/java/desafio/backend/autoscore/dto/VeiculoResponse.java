package desafio.backend.autoscore.dto;

public record VeiculoResponse(
        Long id,
        String proprietario,
        String cpf,
        String placa,
        String marca,
        String modelo,
        String chassi,
        boolean licenciado
) {}
