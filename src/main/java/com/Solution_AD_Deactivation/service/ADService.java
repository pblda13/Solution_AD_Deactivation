package com.Solution_AD_Deactivation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class ADService {
    private static final Logger logger = LoggerFactory.getLogger(ADService.class);

    public void desativarUsuario(String nomeUsuario) {
        logger.info("Desativando usuário: " + nomeUsuario);
        try {
            // Script PowerShell
            ProcessBuilder processBuilder = new ProcessBuilder("powershell", "-File", "desativar-usuarios.ps1", "-NomeUsuario", nomeUsuario);
            Process process = processBuilder.start();
            process.waitFor();  // Espera o processo terminar

            // Captura e loga a saída de erro, caso exista
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                logger.error(errorLine);  // Log de erro
            }

            // Se o processo foi bem-sucedido, loga o sucesso
            if (process.exitValue() == 0) {
                logger.info("Usuário desativado com sucesso: " + nomeUsuario);
            } else {
                logger.error("Erro ao desativar usuário: " + nomeUsuario);
            }
        } catch (Exception e) {
            logger.error("Erro ao desativar usuário: " + nomeUsuario, e);
        }
    }
}

