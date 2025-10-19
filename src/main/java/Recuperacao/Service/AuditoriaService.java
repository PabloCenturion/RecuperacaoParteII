package Recuperacao.Service;

import Recuperacao.Model.Auditoria;
import Recuperacao.Repository.AuditoriaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public AuditoriaService(AuditoriaRepository auditoriaRepository) {
        this.auditoriaRepository = auditoriaRepository;
    }

    public void registrar(String operacao) {
        Auditoria auditoria = new Auditoria(operacao, LocalDateTime.now());
        auditoriaRepository.save(auditoria);
    }
    public void registrarOperacao(String operacao) {
        Auditoria auditoria = new Auditoria(operacao, LocalDateTime.now());
        auditoriaRepository.save(auditoria);
    }

    public List<Auditoria> listarTodos() {
        return auditoriaRepository.findAll();
    }
}
