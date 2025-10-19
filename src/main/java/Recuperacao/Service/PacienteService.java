package Recuperacao.Service;

import Recuperacao.Model.Paciente;
import Recuperacao.Repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente salvar(Paciente paciente) {
        return (Paciente) pacienteRepository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente atualizar(Paciente paciente) {
        return (Paciente) pacienteRepository.save(paciente);
    }

    public void deletar(long id) {
        pacienteRepository.deleteById(id);
    }
}
