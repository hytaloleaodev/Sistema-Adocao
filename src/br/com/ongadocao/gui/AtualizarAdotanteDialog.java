package br.com.ongadocao.gui;

import br.com.ongadocao.model.Adotante;
import br.com.ongadocao.repository.AdotanteRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AtualizarAdotanteDialog {

    public AtualizarAdotanteDialog(JFrame parent, AdotanteRepository repo) {
        JDialog dialog = new JDialog(parent, "Atualizar Adotante", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new GridLayout(0, 2, 6, 6));

        dialog.add(new JLabel("Selecione o Adotante:"));
        List<Adotante> lista = repo.findAll();
        JComboBox<Adotante> cbAdotantes =
                new JComboBox<>(lista.toArray(new Adotante[0]));
        dialog.add(cbAdotantes);

        dialog.add(new JLabel("Nome:"));
        JTextField tfNome = new JTextField();
        dialog.add(tfNome);

        dialog.add(new JLabel("Telefone:"));
        JTextField tfTel = new JTextField();
        dialog.add(tfTel);

        // Preencher ao selecionar
        cbAdotantes.addActionListener(e -> {
            Adotante sel = (Adotante) cbAdotantes.getSelectedItem();
            if (sel != null) {
                tfNome.setText(sel.getNome());
                tfTel.setText(sel.getTelefone());
            }
        });

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> {
            Adotante sel = (Adotante) cbAdotantes.getSelectedItem();
            if (sel == null) return;

            String nome = tfNome.getText().trim();
            String tel = tfTel.getText().trim();

            Adotante atualizado =
                    new Adotante(sel.getId(), nome, tel, sel.getQtdAdocoesAtuais());

            repo.update(atualizado);
            JOptionPane.showMessageDialog(dialog, "Adotante atualizado com sucesso!");
            dialog.dispose();
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnSalvar);
        dialog.add(btnCancelar);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
