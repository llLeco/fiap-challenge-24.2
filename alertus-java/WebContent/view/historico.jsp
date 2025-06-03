<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Histórico</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav>
    <a href="index.jsp">Home</a>
    <a href="historico">Histórico</a>
    <a href="regiao">Regiões</a>
    <a href="evento">Eventos</a>
    <a href="logout">Sair</a>
</nav>
<h2>Eventos</h2>
<table border="1">
<tr><th>Tipo</th><th>Intensidade</th><th>Região</th><th>Data</th></tr>
<% java.util.List<model.Evento> eventos = (java.util.List<model.Evento>)request.getAttribute("eventos");
   if(eventos!=null){
       for(model.Evento e : eventos){ %>
<tr><td><%=e.getTipo()%></td><td><%=e.getIntensidade()%></td><td><%=e.getRegiao().getNome()%></td><td><%=e.getData()%></td></tr>
<%     }
   }
%>
</table>
<h2>Alertas</h2>
<table border="1">
<tr><th>Tipo</th><th>Região</th><th>Mensagem</th><th>Nível</th><th>Data</th></tr>
<% java.util.List<model.Alerta> alertas = (java.util.List<model.Alerta>)request.getAttribute("alertas");
   if(alertas!=null){
       for(model.Alerta a : alertas){ %>
<tr class="alert-<%=a.getNivel()%>"><td><%=a.getEvento().getTipo()%></td><td><%=a.getEvento().getRegiao().getNome()%></td><td><%=a.getMensagem()%></td><td><%=a.getNivel()%></td><td><%=a.getData()%></td></tr>
<%     }
   }
%>
</table>
</body>
</html>
