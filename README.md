

# 🚀 Sistema de Desativação de Usuários com Spring Boot e PowerShell

Este projeto foi desenvolvido para automatizar o processo de desativação de usuários desligados de uma empresa, integrando uma **API de RH**, uma **aplicação Java Spring Boot**, e um **script PowerShell** que desativa o usuário no Active Directory (AD).

---

## 📌 O que essa aplicação faz?

De forma simples:

1. **Consulta uma API de Recursos Humanos (RH)** que retorna os funcionários com status "Desligado".
2. **Exibe esses funcionários em um endpoint da aplicação web.**
3. **Executa um script do PowerShell**, que desativa o login do funcionário desligado no sistema da empresa (Active Directory).

---

## 🧰 Tecnologias Utilizadas

- **Java + Spring Boot** – Para criar a aplicação web.
- **RestTemplate** – Para fazer chamadas HTTP à API de RH.
- **PowerShell Script** – Para desativar o usuário no Active Directory.
- **Active Directory (AD)** – Onde ficam registrados os usuários da empresa.
- **Log com Transcript** – Registra todas as ações feitas.

---

## 🧩 Como funciona?

1. Você faz uma requisição HTTP como essa:

```
GET /api/funcionarios/status/Desligado
```

2. A aplicação busca na API de RH todos os usuários que estão com esse status.
3. Para cada usuário encontrado, o sistema executa o seguinte script PowerShell:

```powershell
desativar-usuarios.ps1 -NomeUsuario "Nome do Funcionário"
```

Esse script:

- Procura o usuário no AD.
- Desativa sua conta.
- Registra a ação num arquivo de log.

---

## 🖥️ Exemplo do Script PowerShell

```powershell
param (
    [Parameter(Mandatory=$true)]
    [string]$NomeUsuario
)

Start-Transcript -Path "C:\Logs\DesativarUsuarios.log" -Append

try {
    Import-Module ActiveDirectory -ErrorAction Stop
    $usuario = Get-ADUser -Filter "Name -eq '$NomeUsuario'" -ErrorAction Stop
    if ($usuario) {
        Disable-ADAccount -Identity $usuario.SamAccountName -ErrorAction Stop
        Write-Host "Usuário $NomeUsuario desativado com sucesso."
    } else {
        Write-Host "Usuário $NomeUsuario não encontrado no AD."
    }
} catch {
    Write-Host "Erro: $($_.Exception.Message)"
}

Stop-Transcript
```

---

## ⚙️ Estrutura do Projeto

```
├── src
│   ├── config (RestClientConfig.java)
│   ├── controller (FuncionarioController.java)
│   ├── service
│   │   ├── FuncionarioService.java
│   │   └── ADService.java
│   └── model (FuncionarioDTO.java)
├── desativar-usuarios.ps1
├── README.md
```

---

## 👥 Para quem é esse projeto?

Este projeto é ideal para empresas que desejam automatizar a remoção de acessos de funcionários desligados, garantindo **segurança da informação** e **agilidade** nos processos de RH e TI.

---

## 📌 Requisitos para rodar

- Java 11 ou superior
- Spring Boot
- PowerShell habilitado
- Permissão para executar scripts no Windows
- Acesso ao Active Directory (com módulo `ActiveDirectory` instalado)

![Visitors](https://api.visitorbadge.io/api/visitors?path=https%3A%2F%2Fgithub.com%2Fpblda13%2FSolution_AD_Deactivation&label=Visitors&labelColor=%23f47373&countColor=%23dce775)
