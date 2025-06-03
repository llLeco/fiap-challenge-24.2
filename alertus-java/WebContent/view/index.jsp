<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alertus - Dashboard</title>
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
            
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index">
                            <i class="bi bi-house-door"></i> Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="evento">
                            <i class="bi bi-calendar-event"></i> Eventos
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="regiao">
                            <i class="bi bi-geo-alt"></i> Regiões
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="historico">
                            <i class="bi bi-clock-history"></i> Histórico
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="logout">
                            <i class="bi bi-box-arrow-right"></i> Sair
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-4">
        <!-- Page Header -->
        <div class="row mb-4">
            <div class="col-12">
                <h1 class="h3 mb-0">
                    <i class="bi bi-speedometer2"></i> Dashboard - Sistema Alertus
                </h1>
                <p class="text-muted">Monitoramento em tempo real de alertas e eventos</p>
            </div>
        </div>

        <!-- Statistics Cards -->
        <div class="row mb-4">
            <div class="col-lg-3 col-md-6 mb-3">
                <div class="card dashboard-card text-white bg-danger">
                    <div class="card-body text-center">
                        <i class="bi bi-exclamation-triangle card-icon"></i>
                        <h5 class="card-title">Alertas Ativos</h5>
                        <h2 class="mb-0">
                            <% 
                                java.util.List<model.Alerta> alertas = (java.util.List<model.Alerta>)request.getAttribute("alertas");
                                int totalAlertas = (alertas != null) ? alertas.size() : 0;
                                out.print(totalAlertas);
                            %>
                        </h2>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-3">
                <div class="card dashboard-card text-white bg-warning">
                    <div class="card-body text-center">
                        <i class="bi bi-exclamation-circle card-icon"></i>
                        <h5 class="card-title">Críticos</h5>
                        <h2 class="mb-0">
                            <% 
                                int criticos = 0;
                                if (alertas != null) {
                                    for(model.Alerta a : alertas) {
                                        if ("Alto".equals(a.getNivel())) criticos++;
                                    }
                                }
                                out.print(criticos);
                            %>
                        </h2>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-3">
                <div class="card dashboard-card text-white bg-info">
                    <div class="card-body text-center">
                        <i class="bi bi-calendar-check card-icon"></i>
                        <h5 class="card-title">Eventos Hoje</h5>
                        <h2 class="mb-0">3</h2>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-3">
                <div class="card dashboard-card text-white bg-success">
                    <div class="card-body text-center">
                        <i class="bi bi-check-circle card-icon"></i>
                        <h5 class="card-title">Sistema</h5>
                        <h6 class="mb-0">Online</h6>
                    </div>
                </div>
            </div>
        </div>

        <!-- Active Alerts -->
        <div class="row">
            <div class="col-12">
                <div class="table-container">
                    <h3 class="mb-3">
                        <i class="bi bi-bell"></i> Alertas Ativos (<%=totalAlertas%>)
                    </h3>
                    
                    <% if (alertas == null || alertas.isEmpty()) { %>
                        <div class="alert alert-success">
                            <i class="bi bi-check-circle"></i> Nenhum alerta ativo no momento
                        </div>
                    <% } else { %>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th><i class="bi bi-tag"></i> Tipo</th>
                                        <th><i class="bi bi-geo-alt"></i> Região</th>
                                        <th><i class="bi bi-chat-text"></i> Mensagem</th>
                                        <th><i class="bi bi-speedometer"></i> Nível</th>
                                        <th><i class="bi bi-calendar"></i> Data</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(model.Alerta a : alertas) { %>
                                        <tr>
                                            <td>
                                                <span class="badge bg-primary"><%=a.getEvento().getTipo()%></span>
                                            </td>
                                            <td><%=a.getEvento().getRegiao().getNome()%></td>
                                            <td><%=a.getMensagem()%></td>
                                            <td>
                                                <span class="status-badge 
                                                    <% if ("Alto".equals(a.getNivel())) { %>status-alta<% } 
                                                       else if ("Medio".equals(a.getNivel())) { %>status-moderada<% } 
                                                       else { %>status-baixa<% } %>">
                                                    <%=a.getNivel()%>
                                                </span>
                                            </td>
                                            <td><%=a.getData()%></td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
