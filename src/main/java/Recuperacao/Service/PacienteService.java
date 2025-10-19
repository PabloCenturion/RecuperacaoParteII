package Recuperacao.Service;

import Recuperacao.Model.Paciente;
import Recuperacao.Repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final AuditoriaService auditoriaService;

    public PacienteService(PacienteRepository pacienteRepository, AuditoriaService auditoriaService) {
        this.pacienteRepository = pacienteRepository;
        this.auditoriaService = auditoriaService;
    }

    public Paciente salvar(Paciente paciente) {
        Paciente p = pacienteRepository.save(paciente);
        auditoriaService.registrar("Inserção de Paciente ID " + p.getId());
        return p;
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }


    public Paciente atualizar(Paciente paciente) {
        Paciente p = pacienteRepository.save(paciente);
        auditoriaService.registrar("Atualização de Paciente ID " + p.getId());
        return p;
    }

    public void deletar(long id) {
        pacienteRepository.deleteById(id);
        auditoriaService.registrar("Exclusão de Paciente ID " + id);
    }
    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

}
