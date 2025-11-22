package br.com.ongadocao.gui;

import br.com.ongadocao.model.*;
import br.com.ongadocao.repository.AnimalRepository;

import javax.swing.*;
import java.awt.*;

public class CadastroAnimalDialog {
    public CadastroAnimalDialog(JFrame parent, AnimalRepository repo) {
        JDialog dialog = new JDialog(parent, "Cadastrar Animal", true);
        dialog.setSize(350, 240);
        dialog.setLayout(new GridLayout(0, 2, 6, 6));

        dialog.add(new JLabel("Tipo (CACHORRO/GATO):"));
        JTextField tfTipo = new JTextField("CACHORRO");
        dialog.add(tfTipo);

        dialog.add(new JLabel("Nome:"));
        JTextField tfNome = new JTextField();
        dialog.add(tfNome);

        dialog.add(new JLabel("Idade:"));
        JTextField tfIdade = new JTextField();
        dialog.add(tfIdade);

        dialog.add(new JLabel("Raça:"));
        JTextField tfRaca = new JTextField();
        dialog.add(tfRaca);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> {
            String tipo = tfTipo.getText().trim();
            String nome = tfNome.getText().trim();
            int idade = 0;
            try { idade = Integer.parseInt(tfIdade.getText().trim()); } catch(Exception ex){}
            String raca = tfRaca.getText().trim();

            Animal a;
            if ("CACHORRO".equalsIgnoreCase(tipo)) {
                a = new Cachorro(null, nome, idade, raca, StatusAnimal.DISPONIVEL);
            } else {
                a = new Gato(null, nome, idade, raca, StatusAnimal.DISPONIVEL);
            }
            repo.save(a);
            JOptionPane.showMessageDialog(dialog, "Animal cadastrado: " + a.getNome());
            dialog.dispose();
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnSalvar);
        dialog.add(btnCancelar);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
