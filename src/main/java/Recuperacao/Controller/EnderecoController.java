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

    // 🔹 Adiciona um endereço vinculado a um paciente usando ViaCep
    public boolean adicionarEndereco(Long pacienteId, String cep, String numero) {
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(pacienteId);
        if (optPaciente.isPresent()) {
            try {
                // Consultar os dados do endereço pelo CEP (ViaCep)
                var dadosCep = enderecoService.consultarCep(cep);

                // Criar o endereço com base nos dados retornados pela API
                Endereco e = new Endereco(
                        dadosCep.get("uf"),
                        dadosCep.get("localidade"),
                        dadosCep.get("logradouro"),
                        numero,
                        dadosCep.get("bairro"),
                        cep,
                        optPaciente.get()
                );

                enderecoService.salvar(e);
                System.out.println("Endereço salvo com sucesso!");
                return true;
            } catch (RuntimeException ex) {
                System.out.println("❌ Erro ao consultar o CEP: " + ex.getMessage());
            }
        } else {
            System.out.println("❌ Paciente não encontrado!");
        }
        return false;
    }

    // 🔹 Listar todos os endereços cadastrados
    public List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    // 🔹 Editar um endereço existente
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
            System.out.println("Endereço atualizado com sucesso!");
            return true;
        }
        System.out.println("❌ Endereço não encontrado!");
        return false;
    }

    // 🔹 Deletar um endereço pelo ID
    public boolean deletarEndereco(Long id) {
        Optional<Endereco> opt = enderecoService.buscarPorId(id);
        if (opt.isPresent()) {
            enderecoService.deletarEndereco(id);
            System.out.println("Endereço deletado com sucesso!");
            return true;
        }
        System.out.println("❌ Endereço não encontrado!");
        return false;
    }
}
