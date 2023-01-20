package servlet;

import model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Categorie", value = "/Categorie")
public class Categorie extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String categorie = request.getParameter("categorie");
        try {
            model.Categorie.insert(categorie);
        } catch (Exception e) {
            request.setAttribute("status", "406");
            request.setAttribute("exception", e.getMessage());
            request.setAttribute("lien", "index.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Exception.jsp");
            dispatcher.forward(request,response);
        }
        response.sendRedirect(request.getContextPath() + "/Accueil");
    }
}
