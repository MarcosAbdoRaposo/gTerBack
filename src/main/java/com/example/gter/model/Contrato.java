package com.example.gter.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ideContrato;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ideOrgao", nullable = false, foreignKey = @ForeignKey(name = "fk_contrato_orgao"))
    private Orgao orgao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ideFornecedor", nullable = false, foreignKey = @ForeignKey(name = "fk_contrato_fornecedor"))
    private Fornecedor fornecedor;

    @Column(nullable = false)
    private int anoContrato;

    @Column(nullable = false)
    private int numContrato;

    @Column(nullable = false)
    private Date datInicioVigencia;

    @Column
    private Date datFimVigencia;

    public Contrato() {
    }

    public Contrato(Orgao orgao, Fornecedor fornecedor, int anoContrato, int numContrato, Date datInicioVigencia, Date datFimVigencia) {
        this.orgao = orgao;
        this.fornecedor = fornecedor;
        this.anoContrato = anoContrato;
        this.numContrato = numContrato;
        this.datInicioVigencia = datInicioVigencia;
        this.datFimVigencia = datFimVigencia;
    }

    public UUID getIdeContrato() {
        return ideContrato;
    }

    public void setIdeContrato(UUID ideContrato) {
        this.ideContrato = ideContrato;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getAnoContrato() {
        return anoContrato;
    }

    public void setAnoContrato(int anoContrato) {
        this.anoContrato = anoContrato;
    }

    public int getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(int numContrato) {
        this.numContrato = numContrato;
    }

    public Date getDatInicioVigencia() {
        return datInicioVigencia;
    }

    public void setDatInicioVigencia(Date datInicioVigencia) {
        this.datInicioVigencia = datInicioVigencia;
    }

    public Date getDatFimVigencia() {
        return datFimVigencia;
    }

    public void setDatFimVigencia(Date datFimVigencia) {
        this.datFimVigencia = datFimVigencia;
    }
}