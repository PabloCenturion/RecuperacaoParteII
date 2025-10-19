package Recuperacao;

import Recuperacao.Controller.PacienteController;
import Recuperacao.Controller.EnderecoController;
import Recuperacao.Controller.ConsultaController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "Recuperacao")
@EntityScan(basePackages = "Recuperacao.Model")
@EnableJpaRepositories(basePackages = "Recuperacao.Repository")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}