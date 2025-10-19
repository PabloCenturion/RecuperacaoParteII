package Recuperacao.Service;

import Recuperacao.Model.Endereco;
import Recuperacao.Repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco atualizar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void deletar(long id) {
        enderecoRepository.deleteById(id);
    }
}
