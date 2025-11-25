package com.sistema.contas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="locais_sanepar", schema="sistema")
public class LocalSanepar implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String endereco;

    @NotBlank
    @Column(name="matricula_sanepar")
    private String matriculaSanepar;

    @NotBlank
    private String orgao;

    @OneToMany(mappedBy = "localSanepar")
    private List<ContaSanepar> contas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getMatriculaSanepar() {
        return matriculaSanepar;
    }

    public void setMatriculaSanepar(String matriculaSanepar) {
        this.matriculaSanepar = matriculaSanepar;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }
    
}
