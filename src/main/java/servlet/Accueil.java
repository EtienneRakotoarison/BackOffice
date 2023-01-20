package servlet;

import model.Admin;
import model.Categorie;
import model.Produit;
import model.Rechargement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Accueil", value = "/Accueil")
public class Accueil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        ArrayList<Categorie> categories = null;
        ArrayList<Categorie> categories_top3 = null;
        ArrayList<Produit> produitsVendus = null;
        ArrayList<Rechargement> rechargements = null;
        try {
            categories = Categorie.getCategories();
            categories_top3 = Categorie.getCategoriesTop3();
            produitsVendus = Produit.getProduitsTop3();
            rechargements = Rechargement.getRechargementsAdmin();
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        request.setAttribute("categories", categories);
        request.setAttribute("categories_top3", categories_top3);
        request.setAttribute("produitsVendus", produitsVendus);
        request.setAttribute("rechargements", rechargements);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Accueil.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
