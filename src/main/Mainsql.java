package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Mainsql {
    private static ArrayList<mainsql> registros = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1; // Simula IDs automáticos

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Adicionar Registro");
            System.out.println("2. Listar Registros");
            System.out.println("3. Atualizar Registro");
            System.out.println("4. Remover Registro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado

            switch (opcao) {
                case 1:
                    adicionarRegistro();
                    break;
                case 2:
                    listarRegistros();
                    break;
                case 3:
                    atualizarRegistro();
                    break;
                case 4:
                    removerRegistro();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void adicionarRegistro() {
        System.out.println("\n--- ADICIONAR REGISTRO ---");
        System.out.print("Código de Rastreio: ");
        String codigoRastreio = scanner.nextLine();

        System.out.print("Data de Envio (dd/MM/yyyy): ");
        String dataEnvioStr = scanner.nextLine();
        Date dataEnvio = null;
        try {
            dataEnvio = new SimpleDateFormat("dd/MM/yyyy").parse(dataEnvioStr);
        } catch (ParseException e) {
            System.out.println("Data inválida! Registro não adicionado.");
            return;
        }

        System.out.print("ID do Usuário: ");
        int idUsuario = scanner.nextInt();

        mainsql registro = new mainsql(codigoRastreio, dataEnvio, idUsuario);
        registro.setId(idCounter++);
        registros.add(registro);

        System.out.println("Registro adicionado com sucesso!");
    }

    private static void listarRegistros() {
        System.out.println("\n--- LISTA DE REGISTROS ---");
        if (registros.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            for (mainsql registro : registros) {
                System.out.println(registro);
            }
        }
    }

    private static void atualizarRegistro() {
        System.out.println("\n--- ATUALIZAR REGISTRO ---");
        System.out.print("Digite o ID do registro que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do teclado

        mainsql registro = buscarRegistroPorId(id);
        if (registro == null) {
            System.out.println("Registro não encontrado.");
            return;
        }

        System.out.print("Novo Código de Rastreio (atual: " + registro.getCodigoRastreio() + "): ");
        String novoCodigoRastreio = scanner.nextLine();
        if (!novoCodigoRastreio.isEmpty()) {
            registro.setCodigoRastreio(novoCodigoRastreio);
        }

        System.out.print("Nova Data de Envio (dd/MM/yyyy, atual: " +
                new SimpleDateFormat("dd/MM/yyyy").format(registro.getDataEnvio()) + "): ");
        String novaDataEnvioStr = scanner.nextLine();
        if (!novaDataEnvioStr.isEmpty()) {
            try {
                Date novaDataEnvio = new SimpleDateFormat("dd/MM/yyyy").parse(novaDataEnvioStr);
                registro.setDataEnvio(novaDataEnvio);
            } catch (ParseException e) {
                System.out.println("Data inválida! Alteração ignorada.");
            }
        }

        System.out.print("Novo ID do Usuário (atual: " + registro.getIdUsuario() + "): ");
        String novoIdUsuarioStr = scanner.nextLine();
        if (!novoIdUsuarioStr.isEmpty()) {
            int novoIdUsuario = Integer.parseInt(novoIdUsuarioStr);
            registro.setIdUsuario(novoIdUsuario);
        }

        System.out.println("Registro atualizado com sucesso!");
    }

    private static void removerRegistro() {
        System.out.println("\n--- REMOVER REGISTRO ---");
        System.out.print("Digite o ID do registro que deseja remover: ");
        int id = scanner.nextInt();

        mainsql registro = buscarRegistroPorId(id);
        if (registro == null) {
            System.out.println("Registro não encontrado.");
            return;
        }

        registros.remove(registro);
        System.out.println("Registro removido com sucesso!");
    }

    private static mainsql buscarRegistroPorId(int id) {
        for (mainsql registro : registros) {
            if (registro.getId() == id) {
                return registro;
            }
        }
        return null;
    }
}
