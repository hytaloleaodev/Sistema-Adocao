package br.com.ongadocao.model;

public class Gato extends Animal implements br.com.ongadocao.model.CuidadosEspeciais {

    public Gato() { super(); }

    public Gato(Long id, String nome, int idade, String raca, StatusAnimal status) {
        super(id, nome, idade, raca, status);
    }

    @Override
    public String emitirSom() {
        return "Miau";
    }

    @Override
    public String toString() {
        return "Gato " + super.toString();
    }

    // Interface
    @Override
    public String vacinar() {
        return "Vacinar conforme calendário (Gato)";
    }

    @Override
    public String vermifugar() {
        return "Vermifugar a cada 3 meses (Gato)";
    }
}