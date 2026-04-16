package desafio.backend.autoscore.service;

import desafio.backend.autoscore.client.ApiClient;
import desafio.backend.autoscore.dto.VeiculoApiResponse;
import desafio.backend.autoscore.dto.VeiculoRequest;
import desafio.backend.autoscore.dto.VeiculoResponse;
import desafio.backend.autoscore.exception.ConflictException;
import desafio.backend.autoscore.exception.NotFoundException;
import desafio.backend.autoscore.model.Veiculo;
import desafio.backend.autoscore.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ApiClient apiClient;

    public List<VeiculoResponse> findAll() {
        return veiculoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public VeiculoResponse findById(Long id) {
        Veiculo veiculo =  veiculoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veículo não encontrado"));

        return toResponse(veiculo);
    }

    public VeiculoResponse cadastrarVeiculo(VeiculoRequest request) {

        if (veiculoRepository.existsByCpf(request.cpf())) {
            throw new ConflictException("CPF já cadastrado");
        }

        if (veiculoRepository.existsByPlaca(request.placa())) {
            throw new ConflictException("Placa já cadastrada");
        }

        VeiculoApiResponse apiResponse = apiClient.getData(request.placa());

        Veiculo veiculo = Veiculo.builder()
                .proprietario(request.proprietario())
                .cpf(request.cpf())
                .placa(request.placa())
                .marca(apiResponse.marca())
                .chassi(apiResponse.chassi())
                .modelo(apiResponse.modelo())
                .licenciado(apiResponse.licenciado())
                .build();

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

        return toResponse(veiculoSalvo);
    }

    private VeiculoResponse toResponse(Veiculo veiculo) {
        return new VeiculoResponse(
                veiculo.getId(),
                veiculo.getProprietario(),
                veiculo.getCpf(),
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getChassi(),
                veiculo.isLicenciado()
        );
    }
}
