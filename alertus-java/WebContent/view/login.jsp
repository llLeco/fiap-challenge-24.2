<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2>Login</h2>
<form method="post" action="login">
    Login: <input type="text" name="login"><br>
    Senha: <input type="password" name="senha"><br>
    <input type="submit" value="Entrar">
</form>
<% if (request.getAttribute("erro") != null) { %>
<p style="color:red"><%=request.getAttribute("erro")%></p>
<% } %>
</body>
</html>
