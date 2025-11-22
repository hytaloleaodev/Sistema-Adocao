package br.com.ongadocao.app;

import br.com.ongadocao.gui.MainGui;
import br.com.ongadocao.repository.AnimalRepository;
import br.com.ongadocao.repository.AdotanteRepository;
import br.com.ongadocao.repository.AdocaoRepository;

public class MainGuiStarter {
    public static void main(String[] args) {

        // Carrega os repositórios usando os CSV da pasta data
        AnimalRepository animalRepo = new AnimalRepository("data/animais.csv");
        AdotanteRepository adotanteRepo = new AdotanteRepository("data/adotantes.csv");
        AdocaoRepository adocaoRepo = new AdocaoRepository("data/adocoes.csv");

        // Inicia a GUI
        MainGui.startGui(animalRepo, adotanteRepo, adocaoRepo);
    }
}
