package br.com.ongadocao.repository;

import br.com.ongadocao.model.*;
import br.com.ongadocao.util.CSVUtil;

import java.util.*;
import java.util.stream.Collectors;

public class AnimalRepository {
    private Map<Long, Animal> mapa = new HashMap<>();
    private String arquivo;

    public AnimalRepository(String arquivoCsv) {
        this.arquivo = arquivoCsv;
        load();
    }

    private void load() {
        List<String[]> rows = CSVUtil.readAll(arquivo);
        for (String[] r : rows) {
            // formato: id,tipo,nome,idade,raca,status
            if (r.length < 6) continue;
            Long id = Long.parseLong(r[0]);
            String tipo = r[1];
            String nome = r[2];
            int idade = Integer.parseInt(r[3]);
            String raca = r[4];
            StatusAnimal status = StatusAnimal.valueOf(r[5]);

            Animal a;
            if ("CACHORRO".equalsIgnoreCase(tipo)) {
                a = new Cachorro(id, nome, idade, raca, status);
            } else {
                a = new Gato(id, nome, idade, raca, status);
            }
            mapa.put(id, a);
        }
    }

    private void save() {
        List<String> lines = mapa.values().stream().map(a -> {
            String tipo = (a instanceof Cachorro) ? "CACHORRO" : "GATO";
            return String.join(",",
                    String.valueOf(a.getId()),
                    tipo,
                    a.getNome(),
                    String.valueOf(a.getIdade()),
                    a.getRaca(),
                    a.getStatus().name()
            );
        }).collect(Collectors.toList());
        CSVUtil.writeAll(arquivo, lines);
    }

    public List<Animal> findAll() { return new ArrayList<>(mapa.values()); }

    public Animal findById(Long id) { return mapa.get(id); }

    public List<Animal> findByNome(String nome) {
        return mapa.values().stream()
                .filter(a -> a.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Animal save(Animal a) {
        if (a.getId() == null) {
            long next = mapa.keySet().stream().mapToLong(Long::longValue).max().orElse(0) + 1;
            a.setId(next);
        }
        mapa.put(a.getId(), a);
        save();
        return a;
    }

    public void update(Animal a) {
        mapa.put(a.getId(), a);
        save();
    }

    public void delete(Long id) {
        mapa.remove(id);
        save();
    }

    public List<Animal> findDisponiveis() {
        return mapa.values().stream()
                .filter(a -> a.getStatus() == StatusAnimal.DISPONIVEL)
                .collect(Collectors.toList());
    }
}
