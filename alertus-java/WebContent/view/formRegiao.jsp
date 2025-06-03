<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Regiões</title>
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
<h2>Cadastrar Região</h2>
<form method="post" action="regiao">
    Nome: <input type="text" name="nome" required><br>
    Latitude: <input type="number" step="0.0001" name="latitude" required><br>
    Longitude: <input type="number" step="0.0001" name="longitude" required><br>
    <input type="submit" value="Salvar">
</form>
<h2>Regiões Cadastradas</h2>
<table border="1">
<tr><th>Nome</th><th>Latitude</th><th>Longitude</th></tr>
<% java.util.List<model.Regiao> regioes = (java.util.List<model.Regiao>)request.getAttribute("regioes");
   if(regioes!=null){
       for(model.Regiao r : regioes){ %>
<tr><td><%=r.getNome()%></td><td><%=r.getLatitude()%></td><td><%=r.getLongitude()%></td></tr>
<%     }
   }
%>
</table>
</body>
</html>
