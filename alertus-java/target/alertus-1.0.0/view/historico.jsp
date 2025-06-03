<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.fiap.alertus.model.Evento" %>
<%@ page import="br.com.fiap.alertus.model.Alerta" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alertus - Histórico</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container">
            <a class="navbar-brand" href="index">
                <i class="bi bi-exclamation-triangle"></i> ALERTUS
            </a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link" href="index"><i class="bi bi-house-door"></i> Dashboard</a></li>
                    <li class="nav-item"><a class="nav-link" href="evento"><i class="bi bi-calendar-event"></i> Eventos</a></li>
                    <li class="nav-item"><a class="nav-link" href="regiao"><i class="bi bi-geo-alt"></i> Regiões</a></li>
                    <li class="nav-item"><a class="nav-link active" href="historico"><i class="bi bi-clock-history"></i> Histórico</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <h2>Histórico de Eventos</h2>
        <% List<Evento> eventos = (List<Evento>)request.getAttribute("eventos");
           if(eventos != null && !eventos.isEmpty()) {
           for(Evento e : eventos){ %>
            <div class="mb-3 card">
                <div class="card-body">
                    <h5><%=e.getTipo()%></h5>
                    <p>Intensidade: <%=e.getIntensidade()%> | Região: <%=e.getRegiao().getNome()%> | Data: <%=e.getData()%></p>
                </div>
            </div>
        <% }} %>

        <h2 class="mt-4">Histórico de Alertas</h2>
        <% List<Alerta> alertas = (List<Alerta>)request.getAttribute("alertas");
           if(alertas != null && !alertas.isEmpty()) {
           for(Alerta a : alertas){ %>
            <div class="mb-3 card">
                <div class="card-body">
                    <h5><%=a.getMensagem()%></h5>
                    <p>Nível: <%=a.getNivel()%> | Evento: <%=a.getEvento().getTipo()%> | Data: <%=a.getData()%></p>
                </div>
            </div>
        <% }} %>
    </div>
</body>
</html>
