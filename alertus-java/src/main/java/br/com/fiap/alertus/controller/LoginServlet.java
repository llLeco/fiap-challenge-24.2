package br.com.fiap.alertus.controller;

import br.com.fiap.alertus.dao.UserDAO;
import br.com.fiap.alertus.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        // Para backward compatibility, também aceita login/senha
        if (email == null) email = req.getParameter("login");
        if (password == null) password = req.getParameter("senha");
        
        UserDAO dao = new UserDAO();
        try {
            User user = dao.findByEmailAndPassword(email, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect("index");
            } else {
                req.setAttribute("error", "Invalid email or password");
                req.getRequestDispatcher("view/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
