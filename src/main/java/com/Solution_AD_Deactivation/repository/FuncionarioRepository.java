package com.Solution_AD_Deactivation.repository;

import com.Solution_AD_Deactivation.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT f FROM Funcionario f WHERE f.status = 'Desligado'")
    List<Funcionario> findDesligados();
}
