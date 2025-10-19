package Recuperacao.View;

import Recuperacao.Model.Auditoria;
import Recuperacao.Service.AuditoriaService;

import java.util.List;
import java.util.Scanner;

public class AuditoriaView {

    private final AuditoriaService auditoriaService;
    private final Scanner sc = new Scanner(System.in);

    public AuditoriaView(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU AUDITORIA ===");
            System.out.println("1. Listar atividades");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> listarAtividades();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void listarAtividades() {
        List<Auditoria> registros = auditoriaService.listarTodos();
        if (registros.isEmpty()) {
            System.out.println("Nenhuma atividade registrada.");
        } else {
            System.out.println("\n--- Atividades Registradas ---");
            registros.forEach(a -> System.out.println(
                    "ID:" + a.getId() +
                            " | Operação:" + a.getOperacao() +
                            " | Data:" + a.getDataOperacao()
            ));
        }
    }
}
