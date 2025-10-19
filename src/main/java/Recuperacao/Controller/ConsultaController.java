package Recuperacao.Controller;

import Recuperacao.Model.Consulta;
import Recuperacao.Model.Paciente;
import Recuperacao.Service.ConsultaService;
import Recuperacao.Service.PacienteService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ConsultaController {

    private final ConsultaService consultaService;
    private final PacienteService pacienteService;

    public ConsultaController(ConsultaService consultaService, PacienteService pacienteService) {
        this.consultaService = consultaService;
        this.pacienteService = pacienteService;
    }

    // Métodos de negócio - recebem dados da View
    public boolean agendarConsulta(Long pacienteId, LocalDateTime dataHora) {
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(pacienteId);
        if (optPaciente.isPresent()) {
            Consulta c = new Consulta();
            c.setPaciente(optPaciente.get());
            c.setDataHora(dataHora);
            c.setStatus("PENDENTE");
            consultaService.salvar(c);
            return true;
        }
        return false;
    }

    public List<Consulta> listarConsultas() {
        return consultaService.listarTodos();
    }

    public boolean editarConsulta(Long id, LocalDateTime novaDataHora, String novoStatus) {
        Optional<Consulta> opt = consultaService.buscarPorId(id);
        if (opt.isPresent()) {
            Consulta c = opt.get();
            if (novaDataHora != null) c.setDataHora(novaDataHora);
            if (novoStatus != null && !novoStatus.isBlank()) c.setStatus(novoStatus);
            consultaService.atualizar(c);
            return true;
        }
        return false;
    }

    public boolean cancelarConsulta(Long id) {
        Optional<Consulta> opt = consultaService.buscarPorId(id);
        if (opt.isPresent()) {
            consultaService.cancelar(opt.get());
            return true;
        }
        return false;
    }

    public boolean confirmarConsulta(Long id) {
        Optional<Consulta> opt = consultaService.buscarPorId(id);
        if (opt.isPresent()) {
            consultaService.confirmar(opt.get());
            return true;
        }
        return false;
    }

    public boolean reagendarConsulta(Long id, LocalDateTime novaDataHora) {
        Optional<Consulta> opt = consultaService.buscarPorId(id);
        if (opt.isPresent()) {
            Consulta c = opt.get();
            c.setDataHora(novaDataHora);
            c.setStatus("PENDENTE");
            consultaService.atualizar(c);
            return true;
        }
        return false;
    }
}
