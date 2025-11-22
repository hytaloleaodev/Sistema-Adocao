package br.com.ongadocao.app;

import br.com.ongadocao.model.*;
import br.com.ongadocao.repository.*;
import br.com.ongadocao.service.AdocaoService;
import br.com.ongadocao.util.CSVUtil;
import br.com.ongadocao.exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String ANIMAIS_CSV = "data/animais.csv";
    private static final String ADOTANTES_CSV = "data/adotantes.csv";
    private static final String ADCOES_CSV = "data/adocoes.csv";

    public static void main(String[] args) {
        AnimalRepository animalRepo = new AnimalRepository(ANIMAIS_CSV);
        AdotanteRepository adotanteRepo = new AdotanteRepository(ADOTANTES_CSV);
        AdocaoRepository adocaoRepo = new AdocaoRepository(ADCOES_CSV);
        AdocaoService service = new AdocaoService(animalRepo, adotanteRepo, adocaoRepo);

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n--- Sistema de Adoção ---");
            System.out.println("1. CRUD Animais");
            System.out.println("2. CRUD Adotantes");
            System.out.println("3. Realizar Adoção");
            System.out.println("4. Listar Adoções");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            String opt = sc.nextLine();

            switch (opt) {
                case "1":
                    menuAnimais(sc, animalRepo);
                    break;
                case "2":
                    menuAdotantes(sc, adotanteRepo);
                    break;
                case "3":
                    realizarAdocao(sc, service, adotanteRepo, animalRepo);
                    break;
                case "4":
                    listarAdocoes(sc, service, adocaoRepo, adotanteRepo, animalRepo);
                    break;
                case "0":
                    running = false;
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }

    private static void menuAnimais(Scanner sc, AnimalRepository repo) {
        boolean loop = true;
        while (loop) {
            System.out.println("\n--- Animais ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Listar disponíveis");
            System.out.println("3. Cadastrar");
            System.out.println("4. Atualizar");
            System.out.println("5. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            String o = sc.nextLine();
            switch (o) {
                case "1":
                    repo.findAll().forEach(System.out::println);
                    break;
                case "2":
                    repo.findDisponiveis().forEach(System.out::println);
                    break;
                case "3":
                    cadastrarAnimal(sc, repo);
                    break;
                case "4":
                    atualizarAnimal(sc, repo);
                    break;
                case "5":
                    System.out.print("ID do animal a remover: ");
                    Long idRem = Long.parseLong(sc.nextLine());
                    repo.delete(idRem);
                    System.out.println("Removido (se existia).");
                    break;
                case "0":
                    loop = false;
                    break;
                default:
                    System.out.println("inválido");
            }
        }
    }

    private static void cadastrarAnimal(Scanner sc, AnimalRepository repo) {
        System.out.print("Tipo (CACHORRO/GATO): ");
        String tipo = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Idade (anos): ");
        int idade = Integer.parseInt(sc.nextLine());
        System.out.print("Raça: ");
        String raca = sc.nextLine();
        Animal a;
        if ("CACHORRO".equalsIgnoreCase(tipo)) {
            a = new Cachorro(null, nome, idade, raca, StatusAnimal.DISPONIVEL);
        } else {
            a = new Gato(null, nome, idade, raca, StatusAnimal.DISPONIVEL);
        }
        repo.save(a);
        System.out.println("Animal cadastrado: " + a);
    }

    private static void atualizarAnimal(Scanner sc, AnimalRepository repo) {
        System.out.print("ID do animal: ");
        Long id = Long.parseLong(sc.nextLine());
        Animal a = repo.findById(id);
        if (a == null) {
            System.out.println("Não encontrado.");
            return;
        }
        System.out.print("Novo nome ("+a.getNome()+"): ");
        String nome = sc.nextLine();
        if (!nome.trim().isEmpty()) a.setNome(nome);
        System.out.print("Nova idade ("+a.getIdade()+"): ");
        String idadeS = sc.nextLine();
        if (!idadeS.trim().isEmpty()) a.setIdade(Integer.parseInt(idadeS));
        System.out.print("Nova raça ("+a.getRaca()+"): ");
        String raca = sc.nextLine();
        if (!raca.trim().isEmpty()) a.setRaca(raca);
        repo.save(a);
        System.out.println("Atualizado: " + a);
    }

    private static void menuAdotantes(Scanner sc, AdotanteRepository repo) {
        boolean loop = true;
        while (loop) {
            System.out.println("\n--- Adotantes ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Cadastrar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            String o = sc.nextLine();
            switch (o) {
                case "1":
                    repo.findAll().forEach(System.out::println);
                    break;
                case "2":
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();
                    Adotante ad = new Adotante(null, nome, tel, 0);
                    repo.save(ad);
                    System.out.println("Cadastrado: " + ad);
                    break;
                case "3":
                    System.out.print("ID do adotante: ");
                    Long id = Long.parseLong(sc.nextLine());
                    Adotante a = repo.findById(id);
                    if (a == null) { System.out.println("Não encontrado."); break; }
                    System.out.print("Novo nome ("+a.getNome()+"): ");
                    String n = sc.nextLine();
                    if (!n.trim().isEmpty()) a.setNome(n);
                    System.out.print("Novo telefone ("+a.getTelefone()+"): ");
                    String t = sc.nextLine();
                    if (!t.trim().isEmpty()) a.setTelefone(t);
                    repo.save(a);
                    System.out.println("Atualizado: " + a);
                    break;
                case "4":
                    System.out.print("ID do adotante a remover: ");
                    Long idRem = Long.parseLong(sc.nextLine());
                    repo.delete(idRem);
                    System.out.println("Removido (se existia).");
                    break;
                case "0":
                    loop = false;
                    break;
                default:
                    System.out.println("inválido");
            }
        }
    }

    private static void realizarAdocao(Scanner sc, AdocaoService service, AdotanteRepository adotanteRepo, AnimalRepository animalRepo) {
        try {
            System.out.print("ID do adotante: ");
            Long idAdotante = Long.parseLong(sc.nextLine());
            Adotante adotante = adotanteRepo.findById(idAdotante);
            if (adotante == null) { System.out.println("Adotante não encontrado."); return; }

            System.out.println("Animais disponíveis:");
            animalRepo.findDisponiveis().forEach(System.out::println);

            System.out.print("ID do animal a adotar: ");
            Long idAnimal = Long.parseLong(sc.nextLine());

            Adocao ad = service.realizarAdocao(idAdotante, idAnimal);
            System.out.println("Adoção realizada: " + ad);
        } catch (LimiteAdocoesException | AnimalIndisponivelException ex) {
            System.out.println("Erro na adoção: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    private static void listarAdocoes(Scanner sc, AdocaoService service, AdocaoRepository adocaoRepo,
                                     AdotanteRepository adotanteRepo, AnimalRepository animalRepo) {
        System.out.println("\n--- Listar Adoções ---");
        System.out.println("1. Todas");
        System.out.println("2. Por adotante");
        System.out.println("3. Por período");
        System.out.print("Opção: ");
        String op = sc.nextLine();
        DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE;
        switch (op) {
            case "1":
                adocaoRepo.findAll().forEach(System.out::println);
                break;
            case "2":
                System.out.print("ID do adotante: ");
                Long id = Long.parseLong(sc.nextLine());
                service.listarPorAdotante(id).forEach(System.out::println);
                break;
            case "3":
                try {
                    System.out.print("Data início (yyyy-MM-dd): ");
                    LocalDate inicio = LocalDate.parse(sc.nextLine(), fmt);
                    System.out.print("Data fim (yyyy-MM-dd): ");
                    LocalDate fim = LocalDate.parse(sc.nextLine(), fmt);
                    service.listarPorPeriodo(inicio, fim).forEach(System.out::println);
                } catch (Exception e) { System.out.println("Formato de data inválido."); }
                break;
            default:
                System.out.println("Opção inválida.");


        }
    }
}
