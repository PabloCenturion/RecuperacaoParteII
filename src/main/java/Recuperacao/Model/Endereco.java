package Recuperacao.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String estado;
    private String cidade;
    private String rua;
    private String numero;
    private String cep;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Construtores
    public Endereco() {}

    public Endereco(String estado, String cidade, String rua, String numero, String cep, Paciente paciente) {
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.paciente = paciente;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
}
