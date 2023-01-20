package servlet;

import model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        try {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setMdp(mdp);
            Admin.login(admin);
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            response.sendRedirect(request.getContextPath() + "/Accueil");
        } catch (Exception e) {
            request.setAttribute("status", "406");
            request.setAttribute("exception", e.getMessage());
            request.setAttribute("lien", "index.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Exception.jsp");
            dispatcher.forward(request,response);
        }
    }
}
