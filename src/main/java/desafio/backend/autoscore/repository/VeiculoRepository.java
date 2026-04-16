package desafio.backend.autoscore.repository;

import desafio.backend.autoscore.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByPlaca(String placa);
}
