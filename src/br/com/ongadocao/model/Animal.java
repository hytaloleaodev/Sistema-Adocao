package br.com.ongadocao.model;

public abstract class Animal {
    protected Long id;
    protected String nome;
    protected int idade;
    protected String raca;
    protected StatusAnimal status;

    public Animal() {}

    public Animal(Long id, String nome, int idade, String raca, StatusAnimal status) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.status = status;
    }

    public abstract String emitirSom();

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public StatusAnimal getStatus() { return status; }
    public void setStatus(StatusAnimal status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Animal{id=%d, nome='%s', idade=%d, raca='%s', status=%s}",
                id, nome, idade, raca, status);
    }
}