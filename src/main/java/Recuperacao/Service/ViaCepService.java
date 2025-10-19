package Recuperacao.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Consulta o CEP na API ViaCep.
     * @param cep CEP informado pelo usuário
     * @return Mapa com os dados retornados (logradouro, bairro, localidade, uf, etc)
     */
    public Map<String, String> buscarCep(String cep) {
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            Map<String, String> response = restTemplate.getForObject(url, Map.class);

            // Verifica se o CEP é inválido
            if (response == null || response.containsKey("erro")) {
                throw new RuntimeException("CEP inválido ou não encontrado!");
            }

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar o ViaCEP: " + e.getMessage());
        }
    }
}
