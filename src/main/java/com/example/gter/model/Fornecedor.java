package com.example.gter.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ideFornecedor;
    @Column(nullable = false, length = 128)
    private String nomFornecedor;
    @Column(nullable = false, length = 14)
    private String numCNPJ;

    public Fornecedor() {
    }

    public Fornecedor(String nomFornecedor, String numCNPJ) {
        this.nomFornecedor = nomFornecedor;
        this.numCNPJ = numCNPJ;
    }

    public UUID getIdeFornecedor() {
        return ideFornecedor;
    }

    public void setIdeFornecedor(UUID ideFornecedor) {
        this.ideFornecedor = ideFornecedor;
    }

    public String getNomFornecedor() {
        return nomFornecedor;
    }

    public void setNomFornecedor(String nomFornecedor) {
        this.nomFornecedor = nomFornecedor;
    }

    public String getNumCNPJ() {
        return numCNPJ;
    }

    public void setNumCNPJ(String numCNPJ) {
        this.numCNPJ = numCNPJ;
    }
}

