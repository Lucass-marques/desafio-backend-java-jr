package desafio.backend.autoscore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record VeiculoRequest(
        @NotBlank(message = "Proprietário é obrigatório")
        String proprietario,

        @NotBlank
        @CPF(message = "CPF inválido")
        String cpf,

        @NotBlank
        @Pattern(regexp = "[A-Z]{3}-?[0-9][A-Z0-9]{2}",
        message = "Placa inválida")
        String placa
) {}
