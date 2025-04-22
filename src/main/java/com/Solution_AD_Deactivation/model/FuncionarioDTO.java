package com.Solution_AD_Deactivation.model;

public class FuncionarioDTO {
    private String nome;
    private String cargo;
    private String status;
    private String dataDesligamento;

    private FuncionarioDTO() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(String dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }
}
