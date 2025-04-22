param (
    [Parameter(Mandatory=$true)]
    [string]$NomeUsuario
)

# Inicia a gravação do transcript para o log
Start-Transcript -Path "C:\Logs\DesativarUsuarios.log" -Append

try {
    # Verifica se o módulo do AD está disponível
    Import-Module ActiveDirectory -ErrorAction Stop

    # Busca o usuário no AD
    $usuario = Get-ADUser -Filter "Name -eq '$NomeUsuario'" -ErrorAction Stop
    if ($usuario) {
        # Desativa o usuário
        Disable-ADAccount -Identity $usuario.SamAccountName -ErrorAction Stop
        Write-Host "Usuário $NomeUsuario desativado com sucesso."
    } else {
        Write-Host "Usuário $NomeUsuario não encontrado no AD."
    }
} catch {
    Write-Host "Erro: $($_.Exception.Message)"
}

# Finaliza a gravação do transcript
Stop-Transcript
