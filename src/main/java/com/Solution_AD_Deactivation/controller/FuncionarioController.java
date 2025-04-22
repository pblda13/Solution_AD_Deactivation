package com.Solution_AD_Deactivation.controller;

import com.Solution_AD_Deactivation.model.Funcionario;
import com.Solution_AD_Deactivation.model.FuncionarioDTO;
import com.Solution_AD_Deactivation.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/status/{status}")
    public List<FuncionarioDTO> getFuncionariosPorStatus(@PathVariable String status) {
        return funcionarioService.getFuncionariosPorStatus(status);
    }
}
