package com.example.gter.controller;

import com.example.gter.model.Contrato;
import com.example.gter.service.ContratoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

    private final ContratoService contratoService;

    // Injeção de dependência via construtor
    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> listarTodos() {
        List<Contrato> contratos = contratoService.listarTodos();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/{ideContrato}")
    public ResponseEntity<Contrato> buscarPorId(@PathVariable String ideContrato) {
        Contrato contrato = contratoService.buscarPorId(UUID.fromString(ideContrato));
        return ResponseEntity.ok(contrato);
    }

    /**
     * Endpoint para buscar contratos pelo ID (UUID) do Órgão.
     * Exemplo de URL GET: /contrato/orgao/123e4567-e89b-12d3-a456-426614174000
     */
    @GetMapping("/orgao/{ideOrgao}")
    public ResponseEntity<List<Contrato>> buscarPorOrgao(@PathVariable String ideOrgao) {
        List<Contrato> contratos = contratoService.buscarPorOrgao(UUID.fromString(ideOrgao));

        // Retorna 200 OK com a lista (mesmo que vazia)
        return ResponseEntity.ok(contratos);
    }

    @PostMapping
    public ResponseEntity<Contrato> incluir(@RequestBody Contrato contrato) {
        Contrato novoContrato = contratoService.incluir(contrato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContrato);
    }

    @PutMapping
    public ResponseEntity<Contrato> atualizar(@RequestBody Contrato contratoAtualizado) {
        Contrato contrato = contratoService.atualizar(contratoAtualizado);
        return ResponseEntity.ok(contrato);
    }

    @DeleteMapping("/{ideContrato}")
    public ResponseEntity<Void> excluir(@PathVariable String ideContrato) {
        contratoService.excluir(UUID.fromString(ideContrato));
        return ResponseEntity.noContent().build();
    }
}
