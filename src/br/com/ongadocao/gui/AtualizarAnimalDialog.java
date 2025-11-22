package br.com.ongadocao.gui;

import br.com.ongadocao.model.*;
import br.com.ongadocao.repository.AnimalRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AtualizarAnimalDialog {

    public AtualizarAnimalDialog(JFrame parent, AnimalRepository repo) {
        JDialog dialog = new JDialog(parent, "Atualizar Animal", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2, 6, 6));

        dialog.add(new JLabel("Selecione o Animal:"));
        List<Animal> animais = repo.findAll();
        JComboBox<Animal> cbAnimais = new JComboBox<>(animais.toArray(new Animal[0]));
        dialog.add(cbAnimais);

        dialog.add(new JLabel("Tipo (CACHORRO/GATO):"));
        JTextField tfTipo = new JTextField();
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

        // Quando mudar seleção, preencher os dados
        cbAnimais.addActionListener(e -> {
            Animal sel = (Animal) cbAnimais.getSelectedItem();
            if (sel != null) {
                tfTipo.setText(sel instanceof Cachorro ? "CACHORRO" : "GATO");
                tfNome.setText(sel.getNome());
                tfIdade.setText(String.valueOf(sel.getIdade()));
                tfRaca.setText(sel.getRaca());
            }
        });

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> {
            Animal sel = (Animal) cbAnimais.getSelectedItem();
            if (sel == null) return;

            String tipo = tfTipo.getText().trim();
            String nome = tfNome.getText().trim();
            int idade = Integer.parseInt(tfIdade.getText().trim());
            String raca = tfRaca.getText().trim();

            Animal atualizado;

            if ("CACHORRO".equalsIgnoreCase(tipo)) {
                atualizado = new Cachorro(sel.getId(), nome, idade, raca, sel.getStatus());
            } else {
                atualizado = new Gato(sel.getId(), nome, idade, raca, sel.getStatus());
            }

            repo.update(atualizado);
            JOptionPane.showMessageDialog(dialog, "Animal atualizado com sucesso!");
            dialog.dispose();
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.add(btnSalvar);
        dialog.add(btnCancelar);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
