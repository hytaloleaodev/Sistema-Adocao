package br.com.ongadocao.gui;

import br.com.ongadocao.model.Animal;
import br.com.ongadocao.repository.AnimalRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListaAnimaisFrame {
    public ListaAnimaisFrame(AnimalRepository repo) {
        JFrame frame = new JFrame("Animais");
        frame.setSize(600, 300);

        String[] colunas = {"ID", "Tipo", "Nome", "Idade", "Raça", "Status"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Animal> animais = repo.findAll();
        for (Animal a : animais) {
            String tipo = a.getClass().getSimpleName();
            model.addRow(new Object[]{a.getId(), tipo, a.getNome(), a.getIdade(), a.getRaca(), a.getStatus()});
        }

        JTable table = new JTable(model);
        frame.add(new JScrollPane(table));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
