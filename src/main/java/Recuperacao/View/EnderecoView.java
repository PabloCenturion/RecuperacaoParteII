package Recuperacao.View;

import Recuperacao.Controller.EnderecoController;

import java.util.List;
import java.util.Scanner;

import Recuperacao.Model.Endereco;

public class EnderecoView {

    private final EnderecoController controller;
    private final Scanner sc = new Scanner(System.in);

    public EnderecoView(EnderecoController controller) {
        this.controller = controller;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== CRUD ENDEREÇOS ===");
            System.out.println("1. Adicionar Endereço");
            System.out.println("2. Listar Endereços");
            System.out.println("3. Editar Endereço");
            System.out.println("4. Deletar Endereço");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> adicionarEndereco();
                case 2 -> listarEnderecos();
                case 3 -> editarEndereco();
                case 4 -> deletarEndereco();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void adicionarEndereco() {
        System.out.print("ID do Paciente: ");
        Long pacienteId = Long.parseLong(sc.nextLine());

        System.out.print("Estado: ");
        String estado = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        System.out.print("Rua: ");
        String rua = sc.nextLine();
        System.out.print("Número: ");
        String numero = sc.nextLine();
        System.out.print("CEP: ");
        String cep = sc.nextLine();

        boolean sucesso = controller.adicionarEndereco(pacienteId, estado, cidade, rua, numero, cep);
        System.out.println(sucesso ? "Endereço adicionado!" : "Paciente não encontrado.");
    }

    private void listarEnderecos() {
        List<Endereco> enderecos = controller.listarEnderecos();
        if (enderecos.isEmpty()) {
            System.out.println("Nenhum endereço cadastrado.");
        } else {
            enderecos.forEach(e -> System.out.println(
                    "ID:" + e.getId() +
                            " | Paciente:" + e.getPaciente().getNome() +
                            " | Estado:" + e.getEstado() +
                            " | Cidade:" + e.getCidade() +
                            " | Rua:" + e.getRua() +
                            " | Número:" + e.getNumero() +
                            " | CEP:" + e.getCep()
            ));
        }
    }

    private void editarEndereco() {
        System.out.print("ID do Endereço: ");
        Long id = Long.parseLong(sc.nextLine());

        System.out.print("Novo Estado ou enter para manter: ");
        String estado = sc.nextLine();
        if (estado.isBlank()) estado = null;

        System.out.print("Nova Cidade ou enter para manter: ");
        String cidade = sc.nextLine();
        if (cidade.isBlank()) cidade = null;

        System.out.print("Nova Rua ou enter para manter: ");
        String rua = sc.nextLine();
        if (rua.isBlank()) rua = null;

        System.out.print("Novo Número ou enter para manter: ");
        String numero = sc.nextLine();
        if (numero.isBlank()) numero = null;

        System.out.print("Novo CEP ou enter para manter: ");
        String cep = sc.nextLine();
        if (cep.isBlank()) cep = null;

        boolean sucesso = controller.editarEndereco(id, estado, cidade, rua, numero, cep);
        System.out.println(sucesso ? "Endereço atualizado!" : "Endereço não encontrado.");
    }

    private void deletarEndereco() {
        System.out.print("ID do Endereço: ");
        Long id = Long.parseLong(sc.nextLine());

        boolean sucesso = controller.deletarEndereco(id);
        System.out.println(sucesso ? "Endereço deletado!" : "Endereço não encontrado.");
    }
}
