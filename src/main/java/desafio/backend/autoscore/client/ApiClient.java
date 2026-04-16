package desafio.backend.autoscore.client;

import desafio.backend.autoscore.dto.VeiculoApiResponse;
import desafio.backend.autoscore.exception.ExternalApiException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {

    private final RestTemplate restTemplate;

    public ApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public VeiculoApiResponse getData(String placa) {
        String url = "https://my.api.mockaroo.com/veiculos?key=55ad1cd0&placa=" + placa;

        try {
            VeiculoApiResponse response = restTemplate.getForObject(url, VeiculoApiResponse.class);

            if (response == null) {
                throw new ExternalApiException("Veiculo não encontrado na base externa");
            }

            return response;
        } catch (ExternalApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ExternalApiException("Erro ao consultar API externa" + e.getMessage());
        }
    }
}
