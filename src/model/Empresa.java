package com.seuprojeto.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PoliticaAntirracista> politicas;

    public Empresa() {
    }

    public Empresa(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PoliticaAntirracista> getPoliticas() {
        return politicas;
    }

    public void setPoliticas(List<PoliticaAntirracista> politicas) {
        this.politicas = politicas;
    }

    @Override
    public String toString() {
        return "Empresa{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               '}';
    }
}
