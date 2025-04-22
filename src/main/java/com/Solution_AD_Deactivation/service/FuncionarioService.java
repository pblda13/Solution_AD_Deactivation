package com.Solution_AD_Deactivation.service;

import com.Solution_AD_Deactivation.model.Funcionario;
import com.Solution_AD_Deactivation.model.FuncionarioDTO;
import com.Solution_AD_Deactivation.repository.FuncionarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_URL = "http://api.rh.com/funcionarios"; // URL da API de RH

    public List<FuncionarioDTO> getFuncionariosPorStatus(String status) {
        // Monta a URL da API com o status
        String url = API_URL + "?status=" + status;

        // Faz a requisição para a API e obtém a resposta
        ResponseEntity<List<FuncionarioDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FuncionarioDTO>>() {}
        );

        // Retorna a lista de funcionários recebidos
        return response.getBody();
    }
}
