package Recuperacao.Service;

import Recuperacao.Model.Endereco;
import Recuperacao.Model.Paciente;
import Recuperacao.Repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ViaCepService viaCepService;

    public EnderecoService(EnderecoRepository enderecoRepository, ViaCepService viaCepService) {
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    // 🔹 Salvar endereço no banco
    public void salvar(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    // 🔹 Atualizar endereço existente
    public void atualizar(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    // 🔹 Deletar endereço pelo ID
    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    // 🔹 Buscar endereço pelo ID
    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    // 🔹 Listar todos os endereços
    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    /**
     * 🔹 Consultar CEP usando ViaCep e retornar os dados em Map
     * @param cep CEP a ser consultado
     * @return Map com chaves: uf, localidade, logradouro, bairro
     */
    public Map<String, String> consultarCep(String cep) {
        Map<String, String> dados = viaCepService.buscarCep(cep);
        if (dados == null || dados.isEmpty() || dados.containsKey("erro")) {
            throw new RuntimeException("CEP inválido ou não encontrado!");
        }
        return dados;
    }

    /**
     * 🔹 Criar e salvar endereço usando CEP, número e paciente
     * @param cep CEP a ser consultado
     * @param numero Número da residência
     * @param paciente Paciente associado ao endereço
     * @return Endereco cadastrado
     */
    public Endereco salvarPorCep(String cep, String numero, Paciente paciente) {
        Map<String, String> dadosCep = consultarCep(cep);

        Endereco endereco = new Endereco(
                dadosCep.get("uf"),
                dadosCep.get("localidade"),
                dadosCep.get("logradouro"),
                numero,
                dadosCep.get("bairro"),
                cep,
                paciente
        );

        enderecoRepository.save(endereco);
        return endereco;
    }
}
