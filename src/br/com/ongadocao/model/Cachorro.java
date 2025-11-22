package br.com.ongadocao.model;

public class Cachorro extends Animal implements br.com.ongadocao.model.CuidadosEspeciais {

    public Cachorro() { super(); }

    public Cachorro(Long id, String nome, int idade, String raca, StatusAnimal status) {
        super(id, nome, idade, raca, status);
    }

    @Override
    public String emitirSom() {
        return "Au Au";
    }

    @Override
    public String toString() {
        return "Cachorro " + super.toString();
    }

    // Métodos da interface
    @Override
    public String vacinar() {
        return "Vacinar anualmente (Cachorro)";
    }

    @Override
    public String vermifugar() {
        return "Vermifugar a cada 3 meses (Cachorro)";
    }
}
