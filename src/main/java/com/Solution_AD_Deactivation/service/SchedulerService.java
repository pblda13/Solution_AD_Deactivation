package com.Solution_AD_Deactivation.service;

import com.Solution_AD_Deactivation.model.Funcionario;
import com.Solution_AD_Deactivation.model.FuncionarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {
    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ADService adService;

    @Scheduled(cron = "0 0 1 * * ?") // Diariamente às 01:00
    public void desativarUsuariosDesligados() {
        List<FuncionarioDTO> desligados = funcionarioService.getFuncionariosPorStatus("Desligado");
        desligados.forEach(func -> adService.desativarUsuario(func.getNome()));
    }

}