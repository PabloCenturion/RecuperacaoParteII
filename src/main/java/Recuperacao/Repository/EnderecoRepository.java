package Recuperacao.Repository;

import Recuperacao.Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    // Aqui você pode adicionar métodos customizados caso precise,
    // por exemplo: buscar por CEP, bairro, cidade, etc.
}
