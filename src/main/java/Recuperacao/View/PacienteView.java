package Recuperacao.View;

import Recuperacao.Controller.PacienteController;
import Recuperacao.Model.Paciente;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PacienteView {

    private final PacienteController pacienteController;

    private final Scanner sc = new Scanner(System.in);

    public PacienteView(PacienteController controller) {
        this.pacienteController = controller;
    }


    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== CRUD PACIENTES ===");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Editar Paciente");
            System.out.println("4. Deletar Paciente");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> adicionarPaciente();
                case 2 -> listarPacientes();
                case 3 -> editarPaciente();
                case 4 -> deletarPaciente();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void adicionarPaciente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        pacienteController.adicionarPaciente(nome, cpf, email, telefone);
        System.out.println("Paciente adicionado!");
    }

    private void listarPacientes() {
        List<Paciente> pacientes = pacienteController.listarPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente encontrado.");
        } else {
            pacientes.forEach(p -> System.out.println(
                    "ID:" + p.getId() +
                            " | Nome:" + p.getNome() +
                            " | CPF:" + p.getCpf() +
                            " | Email:" + p.getEmail() +
                            " | Telefone:" + p.getTelefone()
            ));
        }
    }

    private void editarPaciente() {
        System.out.print("ID do Paciente: ");
        Long id = Long.parseLong(sc.nextLine());

        System.out.print("Novo nome ou enter para manter: ");
        String nome = sc.nextLine();
        if (nome.isBlank()) nome = null;

        System.out.print("Novo CPF ou enter para manter: ");
        String cpf = sc.nextLine();
        if (cpf.isBlank()) cpf = null;

        System.out.print("Novo Email ou enter para manter: ");
        String email = sc.nextLine();
        if (email.isBlank()) email = null;

        System.out.print("Novo Telefone ou enter para manter: ");
        String telefone = sc.nextLine();
        if (telefone.isBlank()) telefone = null;

        boolean sucesso = pacienteController.editarPaciente(id, nome, cpf, email, telefone);
        System.out.println(sucesso ? "Paciente atualizado!" : "Paciente não encontrado.");
    }

    private void deletarPaciente() {
        System.out.print("ID do Paciente: ");
        Long id = Long.parseLong(sc.nextLine());
        boolean sucesso = pacienteController.deletarPaciente(id);
        System.out.println(sucesso ? "Paciente deletado!" : "Paciente não encontrado.");
    }
}
