package com.example.gter.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Orgao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private java.util.UUID ideOrgao;
    @Column(nullable = false, length = 128)
    private String nomOrgao;
    @Column(name = "NUM_CNPJ", nullable = false, length = 14)
    private String numCNPJ;

    public Orgao() {
    }

    public Orgao(String nomOrgao, String numCNPJ) {
        this.nomOrgao = nomOrgao;
        this.numCNPJ = numCNPJ;
    }

    public UUID getIdeOrgao() {
        return ideOrgao;
    }

    public void setIdeOrgao(UUID ideOrgao) {
        this.ideOrgao = ideOrgao;
    }

    public String getNomOrgao() {
        return nomOrgao;
    }

    public void setNomOrgao(String nomOrgao) {
        this.nomOrgao = nomOrgao;
    }

    public String getNumCNPJ() {
        return numCNPJ;
    }

    public void setNumCNPJ(String numCNPJ) {
        this.numCNPJ = numCNPJ;
    }

    public String getNumCNPJFormatado() {
        return  numCNPJ.substring(0, 2) + "." +
                numCNPJ.substring(2, 5) + "." +
                numCNPJ.substring(5, 8) + "/" +
                numCNPJ.substring(8, 12) + "-" +
                numCNPJ.substring(12, 14);
    }

}

