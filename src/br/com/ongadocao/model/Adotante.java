package br.com.ongadocao.model;

import java.util.ArrayList;
import java.util.List;

public class Adotante {
    private Long id;
    private String nome;
    private String telefone;
    private int qtdAdocoesAtuais;

    // Sobre carga de construtores
    public Adotante() {}

    public Adotante(Long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.qtdAdocoesAtuais = 0;
    }

    public Adotante(Long id, String nome, String telefone, int qtdAdocoesAtuais) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.qtdAdocoesAtuais = qtdAdocoesAtuais;
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public int getQtdAdocoesAtuais() { return qtdAdocoesAtuais; }
    public void setQtdAdocoesAtuais(int qtdAdocoesAtuais) { this.qtdAdocoesAtuais = qtdAdocoesAtuais; }

    public void incrementaAdocao() { this.qtdAdocoesAtuais++; }
    public void decrementaAdocao() { if (this.qtdAdocoesAtuais > 0) this.qtdAdocoesAtuais--; }

    @Override
    public String toString() {
        return String.format("Adotante{id=%d, nome='%s', telefone='%s', qtdAdocoesAtuais=%d}",
                id, nome, telefone, qtdAdocoesAtuais);
    }
}