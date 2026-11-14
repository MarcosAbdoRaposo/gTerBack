package com.example.gter.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PostoTrabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private java.util.UUID idePostoTrabalho;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ideContrato", nullable = false, foreignKey = @ForeignKey(name = "fk_postoTrabalho_contrato"))
    private Contrato contrato;

    @Column(nullable = false, length = 60)
    private String nomPostoTrabalho;

    @Column(nullable = false)
    private int qtdPostoTrabalho;

    public PostoTrabalho() {
    }

    public PostoTrabalho(Contrato contrato, String nomPostoTrabalho, int qtdPostoTrabalho) {
        this.contrato = contrato;
        this.nomPostoTrabalho = nomPostoTrabalho;
        this.qtdPostoTrabalho = qtdPostoTrabalho;
    }

    public UUID getIdePostoTrabalho() {
        return idePostoTrabalho;
    }

    public void setIdePostoTrabalho(UUID idePostoTrabalho) {
        this.idePostoTrabalho = idePostoTrabalho;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public String getNomPostoTrabalho() {
        return nomPostoTrabalho;
    }

    public void setNomPostoTrabalho(String nomPostoTrabalho) {
        this.nomPostoTrabalho = nomPostoTrabalho;
    }

    public int getQtdPostoTrabalho() {
        return qtdPostoTrabalho;
    }

    public void setQtdPostoTrabalho(int qtdPostoTrabalho) {
        this.qtdPostoTrabalho = qtdPostoTrabalho;
    }
}
