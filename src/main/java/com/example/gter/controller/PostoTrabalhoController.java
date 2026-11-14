package com.example.gter.controller;

import com.example.gter.model.PostoTrabalho;
import com.example.gter.service.PostoTrabalhoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/postotrabalho")
public class PostoTrabalhoController {

    private final PostoTrabalhoService postoTrabalhoService;

    public PostoTrabalhoController(PostoTrabalhoService postoTrabalhoService) {
        this.postoTrabalhoService = postoTrabalhoService;
    }

    @GetMapping
    public ResponseEntity<List<PostoTrabalho>> listarTodos() {
        List<PostoTrabalho> postos = postoTrabalhoService.listarTodos();
        return ResponseEntity.ok(postos);
    }

    @GetMapping("/contrato/{ideContrato}")
    public ResponseEntity<List<PostoTrabalho>> listarPostoTrabalhoContrato(@PathVariable UUID ideContrato) {
        List<PostoTrabalho> postos = postoTrabalhoService.listarPostoTrabalhoContrato(ideContrato);

        return ResponseEntity.ok(postos);
    }

    @GetMapping("/{idePostoTrabalho}")
    public ResponseEntity<PostoTrabalho> buscarPorId(@PathVariable UUID idePostoTrabalho) {
        PostoTrabalho posto = postoTrabalhoService.buscarPorId(idePostoTrabalho);
        return ResponseEntity.ok(posto);
    }

    @PostMapping
    public ResponseEntity<PostoTrabalho> incluir(@RequestBody PostoTrabalho postoTrabalho) {
        PostoTrabalho novoPosto = postoTrabalhoService.incluir(postoTrabalho);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPosto);
    }

    @PutMapping
    public ResponseEntity<PostoTrabalho> atualizar(@RequestBody PostoTrabalho postoTrabalhoAtualizado) {
        PostoTrabalho posto = postoTrabalhoService.atualizar(postoTrabalhoAtualizado);
        return ResponseEntity.ok(posto);
    }

    @DeleteMapping("/{idePostoTrabalho}")
    public ResponseEntity<Void> excluir(@PathVariable UUID idePostoTrabalho) {
        postoTrabalhoService.excluir(idePostoTrabalho);
        return ResponseEntity.noContent().build();
    }
}