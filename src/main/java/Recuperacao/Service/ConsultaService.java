package Recuperacao.Service;

import Recuperacao.Model.Consulta;
import Recuperacao.Repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final AuditoriaService auditoriaService;

    public ConsultaService(ConsultaRepository consultaRepository, AuditoriaService auditoriaService) {
        this.consultaRepository = consultaRepository;
        this.auditoriaService = auditoriaService;
    }

    // 🔹 Salvar ou atualizar consulta
    public Consulta salvar(Consulta consulta) {
        Consulta c = consultaRepository.save(consulta);
        auditoriaService.registrar("Atualização de Consulta ID " + c.getId());
        return c;
    }

    // 🔹 Listar todas as consultas
    public List<Consulta> listarTodos() {
        return consultaRepository.findAll();
    }

    // 🔹 Buscar consulta por ID
    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    // 🔹 Atualizar consulta
    public Consulta atualizar(Consulta consulta) {
        Consulta c = consultaRepository.save(consulta);
        auditoriaService.registrar("Atualização de Consulta ID " + c.getId());
        return c;
    }

    // 🔹 Deletar consulta por ID
    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
        auditoriaService.registrar("Exclusão de Consulta ID " + id);
    }

    // 🔹 Cancelar consulta
    public void cancelar(Consulta consulta) {
        consulta.setStatus("Cancelada");
        consultaRepository.save(consulta);
    }

    // 🔹 Confirmar consulta
    public void confirmar(Consulta consulta) {
        consulta.setStatus("Confirmada");
        consultaRepository.save(consulta);
    }

    // 🔹 Reagendar consulta
    public void reagendar(Consulta consulta, LocalDateTime novaDataHora) {
        consulta.setDataHora(novaDataHora);
        consulta.setStatus("Reagendada");
        consultaRepository.save(consulta);
    }

    // 🔹 Buscar consultas em um período específico (diário, semanal, mensal)
    public List<Consulta> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findByDataHoraBetween(inicio, fim);
    }
}
