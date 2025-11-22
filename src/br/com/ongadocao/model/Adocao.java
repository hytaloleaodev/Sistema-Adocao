package br.com.ongadocao.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Adocao {
    private Long id;
    private Long idAnimal;
    private Long idAdotante;
    private LocalDate data;

    public Adocao() {}

    public Adocao(Long id, Long idAnimal, Long idAdotante, LocalDate data) {
        this.id = id;
        this.idAnimal = idAnimal;
        this.idAdotante = idAdotante;
        this.data = data;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdAnimal() { return idAnimal; }
    public void setIdAnimal(Long idAnimal) { this.idAnimal = idAnimal; }

    public Long getIdAdotante() { return idAdotante; }
    public void setIdAdotante(Long idAdotante) { this.idAdotante = idAdotante; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE;
        return String.format("Adocao{id=%d, idAnimal=%d, idAdotante=%d, data=%s}",
                id, idAnimal, idAdotante, data.format(fmt));
    }
}