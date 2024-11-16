package com.seuprojeto.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "politicas_antirracistas")
public class PoliticaAntirracista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    // Construtor padrão
    public PoliticaAntirracista() {
    }

    // Construtor com parâmetros
    public PoliticaAntirracista(String descricao, Empresa empresa) {
        this.descricao = descricao;
        this.empresa = empresa;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    // Método toString para exibir informações da política
    @Override
    public String toString() {
        return "PoliticaAntirracista{" +
               "id=" + id +
               ", descricao='" + descricao + '\'' +
               ", empresa=" + (empresa != null ? empresa.getNome() : "Nenhuma") +
               '}';
    }

    // Métodos equals e hashCode para comparação
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoliticaAntirracista that = (PoliticaAntirracista) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(descricao, that.descricao) &&
               Objects.equals(empresa, that.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, empresa);
    }
}
