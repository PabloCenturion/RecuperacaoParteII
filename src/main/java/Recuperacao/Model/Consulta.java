package Recuperacao.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime dataHora;
    private String status; // "Pendente", "Confirmada", "Cancelada"

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

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
}
