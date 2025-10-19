package Recuperacao.View;

import Recuperacao.Controller.ConsultaController;
import Recuperacao.Model.Consulta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsultaView {

    private final ConsultaController controller;
    private final Scanner sc = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ConsultaView(ConsultaController controller) {
        this.controller = controller;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== CRUD CONSULTAS ===");
            System.out.println("1. Agendar Consulta");
            System.out.println("2. Listar Consultas");
            System.out.println("3. Editar Consulta");
            System.out.println("4. Cancelar Consulta");
            System.out.println("5. Confirmar Consulta");
            System.out.println("6. Reagendar Consulta");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> agendarConsulta();
                case 2 -> listarConsultas();
                case 3 -> editarConsulta();
                case 4 -> cancelarConsulta();
                case 5 -> confirmarConsulta();
                case 6 -> reagendarConsulta();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void agendarConsulta() {
        System.out.print("ID do Paciente: ");
        Long id = Long.parseLong(sc.nextLine());

        System.out.print("Data e hora (yyyy-MM-dd HH:mm): ");
        LocalDateTime dt = LocalDateTime.parse(sc.nextLine(), formatter);

        boolean sucesso = controller.agendarConsulta(id, dt);
        System.out.println(sucesso ? "Consulta agendada!" : "Paciente não encontrado.");
    }

    private void listarConsultas() {
        List<Consulta> consultas = controller.listarConsultas();
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
        } else {
            consultas.forEach(c -> System.out.println(
                    "ID:" + c.getId() +
                            " | Paciente:" + c.getPaciente().getNome() +
                            " | Data:" + c.getDataHora().format(formatter) +
                            " | Status:" + c.getStatus()
            ));
        }
    }

    private void editarConsulta() {
        System.out.print("ID da Consulta: ");
        Long id = Long.parseLong(sc.nextLine());

        System.out.print("Nova data e hora (yyyy-MM-dd HH:mm) ou enter para manter: ");
        String dtStr = sc.nextLine();
        LocalDateTime novaData = dtStr.isBlank() ? null : LocalDateTime.parse(dtStr, formatter);

        System.out.print("Novo status ou enter para manter: ");
        String status = sc.nextLine();
        if (status.isBlank()) status = null;

        boolean sucesso = controller.editarConsulta(id, novaData, status);
        System.out.println(sucesso ? "Consulta atualizada!" : "Consulta não encontrada.");
    }

    private void cancelarConsulta() {
        System.out.print("ID da Consulta: ");
        Long id = Long.parseLong(sc.nextLine());
        boolean sucesso = controller.cancelarConsulta(id);
        System.out.println(sucesso ? "Consulta cancelada!" : "Consulta não encontrada.");
    }

    private void confirmarConsulta() {
        System.out.print("ID da Consulta: ");
        Long id = Long.parseLong(sc.nextLine());
        boolean sucesso = controller.confirmarConsulta(id);
        System.out.println(sucesso ? "Consulta confirmada!" : "Consulta não encontrada.");
    }

    private void reagendarConsulta() {
        System.out.print("ID da Consulta: ");
        Long id = Long.parseLong(sc.nextLine());

        System.out.print("Nova data e hora (yyyy-MM-dd HH:mm): ");
        LocalDateTime novaData = LocalDateTime.parse(sc.nextLine(), formatter);

        boolean sucesso = controller.reagendarConsulta(id, novaData);
        System.out.println(sucesso ? "Consulta reagendada!" : "Consulta não encontrada.");
    }
}
