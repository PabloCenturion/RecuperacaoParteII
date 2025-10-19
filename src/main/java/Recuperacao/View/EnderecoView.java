package Recuperacao.View;

import Recuperacao.Controller.EnderecoController;
import Recuperacao.Model.Endereco;

import java.util.List;
import java.util.Scanner;

public class EnderecoView {

    private final EnderecoController enderecoController;
    private final Scanner sc = new Scanner(System.in);

    public EnderecoView(EnderecoController enderecoController) {
        this.enderecoController = enderecoController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== CRUD ENDEREÇOS ===");
            System.out.println("1. Adicionar Endereço (usando ViaCep)");
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

        System.out.print("Digite o CEP: ");
        String cep = sc.nextLine();

        System.out.print("Número da residência: ");
        String numero = sc.nextLine();

        boolean sucesso = enderecoController.adicionarEndereco(pacienteId, cep, numero);
        if (sucesso) {
            System.out.println("✅ Endereço cadastrado com sucesso!");
            System.out.println();
        } else {
            System.out.println("❌ Não foi possível cadastrar o endereço.");
        }
    }

    private void listarEnderecos() {
        List<Endereco> enderecos = enderecoController.listarEnderecos();
        if (enderecos.isEmpty()) {
            System.out.println("Nenhum endereço encontrado.");
        } else {
            enderecos.forEach(e -> System.out.println(
                    "ID: " + e.getId() +
                            " | Paciente: " + e.getPaciente().getNome() +
                            " | Rua: " + e.getRua() +
                            " | Número: " + e.getNumero() +
                            " | Bairro: " + e.getBairro() +
                            " | Cidade: " + e.getCidade() +
                            " | Estado: " + e.getEstado() +
                            " | CEP: " + e.getCep()
            ));
        }
    }

    private void editarEndereco() {
        System.out.print("ID do Endereço: ");
        Long id = Long.parseLong(sc.nextLine());

        System.out.print("Novo Estado (ou enter para manter): ");
        String estado = sc.nextLine();
        if (estado.isBlank()) estado = null;

        System.out.print("Nova Cidade (ou enter para manter): ");
        String cidade = sc.nextLine();
        if (cidade.isBlank()) cidade = null;

        System.out.print("Nova Rua (ou enter para manter): ");
        String rua = sc.nextLine();
        if (rua.isBlank()) rua = null;

        System.out.print("Novo Número (ou enter para manter): ");
        String numero = sc.nextLine();
        if (numero.isBlank()) numero = null;

        System.out.print("Novo CEP (ou enter para manter): ");
        String cep = sc.nextLine();
        if (cep.isBlank()) cep = null;

        boolean sucesso = enderecoController.editarEndereco(id, estado, cidade, rua, numero, cep);
        System.out.println(sucesso ? "✅ Endereço atualizado!" : "❌ Endereço não encontrado.");
    }

    private void deletarEndereco() {
        System.out.print("ID do Endereço: ");
        Long id = Long.parseLong(sc.nextLine());
        boolean sucesso = enderecoController.deletarEndereco(id);
        System.out.println(sucesso ? "✅ Endereço deletado!" : "❌ Endereço não encontrado.");
    }
}
