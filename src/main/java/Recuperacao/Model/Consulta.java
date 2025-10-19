package Recuperacao.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "consultas")
@EntityListeners(Recuperacao.Listener.AuditoriaListener.class) // Listener de auditoria
public class Consulta {

    // Getters e Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // melhor usar Long

    private LocalDateTime dataHora;

    private String status; // "Pendente", "Confirmada", "Cancelada", "Reagendada"

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Construtores
    public Consulta() {}

    public Consulta(LocalDateTime dataHora, String status, Paciente paciente) {
        this.dataHora = dataHora;
        this.status = status;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", status='" + status + '\'' +
                ", pacienteId=" + (paciente != null ? paciente.getId() : null) +
                '}';
    }
}
