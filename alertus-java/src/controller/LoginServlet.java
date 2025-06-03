package controller;

import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");
        UsuarioDAO dao = new UsuarioDAO();
        try {
            Usuario u = dao.buscarPorLoginSenha(login, senha);
            if (u != null) {
                HttpSession session = req.getSession();
                session.setAttribute("usuario", u);
                resp.sendRedirect("index.jsp");
            } else {
                req.setAttribute("erro", "Login inválido");
                req.getRequestDispatcher("view/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
