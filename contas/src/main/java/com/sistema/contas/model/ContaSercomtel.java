package com.sistema.contas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name ="contas_sercomtel", schema ="sistema")
public class ContaSercomtel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private Integer mes;

    @NotBlank
    private Integer ano;
    @NotBlank
    private Date vencimento;

    @NotBlank
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @NotBlank
    @Column(name="nota_fiscal")
    private String notaFiscal;

    @NotBlank
    @Column(name="numero_protocolo")
    private String numeroProtocolo;

    @NotBlank
    @Column(name="data_envio_pagamento")
    private Date dataEnvioPagamento;

    @NotBlank
    @Column(name="conta_inativa")
    private Character contaInativa;

    @ManyToOne
    @JoinColumn(name="local_sercomtel_id", nullable = false)
    private LocalSercomtel localSercomtel;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getMes () {
        return mes;
    }

    public void setMes (Integer mes) {
        this.mes = mes;
    }

    public Integer getAno () {
        return ano;
    }

    public void setAno (Integer ano) {
        this.ano = ano;
    }

    public Date getVencimento () {
        return vencimento;
    }

    public void setVencimento (Date vencimento) {
        this.vencimento = vencimento;
    }

    public BigDecimal getValor () {
        return valor;
    }

    public void setValor (BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor nao pode ser nulo nem negativo");
        }
        this.valor = valor;
    }

    public String getNotaFiscal () {
        return notaFiscal;
    }

    public void setNotaFiscal (String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getNumeroProtocolo () {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo (String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public Date getDataEnvioPagamento () {
        return dataEnvioPagamento;
    }

    public void setDataEnvioPagamento (Date dataEnvioPagamento) {
        this.dataEnvioPagamento = dataEnvioPagamento;
    }

    public Character getContaInativa () {
        return contaInativa;
    }

    public void setContaInativa (Character contaInativa) {
        this.contaInativa = contaInativa;
    }

    public LocalSercomtel getLocalSercomtel () {
        return localSercomtel;
    }

    public void setLocalSercomtel (LocalSercomtel localSercomtel) {
        this.localSercomtel = localSercomtel;
    }
}