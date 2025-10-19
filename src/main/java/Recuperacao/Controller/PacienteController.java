package Recuperacao.Controller;

import Recuperacao.Model.Paciente;
import Recuperacao.Service.PacienteService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Métodos de negócio - recebem dados da View
    public Paciente adicionarPaciente(String nome, String cpf, String email, String telefone) {
        Paciente p = new Paciente(nome, cpf, email, telefone);
        return pacienteService.salvar(p);
    }

    public List<Paciente> listarPacientes() {
        return pacienteService.listarTodos();
    }

    public boolean editarPaciente(Long id, String nome, String cpf, String email, String telefone) {
        Optional<Paciente> opt = pacienteService.buscarPorId(id);
        if (opt.isPresent()) {
            Paciente p = opt.get();
            if (nome != null && !nome.isBlank()) p.setNome(nome);
            if (cpf != null && !cpf.isBlank()) p.setCpf(cpf);
            if (email != null && !email.isBlank()) p.setEmail(email);
            if (telefone != null && !telefone.isBlank()) p.setTelefone(telefone);
            pacienteService.atualizar(p);
            return true;
        }
        return false;
    }

    public boolean deletarPaciente(Long id) {
        Optional<Paciente> opt = pacienteService.buscarPorId(id);
        if (opt.isPresent()) {
            pacienteService.deletar(id);
            return true;
        }
        return false;
    }

    public Optional<Paciente> buscarPacientePorId(Long id) {
        return pacienteService.buscarPorId(id);
    }
}
