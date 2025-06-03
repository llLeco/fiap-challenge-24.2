<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Verificar se usuário está logado
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("view/login.jsp");
    } else {
        response.sendRedirect("index");
    }
%> 