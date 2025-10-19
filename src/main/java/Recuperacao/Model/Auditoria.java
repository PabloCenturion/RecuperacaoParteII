package Recuperacao.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Auditoria {

    // Getters e Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operacao;

    private LocalDateTime dataOperacao;

    public Auditoria() {}

    public Auditoria(String operacao, LocalDateTime dataOperacao) {
        this.operacao = operacao;
        this.dataOperacao = dataOperacao;
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "id=" + id +
                ", operacao='" + operacao + '\'' +
                ", dataOperacao=" + dataOperacao +
                '}';
    }
}
