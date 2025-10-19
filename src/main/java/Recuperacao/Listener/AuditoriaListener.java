package Recuperacao.Listener;

import jakarta.persistence.*;
import Recuperacao.Model.Auditoria;
import Recuperacao.Repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuditoriaListener {

    private static AuditoriaRepository auditoriaRepository;

    // Injeta o repository no static
    @Autowired
    public void setAuditoriaRepository(AuditoriaRepository repository) {
        AuditoriaListener.auditoriaRepository = repository;
    }

    @PrePersist
    public void prePersist(Object entity) {
        salvarAuditoria("Inserção", entity);
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        salvarAuditoria("Atualização", entity);
    }

    @PreRemove
    public void preRemove(Object entity) {
        salvarAuditoria("Exclusão", entity);
    }

    private void salvarAuditoria(String operacao, Object entity) {
        Auditoria auditoria = new Auditoria();
        auditoria.setOperacao(operacao + " de " + entity.getClass().getSimpleName());
        auditoria.setDataOperacao(LocalDateTime.now());

        // Salva no banco
        if (auditoriaRepository != null) {
            auditoriaRepository.save(auditoria);
        }
    }
}
