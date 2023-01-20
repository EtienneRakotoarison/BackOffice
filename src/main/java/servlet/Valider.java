package servlet;

import model.Rechargement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Valider", value = "/Valider")
public class Valider extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idrechargement = request.getParameter("idrechargement");
        Double valeur = Double.valueOf(request.getParameter("valeur"));
        String idutilisateur = request.getParameter("idutilisateur");
        try {
            Rechargement.recharger(idrechargement, valeur, idutilisateur);
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
