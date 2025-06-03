<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eventos</title>
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
<h2>Cadastrar Evento</h2>
<form method="post" action="evento">
    Tipo: <select name="tipo">
        <option value="enchente">Enchente</option>
        <option value="terremoto">Terremoto</option>
        <option value="calor">Calor Extremo</option>
    </select><br>
    Intensidade: <input type="number" step="0.1" name="intensidade" required><br>
    Data/Hora: <input type="datetime-local" name="data" required><br>
    Região: <select name="regiao">
        <% java.util.List<model.Regiao> regioes = (java.util.List<model.Regiao>)request.getAttribute("regioes");
           if(regioes!=null){
               for(model.Regiao r : regioes){ %>
        <option value="<%=r.getId()%>"><%=r.getNome()%></option>
        <%     }
           }
        %>
    </select><br>
    <input type="submit" value="Salvar">
</form>
<h2>Eventos Registrados</h2>
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
</body>
</html>
