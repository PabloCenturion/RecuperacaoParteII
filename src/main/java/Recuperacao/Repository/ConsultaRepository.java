package Recuperacao.Repository;

import Recuperacao.Model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPacienteId(Long pacienteId);
    List<Consulta> findByStatus(String status);
    List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}
