package br.com.ongadocao.gui;

import br.com.ongadocao.model.Adotante;
import br.com.ongadocao.model.Animal;
import br.com.ongadocao.repository.AdotanteRepository;
import br.com.ongadocao.repository.AnimalRepository;
import br.com.ongadocao.service.AdocaoService;
import br.com.ongadocao.exception.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RealizarAdocaoDialog {
    public RealizarAdocaoDialog(JFrame parent, AdocaoService service, AdotanteRepository adotanteRepo, AnimalRepository animalRepo) {
        JDialog dialog = new JDialog(parent, "Realizar Adoção", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new GridLayout(0,2,6,6));

        dialog.add(new JLabel("Adotante:"));
        List<Adotante> adotantes = adotanteRepo.findAll();
        JComboBox<Adotante> cbAdotantes = new JComboBox<>(adotantes.toArray(new Adotante[0]));
        dialog.add(cbAdotantes);

        dialog.add(new JLabel("Animal disponível:"));
        List<Animal> disponiveis = animalRepo.findDisponiveis();
        JComboBox<Animal> cbAnimais = new JComboBox<>(disponiveis.toArray(new Animal[0]));
        dialog.add(cbAnimais);

        JButton btnOk = new JButton("Adotar");
        JButton btnCancel = new JButton("Cancelar");

        btnOk.addActionListener(e -> {
            Adotante selAd = (Adotante) cbAdotantes.getSelectedItem();
            Animal selAn = (Animal) cbAnimais.getSelectedItem();
            if (selAd == null || selAn == null) { JOptionPane.showMessageDialog(dialog, "Selecione os dois"); return; }
            try {
                service.realizarAdocao(selAd.getId(), selAn.getId());
                JOptionPane.showMessageDialog(dialog, "Adoção realizada!");
            } catch (LimiteAdocoesException | br.com.ongadocao.exception.AnimalIndisponivelException ex) {
                JOptionPane.showMessageDialog(dialog, "Erro: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro inesperado: " + ex.getMessage());
            }
            dialog.dispose();
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.add(btnOk);
        dialog.add(btnCancel);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
