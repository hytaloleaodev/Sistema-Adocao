package br.com.ongadocao.repository;

import br.com.ongadocao.model.Adocao;
import br.com.ongadocao.util.CSVUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AdocaoRepository {
    private Map<Long, Adocao> mapa = new HashMap<>();
    private String arquivo;
    private DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE;

    public AdocaoRepository(String arquivoCsv) {
        this.arquivo = arquivoCsv;
        load();
    }

    private void load() {
        List<String[]> rows = CSVUtil.readAll(arquivo);
        for (String[] r : rows) {
            // formato: id,idAnimal,idAdotante,data(yyyy-MM-dd)
            if (r.length < 4) continue;
            Long id = Long.parseLong(r[0]);
            Long idAnimal = Long.parseLong(r[1]);
            Long idAdotante = Long.parseLong(r[2]);
            LocalDate data = LocalDate.parse(r[3], fmt);
            Adocao ad = new Adocao(id, idAnimal, idAdotante, data);
            mapa.put(id, ad);
        }
    }

    private void save() {
        List<String> lines = mapa.values().stream().map(a ->
                String.join(",",
                        String.valueOf(a.getId()),
                        String.valueOf(a.getIdAnimal()),
                        String.valueOf(a.getIdAdotante()),
                        a.getData().format(fmt)
                )
        ).collect(Collectors.toList());
        CSVUtil.writeAll(arquivo, lines);
    }

    public List<Adocao> findAll() { return new ArrayList<>(mapa.values()); }

    public Adocao save(Adocao a) {
        if (a.getId() == null) {
            long next = mapa.keySet().stream().mapToLong(Long::longValue).max().orElse(0) + 1;
            a.setId(next);
        }
        mapa.put(a.getId(), a);
        save();
        return a;
    }

    public List<Adocao> findByAdotanteId(Long idAdotante) {
        return mapa.values().stream()
                .filter(a -> a.getIdAdotante().equals(idAdotante))
                .collect(Collectors.toList());
    }

    public List<Adocao> findByPeriodo(LocalDate inicio, LocalDate fim) {
        return mapa.values().stream()
                .filter(a -> !a.getData().isBefore(inicio) && !a.getData().isAfter(fim))
                .collect(Collectors.toList());
    }
}