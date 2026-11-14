package com.example.gter.controller;

import com.example.gter.model.Fornecedor;
import com.example.gter.service.FornecedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    // Injeção de dependência via construtor
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarTodos() {
        List<Fornecedor> fornecedores = fornecedorService.listarTodos();
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/{ideFornecedor}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable UUID ideFornecedor) {
        Fornecedor fornecedor = fornecedorService.buscarPorId(ideFornecedor);
        return ResponseEntity.ok(fornecedor);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> incluir(@RequestBody Fornecedor fornecedor) {
        Fornecedor novoFornecedor = fornecedorService.incluir(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFornecedor);
    }

    @PutMapping
    public ResponseEntity<Fornecedor> atualizar(@RequestBody Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = fornecedorService.atualizar(fornecedorAtualizado);
        return ResponseEntity.ok(fornecedor);
    }

    @DeleteMapping("/{ideFornecedor}")
    public ResponseEntity<Void> excluir(@PathVariable UUID ideFornecedor) {
        fornecedorService.excluir(ideFornecedor);
        return ResponseEntity.noContent().build();
    }
}