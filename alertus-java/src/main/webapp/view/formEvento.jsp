<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.fiap.alertus.model.Region" %>
<%@ page import="br.com.fiap.alertus.model.Event" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alertus - Eventos</title>
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
                    <li class="nav-item"><a class="nav-link active" href="evento"><i class="bi bi-calendar-event"></i> Eventos</a></li>
                    <li class="nav-item"><a class="nav-link" href="regiao"><i class="bi bi-geo-alt"></i> Regiões</a></li>
                    <li class="nav-item"><a class="nav-link" href="historico"><i class="bi bi-clock-history"></i> Histórico</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <!-- Formulário de Cadastro -->
        <h2>Cadastrar Evento</h2>
        <form method="post">
            <div class="mb-3">
                <label class="form-label">Tipo:</label>
                <input type="text" name="tipo" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Intensidade:</label>
                <select name="intensidade" class="form-control" required>
                    <option value="Baixa">Baixa</option>
                    <option value="Moderada">Moderada</option>
                    <option value="Alta">Alta</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Região:</label>
                <select name="regiao_id" class="form-control" required>
                    <% List<Region> regions = (List<Region>)request.getAttribute("regions");
                       if(regions != null){
                       for(Region r : regions){ %>
                        <option value="<%=r.getId()%>"><%=r.getName()%></option>
                    <% }} %>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>

        <!-- Lista de Eventos -->
        <h2 class="mt-4">Eventos Cadastrados</h2>
        <% List<Event> events = (List<Event>)request.getAttribute("events");
           if(events != null && !events.isEmpty()) {
           for(Event e : events){ %>
            <div class="mb-3 card">
                <div class="card-body">
                    <strong><%=e.getType()%></strong> - <%=e.getIntensity()%> 
                    <span class="text-muted">(<%=e.getRegion().getName()%>)</span>
                </div>
            </div>
        <% }} %>
    </div>
</body>
</html>
