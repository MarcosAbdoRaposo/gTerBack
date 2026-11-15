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
    public ResponseEntity<Contrato> buscarPorId(@PathVariable UUID ideContrato) {
        Contrato contrato = contratoService.buscarPorId(ideContrato);
        return ResponseEntity.ok(contrato);
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
    public ResponseEntity<Void> excluir(@PathVariable UUID ideContrato) {
        contratoService.excluir(ideContrato);
        return ResponseEntity.noContent().build();
    }
}
