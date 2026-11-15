package com.example.gter.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Terceirizado {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ideTerceirizado;

    @Column(nullable = false, length = 50)
    private String nomTerceirizado;

    @Column(nullable = false, length = 11, unique = true)
    private String numCPF;

    @Column(nullable = false)
    private Date datNascimento;

    @Column(nullable = false)
    private String codSexo;

    @Column(nullable = false)
    private String nomLogradouro;

    @Column(length = 36)
    private String nomBairro;

    @Column(nullable = false, length = 36)
    private String nomCidade;

    @Column(nullable = false, length = 2)
    private String sigUF;

    @Column(nullable = false, length = 48)
    private String nomPais;

    @Column(length = 10)
    private String numCep;

    public Terceirizado() {
    }

    public Terceirizado(String nomTerceirizado, String numCPF, Date datNascimento, String codSexo, String nomLogradouro, String nomBairro, String nomCidade, String sigUF, String nomPais, String numCep) {
        this.nomTerceirizado = nomTerceirizado;
        this.numCPF = numCPF;
        this.datNascimento = datNascimento;
        this.codSexo = codSexo;
        this.nomLogradouro = nomLogradouro;
        this.nomBairro = nomBairro;
        this.nomCidade = nomCidade;
        this.sigUF = sigUF;
        this.nomPais = nomPais;
        this.numCep = numCep;
    }

    public UUID getIdeTerceirizado() {
        return ideTerceirizado;
    }

    public void setIdeTerceirizado(UUID ideTerceirizado) {
        this.ideTerceirizado = ideTerceirizado;
    }

    public String getNomTerceirizado() {
        return nomTerceirizado;
    }

    public void setNomTerceirizado(String nomTerceirizado) {
        this.nomTerceirizado = nomTerceirizado;
    }

    public String getNumCPF() {
        return numCPF;
    }

    public void setNumCPF(String numCPF) {
        this.numCPF = numCPF;
    }

    public Date getDatNascimento() {
        return datNascimento;
    }

    public void setDatNascimento(Date datNascimento) {
        this.datNascimento = datNascimento;
    }

    public String getCodSexo() {
        return codSexo;
    }

    public void setCodSexo(String codSexo) {
        this.codSexo = codSexo;
    }

    public String getNomLogradouro() {
        return nomLogradouro;
    }

    public void setNomLogradouro(String nomLogradouro) {
        this.nomLogradouro = nomLogradouro;
    }

    public String getNomBairro() {
        return nomBairro;
    }

    public void setNomBairro(String nomBairro) {
        this.nomBairro = nomBairro;
    }

    public String getNomCidade() {
        return nomCidade;
    }

    public void setNomCidade(String nomCidade) {
        this.nomCidade = nomCidade;
    }

    public String getSigUF() {
        return sigUF;
    }

    public void setSigUF(String sigUF) {
        this.sigUF = sigUF;
    }

    public String getNomPais() {
        return nomPais;
    }

    public void setNomPais(String nomPais) {
        this.nomPais = nomPais;
    }

    public String getNumCep() {
        return numCep;
    }

    public void setNumCep(String numCep) {
        this.numCep = numCep;
    }
}
