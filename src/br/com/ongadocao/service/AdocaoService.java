package br.com.ongadocao.service;

import br.com.ongadocao.exception.AnimalIndisponivelException;
import br.com.ongadocao.exception.LimiteAdocoesException;
import br.com.ongadocao.model.*;
import br.com.ongadocao.repository.*;

import java.time.LocalDate;
import java.util.List;

public class AdocaoService {
    private AnimalRepository animalRepo;
    private AdotanteRepository adotanteRepo;
    private AdocaoRepository adocaoRepo;

    public AdocaoService(AnimalRepository ar, AdotanteRepository pr, AdocaoRepository or) {
        this.animalRepo = ar;
        this.adotanteRepo = pr;
        this.adocaoRepo = or;
    }

    public Adocao realizarAdocao(Long idAdotante, Long idAnimal) throws LimiteAdocoesException, AnimalIndisponivelException {
        Adotante adotante = adotanteRepo.findById(idAdotante);
        if (adotante == null) throw new IllegalArgumentException("Adotante não encontrado");
        Animal a = animalRepo.findById(idAnimal);
        if (a == null) throw new IllegalArgumentException("Animal não encontrado");

        if (adotante.getQtdAdocoesAtuais() >= 3) {
            throw new LimiteAdocoesException("Adotante já possui 3 animais adotados");
        }
        if (a.getStatus() != StatusAnimal.DISPONIVEL) {
            throw new AnimalIndisponivelException("Animal não está disponível");
        }

        // tudo ok
        a.setStatus(StatusAnimal.ADOTADO);
        animalRepo.save(a);

        adotante.incrementaAdocao();
        adotanteRepo.save(adotante);

        Adocao ad = new Adocao(null, idAnimal, idAdotante, LocalDate.now());
        adocaoRepo.save(ad);

        return ad;
    }

    public List<Adocao> listarPorAdotante(Long idAdotante) {
        return adocaoRepo.findByAdotanteId(idAdotante);
    }

    public List<Adocao> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return adocaoRepo.findByPeriodo(inicio, fim);
    }
}
