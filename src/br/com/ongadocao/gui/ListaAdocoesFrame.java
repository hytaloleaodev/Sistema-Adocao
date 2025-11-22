package br.com.ongadocao.gui;

import br.com.ongadocao.model.Adocao;
import br.com.ongadocao.model.Adotante;
import br.com.ongadocao.model.Animal;
import br.com.ongadocao.repository.AdocaoRepository;
import br.com.ongadocao.repository.AdotanteRepository;
import br.com.ongadocao.repository.AnimalRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListaAdocoesFrame {

    public ListaAdocoesFrame(AdocaoRepository adocaoRepo,
                             AdotanteRepository adotanteRepo,
                             AnimalRepository animalRepo) {

        JFrame frame = new JFrame("Adoções Realizadas");
        frame.setSize(700, 300);

        String[] colunas = {"ID Adoção", "Adotante", "Animal", "Data"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Adocao> adocoes = adocaoRepo.findAll();
        for (Adocao a : adocoes) {
            Adotante adotante = adotanteRepo.findById(a.getIdAdotante());
            Animal animal = animalRepo.findById(a.getIdAnimal());

            model.addRow(new Object[]{
                    a.getId(),
                    adotante != null ? adotante.getNome() : "Desconhecido",
                    animal != null ? animal.getNome() : "Desconhecido",
                    a.getData()
            });
        }

        JTable table = new JTable(model);
        frame.add(new JScrollPane(table));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
