package com.example.gter.controller;

import com.example.gter.model.Orgao;
import com.example.gter.service.OrgaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orgao")
public class OrgaoController {

    private final OrgaoService orgaoService;

    // Injeção de dependência via construtor
    public OrgaoController(OrgaoService orgaoService) {
        this.orgaoService = orgaoService;
    }

    @GetMapping
    public ResponseEntity<List<Orgao>> listarTodos() {
        List<Orgao> orgaos = orgaoService.listarTodos();
        return ResponseEntity.ok(orgaos);
    }

    @GetMapping("/{ideOrgao}")
    public ResponseEntity<Orgao> buscarPorId(@PathVariable UUID ideOrgao) {
        Orgao orgao = orgaoService.buscarPorId(ideOrgao);
        return ResponseEntity.ok(orgao);
    }

    @PostMapping
    public ResponseEntity<Orgao> incluir(@RequestBody Orgao orgao) {
        Orgao novoOrgao = orgaoService.incluir(orgao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoOrgao);
    }

    @PutMapping
    public ResponseEntity<Orgao> atualizar(@RequestBody Orgao orgaoAtualizado) {
        Orgao orgao = orgaoService.atualizar(orgaoAtualizado);
        return ResponseEntity.ok(orgao);
    }

    @DeleteMapping("/{ideOrgao}")
    public ResponseEntity<Void> excluir(@PathVariable UUID ideOrgao) {
        orgaoService.excluir(ideOrgao);
        return ResponseEntity.noContent().build();
    }
}