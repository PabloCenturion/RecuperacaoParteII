package Recuperacao.Controller;

import Recuperacao.Model.Endereco;
import Recuperacao.Model.Paciente;
import Recuperacao.Service.EnderecoService;
import Recuperacao.Service.PacienteService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final PacienteService pacienteService;

    public EnderecoController(EnderecoService enderecoService, PacienteService pacienteService) {
        this.enderecoService = enderecoService;
        this.pacienteService = pacienteService;
    }

    // Adiciona um endereço vinculado a um paciente
    public boolean adicionarEndereco(Long pacienteId, String estado, String cidade, String rua, String numero, String cep) {
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(pacienteId);
        if (optPaciente.isPresent()) {
            Endereco e = new Endereco(estado, cidade, rua, numero, cep, optPaciente.get());
            enderecoService.salvar(e);
            return true;
        }
        return false;
    }

    public List<Endereco> listarEnderecos() {
        return enderecoService.listarTodos();
    }

    public boolean editarEndereco(Long id, String estado, String cidade, String rua, String numero, String cep) {
        Optional<Endereco> opt = enderecoService.buscarPorId(id);
        if (opt.isPresent()) {
            Endereco e = opt.get();
            if (estado != null && !estado.isBlank()) e.setEstado(estado);
            if (cidade != null && !cidade.isBlank()) e.setCidade(cidade);
            if (rua != null && !rua.isBlank()) e.setRua(rua);
            if (numero != null && !numero.isBlank()) e.setNumero(numero);
            if (cep != null && !cep.isBlank()) e.setCep(cep);
            enderecoService.atualizar(e);
            return true;
        }
        return false;
    }

    public boolean deletarEndereco(Long id) {
        Optional<Endereco> opt = enderecoService.buscarPorId(id);
        if (opt.isPresent()) {
            enderecoService.deletar(id);
            return true;
        }
        return false;
    }
}
