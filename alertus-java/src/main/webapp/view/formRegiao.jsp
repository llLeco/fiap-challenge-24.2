<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.fiap.alertus.model.Regiao" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alertus - Regiões</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>
    <!-- Navigation (mantendo igual) -->
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container">
            <a class="navbar-brand" href="index">
                <i class="bi bi-exclamation-triangle"></i> ALERTUS
            </a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link" href="index"><i class="bi bi-house-door"></i> Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link" href="evento"><i class="bi bi-calendar-event"></i> Eventos</a></li>
                    <li class="nav-item"><a class="nav-link active" href="regiao"><i class="bi bi-geo-alt"></i> Regiões</a></li>
                    <li class="nav-item"><a class="nav-link" href="historico"><i class="bi bi-clock-history"></i> Histórico</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <!-- Lista de Regiões -->
        <% List<Regiao> regioes = (List<Regiao>)request.getAttribute("regioes");
           if(regioes != null && !regioes.isEmpty()) {
           for(Regiao r : regioes){ %>
            <div class="mb-3">
                <strong><%=r.getNome()%></strong> - Lat: <%=r.getLatitude()%>, Lng: <%=r.getLongitude()%>
            </div>
        <% }} %>
        
        <!-- Formulário -->
        <form method="post">
            <div class="mb-3">
                <label class="form-label">Nome:</label>
                <input type="text" name="nome" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Latitude:</label>
                <input type="number" name="latitude" step="any" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Longitude:</label>
                <input type="number" name="longitude" step="any" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
    </div>
</body>
</html>
