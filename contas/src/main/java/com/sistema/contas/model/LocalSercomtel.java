package com.sistema.contas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Locais_sercomtel", schema="sistema")
public class LocalSercomtel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String tipo;

    @NotBlank
    private String endereco;

    @NotBlank
    @Column(name="inscricao_sercomtel")
    private Integer inscricaoSercomtel;

    @NotBlank
    private String telefone;

    @NotBlank
    private String orgao;

    @OneToMany(mappedBy ="localSercomtel")
    private List<ContaSercomtel> contas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getInscricaoSercomtel() {
        return inscricaoSercomtel;
    }

    public void setInscricaoSercomtel(Integer inscricaoSercomtel) {
        this.inscricaoSercomtel = inscricaoSercomtel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }
}
