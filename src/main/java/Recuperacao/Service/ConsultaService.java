package Recuperacao.Service;

import Recuperacao.Model.Consulta;
import Recuperacao.Repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta salvar(Consulta consulta) {
        return (Consulta) consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodos() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(long id) {
        return consultaRepository.findById(id);
    }

    public Consulta atualizar(Consulta consulta) {
        return (Consulta) consultaRepository.save(consulta);
    }

    public void deletar(long id) {
        consultaRepository.deleteById(id);
    }

    // Métodos extras de status
    public void cancelar(Consulta consulta) {
        consulta.setStatus("Cancelada");
        consultaRepository.save(consulta);
    }

    public void confirmar(Consulta consulta) {
        consulta.setStatus("Confirmada");
        consultaRepository.save(consulta);
    }
}
