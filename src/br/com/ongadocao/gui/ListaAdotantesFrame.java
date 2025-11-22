package br.com.ongadocao.gui;

import br.com.ongadocao.model.Adotante;
import br.com.ongadocao.repository.AdotanteRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListaAdotantesFrame {
    public ListaAdotantesFrame(AdotanteRepository repo) {
        JFrame frame = new JFrame("Adotantes");
        frame.setSize(500, 300);

        String[] colunas = {"ID", "Nome", "Telefone", "Qtd Adoções"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Adotante> lista = repo.findAll();
        for (Adotante a : lista) {
            model.addRow(new Object[]{a.getId(), a.getNome(), a.getTelefone(), a.getQtdAdocoesAtuais()});
        }

        JTable table = new JTable(model);
        frame.add(new JScrollPane(table));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
