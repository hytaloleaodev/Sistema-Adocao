package br.com.ongadocao.gui;

import br.com.ongadocao.model.Adotante;
import br.com.ongadocao.repository.AdotanteRepository;

import javax.swing.*;
import java.awt.*;

public class CadastroAdotanteDialog {
    public CadastroAdotanteDialog(JFrame parent, AdotanteRepository repo) {
        JDialog dialog = new JDialog(parent, "Cadastrar Adotante", true);
        dialog.setSize(350, 200);
        dialog.setLayout(new GridLayout(0, 2, 6, 6));

        dialog.add(new JLabel("Nome:"));
        JTextField tfNome = new JTextField();
        dialog.add(tfNome);

        dialog.add(new JLabel("Telefone:"));
        JTextField tfTel = new JTextField();
        dialog.add(tfTel);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> {
            String nome = tfNome.getText().trim();
            String tel = tfTel.getText().trim();
            Adotante ad = new Adotante(null, nome, tel, 0);
            repo.save(ad);
            JOptionPane.showMessageDialog(dialog, "Adotante cadastrado: " + ad.getNome());
            dialog.dispose();
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnSalvar);
        dialog.add(btnCancelar);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
