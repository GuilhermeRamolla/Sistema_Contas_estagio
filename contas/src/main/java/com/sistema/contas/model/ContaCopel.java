package com.sistema.contas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="contas_copel", schema="sistema")
public class ContaCopel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer mes;

    private Integer ano;

    private LocalDate vencimento;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @NotNull
    @Column(name="kwh_ponta", precision = 10, scale = 2)
    private BigDecimal kwhPonta;

    @NotNull
    @Column(name="kwh_fora", precision = 10, scale = 2)
    private BigDecimal kwhFora;

    @NotNull
    @Column(name="kwh_total", precision = 10, scale = 2)
    private BigDecimal kwhTotal;

    @NotBlank
    @Column(name="numero_protocolo")
    private String numeroProtocolo;

    @Column(name="data_envio_pagamento")
    private LocalDate dataEnvioPagamento;

    @Column(name="conta_inativa")
    private Character contaInativa;

    @ManyToOne
    @JoinColumn(name="local_copel_id", nullable = false)
    private LocalCopel localCopel;

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

    public LocalDate getVencimento () {
        return vencimento;
    }

    public void setVencimento (LocalDate vencimento) {
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

    public BigDecimal getKwhPonta () {
        return kwhPonta;
    }

    public void setKwhPonta (BigDecimal kwhPonta) {
        this.kwhPonta = kwhPonta;
    }

    public BigDecimal getKwhFora () {
        return kwhFora;
    }

    public void setKwhFora (BigDecimal kwhFora) {
        this.kwhFora = kwhFora;
    }

    public BigDecimal getKwhTotal () {
        return kwhTotal;
    }

    public void setKwhTotal (BigDecimal kwhTotal) {
        this.kwhTotal = kwhTotal;
    }

    public String getNumeroProtocolo () {
        return numeroProtocolo;
    }

    public void setNumeroProtocolo (String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public LocalDate getDataEnvioPagamento () {
        return dataEnvioPagamento;
    }

    public void setDataEnvioPagamento (LocalDate dataEnvioPagamento) {
        this.dataEnvioPagamento = dataEnvioPagamento;
    }

    public Character getContaInativa () {
        return contaInativa;
    }

    public void setContaInativa (Character contaInativa) {
        this.contaInativa = contaInativa;
    }

    public LocalCopel getLocalCopel() {
        return localCopel;
    }

    public void setLocalCopel(LocalCopel localCopel) {
        this.localCopel = localCopel;
    }
}