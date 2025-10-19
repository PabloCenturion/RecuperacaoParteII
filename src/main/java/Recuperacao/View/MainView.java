package Recuperacao.View;

import Recuperacao.Controller.PacienteController;
import Recuperacao.Controller.EnderecoController;
import Recuperacao.Controller.ConsultaController;
import Recuperacao.Service.AuditoriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainView implements CommandLineRunner {   //esse comandLine faz com que o terminal fique ativo mesmo após iniciar a aplçicação

    private final PacienteController pacienteController;
    private final EnderecoController enderecoController;
    private final ConsultaController consultaController;
    private final AuditoriaService auditoriaService;

    private final Scanner sc = new Scanner(System.in);

    public MainView(PacienteController pacienteController,
                    EnderecoController enderecoController,
                    ConsultaController consultaController, AuditoriaService auditoriaService) {
        this.pacienteController = pacienteController;
        this.enderecoController = enderecoController;
        this.consultaController = consultaController;
        this.auditoriaService = auditoriaService;
    }

    @Override
    public void run(String... args) {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE CLÍNICA ===");
            System.out.println("1. Gerenciar Pacientes");
            System.out.println("2. Gerenciar Endereços");
            System.out.println("3. Gerenciar Consultas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> {
                    // Aqui chamamos a view do Paciennt
                    PacienteView pacienteView = new PacienteView(pacienteController);
                    pacienteView.exibirMenu();
                }
                case 2 -> {
                    // Aqui chamamos as respectivas viwes
                    EnderecoView enderecoView = new EnderecoView(enderecoController);
                    enderecoView.exibirMenu();
                }
                case 3 -> {
                        // Aqui chamamos as respectivas viwes
                        ConsultaView consultaView = new ConsultaView(consultaController);
                        consultaView.exibirMenu();
                }
                case 4 -> {
                    AuditoriaView auditoriaView = new AuditoriaView(auditoriaService);
                    auditoriaView.exibirMenu();
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

}
