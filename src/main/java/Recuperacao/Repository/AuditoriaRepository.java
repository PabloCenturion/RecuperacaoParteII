package Recuperacao.Repository;

import Recuperacao.Model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
    // JpaRepository já fornece todos os métodos de CRUD:
    // save(), findById(), findAll(), deleteById(), etc.
}
