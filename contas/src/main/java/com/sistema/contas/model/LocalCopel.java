package com.sistema.contas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="locais_copel", schema="sistema")
public class LocalCopel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String endereco;

    @NotBlank
    @Column(name="id_copel")
    private Integer idCopel;

    @NotBlank
    private String orgao;

    @OneToMany(mappedBy = "localCopel")
    private List<ContaCopel> contas;

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

    public Integer getIdCopel() {
        return idCopel;
    }

    public void setIdCopel(Integer idCopel) {
        this.idCopel = idCopel;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }
}
