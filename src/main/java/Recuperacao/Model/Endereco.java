package Recuperacao.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "enderecos")
@EntityListeners(Recuperacao.Listener.AuditoriaListener.class) // Listener de auditoria
public class Endereco implements Serializable {

    // Getters e Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // melhor usar Long para IDs

    private String estado;
    private String cidade;
    private String rua;
    private String numero;
    private String bairro;
    private String cep;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Construtores
    public Endereco() {}

    public Endereco(String estado, String cidade, String rua, String numero, String bairro, String cep, Paciente paciente) {
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", pacienteId=" + (paciente != null ? paciente.getId() : null) +
                '}';
    }
}
