<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alertus - Login</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>
    <div class="login-container">
        <div class="login-card">
            <div class="text-center">
                <i class="bi bi-exclamation-triangle card-icon text-danger"></i>
                <h2 class="login-title">🚨 ALERTUS</h2>
                <p class="text-muted mb-4">Sistema de Alerta Antecipado</p>
            </div>
            
            <form method="post" action="../login">
                <div class="mb-3">
                    <label for="login" class="form-label">
                        <i class="bi bi-person"></i> Usuário
                    </label>
                    <input type="text" class="form-control" id="login" name="login" required 
                           placeholder="Digite seu usuário">
                </div>
                
                <div class="mb-3">
                    <label for="senha" class="form-label">
                        <i class="bi bi-lock"></i> Senha
                    </label>
                    <input type="password" class="form-control" id="senha" name="senha" required 
                           placeholder="Digite sua senha">
                </div>
                
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-box-arrow-in-right"></i> Entrar
                    </button>
                </div>
            </form>
            
            <% if (request.getAttribute("erro") != null) { %>
                <div class="alert alert-danger mt-3" role="alert">
                    <i class="bi bi-exclamation-circle"></i> <%=request.getAttribute("erro")%>
                </div>
            <% } %>
            
            <div class="text-center mt-4">
                <small class="text-muted">
                    <i class="bi bi-info-circle"></i> Use: admin/admin para acesso
                </small>
            </div>
        </div>
    </div>
</body>
</html>
