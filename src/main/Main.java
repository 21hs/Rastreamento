package main;

import entities.Usuario;
import entities.Encomenda;
import entities.Status;
import services.UsuarioServiceImpl;
import services.EncomendaServiceImpl;
import services.StatusServiceImpl;
import interfaces.UsuarioService;
import EncomendaRepository;
import StatusRepository;
import UsuarioRepository;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioServiceImpl();
        EncomendaServiceImpl encomendaService = new EncomendaServiceImpl();
        StatusServiceImpl statusService = new StatusServiceImpl();

        while (true) {
            // Menu interativo
            System.out.println("\n=== Menu ===");
            System.out.println("1. Criar novo usuário");
            System.out.println("2. Buscar usuário");
            System.out.println("3. Listar todos os usuários");
            System.out.println("4. Criar nova encomenda");
            System.out.println("5. Adicionar status à encomenda");
            System.out.println("6. Buscar status por encomenda");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    // Criar novo usuário
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o e-mail do usuário: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite o telefone do usuário: ");
                    String telefone = scanner.nextLine();
                    
                    Usuario novoUsuario = new Usuario(nome, email, telefone);
                    usuarioService.criarUsuario(novoUsuario);
                    System.out.println("Usuário criado com sucesso!");
                    break;

                case 2:
                    // Buscar usuário
                    System.out.print("Digite o ID do usuário: ");
                    int usuarioId = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    Usuario usuario = usuarioService.buscarUsuario(usuarioId);
                    if (usuario != null) {
                        System.out.println("Usuário encontrado: " + usuario.getNome());
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 3:
                    // Listar todos os usuários
                    List<Usuario> usuarios = usuarioService.listarUsuarios();
                    System.out.println("Lista de usuários:");
                    usuarios.forEach(u -> System.out.println("Usuário: " + u.getNome()));
                    break;

                case 4:
                    // Criar nova encomenda
                    System.out.print("Digite o código de rastreamento da encomenda: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Digite o ID do usuário associado à encomenda: ");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    Encomenda novaEncomenda = new Encomenda(codigo, new Date(), idUsuario);
                    encomendaService.criarEncomenda(novaEncomenda);
                    System.out.println("Encomenda criada com sucesso!");
                    break;

                case 5:
                    // Adicionar status à encomenda
                    System.out.print("Digite o ID da encomenda: ");
                    int idEncomenda = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    System.out.print("Digite o status da encomenda: ");
                    String statusDescricao = scanner.nextLine();
                    Status novoStatus = new Status(idEncomenda, statusDescricao, new Date());
                    statusService.salvarStatus(novoStatus);
                    System.out.println("Status adicionado com sucesso!");
                    break;

                case 6:
                    // Buscar status por encomenda
                    System.out.print("Digite o ID da encomenda para buscar os status: ");
                    int encomendaId = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    List<Status> statusList = statusService.buscarStatusPorEncomenda(encomendaId);
                    if (statusList.isEmpty()) {
                        System.out.println("Nenhum status encontrado para a encomenda.");
                    } else {
                        statusList.forEach(s -> System.out.println("Status da encomenda: " + s.getStatus()));
                    }
                    break;

                case 0:
                    // Sair
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
