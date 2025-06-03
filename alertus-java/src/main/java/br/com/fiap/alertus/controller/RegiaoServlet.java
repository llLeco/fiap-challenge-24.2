package br.com.fiap.alertus.controller;

import br.com.fiap.alertus.dao.RegionDAO;
import br.com.fiap.alertus.model.Region;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegiaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendRedirect("view/login.jsp");
            return;
        }
        
        RegionDAO dao = new RegionDAO();
        try {
            List<Region> regions = dao.findAll();
            req.setAttribute("regions", regions);
            req.getRequestDispatcher("view/formRegiao.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendRedirect("view/login.jsp");
            return;
        }
        
        Region region = new Region();
        region.setName(req.getParameter("nome"));
        region.setLatitude(Double.parseDouble(req.getParameter("latitude")));
        region.setLongitude(Double.parseDouble(req.getParameter("longitude")));
        
        RegionDAO dao = new RegionDAO();
        try {
            dao.insert(region);
            resp.sendRedirect("regiao");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
