package br.com.ongadocao.gui;

import br.com.ongadocao.repository.*;
import br.com.ongadocao.service.AdocaoService;

import javax.swing.*;
import java.awt.*;

public class MainGui {
    private AnimalRepository animalRepo;
    private AdotanteRepository adotanteRepo;
    private AdocaoRepository adocaoRepo;
    private AdocaoService adocaoService;

    public MainGui(AnimalRepository ar, AdotanteRepository pr, AdocaoRepository or) {
        this.animalRepo = ar;
        this.adotanteRepo = pr;
        this.adocaoRepo = or;
        this.adocaoService = new AdocaoService(ar, pr, or);
        createAndShow();
    }

    private void createAndShow() {
        JFrame frame = new JFrame("Sistema de Adoção - GUI");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1, 6, 6));

        JButton btnListAnimais = new JButton("Listar Animais");
        JButton btnListAdotantes = new JButton("Listar Adotantes");
        JButton btnCadAnimal = new JButton("Cadastrar Animal");
        JButton btnCadAdotante = new JButton("Cadastrar Adotante");
        JButton btnAdotar = new JButton("Realizar Adoção");
        JButton btnAtualizarAnimal = new JButton("Atualizar Animal");
        JButton btnAtualizarAdotante = new JButton("Atualizar Adotante");
        JButton btnListAdocoes = new JButton("Listar Adoções");


        btnListAnimais.addActionListener(e -> new ListaAnimaisFrame(animalRepo));
        btnListAdotantes.addActionListener(e -> new ListaAdotantesFrame(adotanteRepo));
        btnCadAnimal.addActionListener(e -> new CadastroAnimalDialog(frame, animalRepo));
        btnCadAdotante.addActionListener(e -> new CadastroAdotanteDialog(frame, adotanteRepo));
        btnAdotar.addActionListener(e -> new RealizarAdocaoDialog(frame, adocaoService, adotanteRepo, animalRepo));
        btnAtualizarAnimal.addActionListener(e -> new AtualizarAnimalDialog(frame, animalRepo));
        btnAtualizarAdotante.addActionListener(e -> new AtualizarAdotanteDialog(frame, adotanteRepo));
        btnListAdocoes.addActionListener(e -> new ListaAdocoesFrame(adocaoRepo, adotanteRepo, animalRepo));


        frame.add(btnListAnimais);
        frame.add(btnListAdotantes);
        frame.add(btnCadAnimal);
        frame.add(btnCadAdotante);
        frame.add(btnAdotar);
        frame.add(btnAtualizarAnimal);
        frame.add(btnAtualizarAdotante);
        frame.add(btnListAdocoes);


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Método de conveniência para iniciar a GUI a partir do Main
    public static void startGui(AnimalRepository ar, AdotanteRepository pr, AdocaoRepository or) {
        SwingUtilities.invokeLater(() -> new MainGui(ar, pr, or));
    }
}
