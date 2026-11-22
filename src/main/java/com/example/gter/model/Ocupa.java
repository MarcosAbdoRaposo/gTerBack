package com.example.gter.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Ocupa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ideOcupa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ideTerceirizado", nullable = false, foreignKey = @ForeignKey(name = "fk_ocupa_terceirizado"))
    private Terceirizado terceirizado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idePostoTrabalho", nullable = false, foreignKey = @ForeignKey(name = "fk_ocupa_posto_trabalho"))
    private PostoTrabalho postoTrabalho;

    @Column(nullable = false, length = 1)
    private String codSituacao;


    @Column(nullable = false)
    private Date datInicio;

    @Column
    private Date datFim;

    @Column(nullable = true, name = "desLotacao", length = 128)
    private String desLotacao;

    @Column(nullable = true, name = "desLocalizacao", length = 128)
    private String desLocalizacao;



    public Ocupa() {
    }

    public Ocupa(UUID ideOcupa, Terceirizado terceirizado, PostoTrabalho postoTrabalho, String codSituacao, Date datInicio, Date datFim, String desLotacao, String desLocalizacao) {
        this.ideOcupa = ideOcupa;
        this.terceirizado = terceirizado;
        this.postoTrabalho = postoTrabalho;
        this.codSituacao = codSituacao;
        this.datInicio = datInicio;
        this.datFim = datFim;
        this.desLotacao = desLotacao;
        this.desLocalizacao = desLocalizacao;

    }

    public UUID getIdeOcupa() {
        return ideOcupa;
    }

    public void setIdeOcupa(UUID ideOcupa) {
        this.ideOcupa = ideOcupa;
    }

    public Terceirizado getTerceirizado() {
        return terceirizado;
    }

    public void setTerceirizado(Terceirizado terceirizado) {
        this.terceirizado = terceirizado;
    }

    public PostoTrabalho getPostoTrabalho() {
        return postoTrabalho;
    }

    public void setPostoTrabalho(PostoTrabalho postoTrabalho) {
        this.postoTrabalho = postoTrabalho;
    }

    public String getCodSituacao() {
        return codSituacao;
    }

    public String getDesSituacao() {
        if (this.codSituacao.equalsIgnoreCase("T")) {
            return "Titular";
        } else {
            return "Extra";
        }
    }

    public void setCodSituacao(String codSituacao) {
        this.codSituacao = codSituacao;
    }

    public Date getDatInicio() {
        return datInicio;
    }

    public void setDatInicio(Date datInicio) {
        this.datInicio = datInicio;
    }

    public Date getDatFim() {
        return datFim;
    }

    public void setDatFim(Date datFim) {
        this.datFim = datFim;
    }
}