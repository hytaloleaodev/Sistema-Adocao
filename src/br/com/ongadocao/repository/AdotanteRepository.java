package br.com.ongadocao.repository;

import br.com.ongadocao.model.Adotante;
import br.com.ongadocao.util.CSVUtil;

import java.util.*;
import java.util.stream.Collectors;

public class AdotanteRepository {
    private Map<Long, Adotante> mapa = new HashMap<>();
    private String arquivo;

    public AdotanteRepository(String arquivoCsv) {
        this.arquivo = arquivoCsv;
        load();
    }

    private void load() {
        List<String[]> rows = CSVUtil.readAll(arquivo);
        for (String[] r : rows) {
            // formato: id,nome,telefone,qtdAdocoesAtuais
            if (r.length < 4) continue;
            Long id = Long.parseLong(r[0]);
            String nome = r[1];
            String telefone = r[2];
            int qtd = Integer.parseInt(r[3]);
            Adotante ad = new Adotante(id, nome, telefone, qtd);
            mapa.put(id, ad);
        }
    }

    private void save() {
        List<String> lines = mapa.values().stream().map(a ->
                String.join(",",
                        String.valueOf(a.getId()),
                        a.getNome(),
                        a.getTelefone() == null ? "" : a.getTelefone(),
                        String.valueOf(a.getQtdAdocoesAtuais())
                )
        ).collect(Collectors.toList());
        CSVUtil.writeAll(arquivo, lines);
    }

    public List<Adotante> findAll() { return new ArrayList<>(mapa.values()); }

    public Adotante findById(Long id) { return mapa.get(id); }

    public List<Adotante> findByNome(String nome) {
        return mapa.values().stream()
                .filter(a -> a.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Adotante save(Adotante a) {
        if (a.getId() == null) {
            long next = mapa.keySet().stream().mapToLong(Long::longValue).max().orElse(0) + 1;
            a.setId(next);
        }
        mapa.put(a.getId(), a);
        save();
        return a;
    }
    
    public void update(Adotante a) {
    mapa.put(a.getId(), a);
    save();
    }


    public void delete(Long id) {
        mapa.remove(id);
        save();
    }
}