package desafio.backend.autoscore.controller;

import desafio.backend.autoscore.dto.VeiculoRequest;
import desafio.backend.autoscore.dto.VeiculoResponse;
import desafio.backend.autoscore.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> listarTodos() {
        return ResponseEntity.ok(veiculoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> criarVeiculo(@RequestBody @Valid VeiculoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.cadastrarVeiculo(request));
    }
}
