<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Categorie> categories = (ArrayList<Categorie>)request.getAttribute("categories");
    ArrayList<Categorie> categories_top3 = (ArrayList<Categorie>)request.getAttribute("categories_top3");
    ArrayList<Produit> produitsVendus = (ArrayList<Produit>)request.getAttribute("produitsVendus");
    ArrayList<Rechargement> rechargements = (ArrayList<Rechargement>)request.getAttribute("rechargements");
%>
<html>
<head>
    <title>BackOffice - Accueil</title>
</head>
<body>
<h1>Ajout Catégorie</h1>
<form method="post" action="Categorie">
    <p>
        Catégorie: <input type="text" name="categorie" placeholder="Nom" required>
        <input type="submit" value="Ajouter l'employé">
    </p>
</form>
<br/>
<table border="1" cellspacing="1" cellpadding="10">
    <thead>
        <tr>
            <th>ID Catégorie</th>
            <th>Nom</th>
        </tr>
    </thead>
    <tbody>
    <%
        for(int i=0;i<categories.size(); i++) {
            out.println("<tr>");
            out.println("<td>"+categories.get(i).getIdcategorie()+"</td>");
            out.println("<td>"+categories.get(i).getNomcategorie()+"</td>");
            out.println("</tr>");
        }
    %>
    </tbody>
</table>
<br/>
<h1>Top 3 des produits plus chers (vendus)</h1>
<table border="1" cellspacing="1" cellpadding="10">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Produit</th>
        <th>Catégorie</th>
        <th>Date</th>
        <th>Durée</th>
        <th>Prix</th>
        <th>Commission (20%)</th>
    </tr>
    </thead>
    <tbody>
    <%
        for(int i=0;i<produitsVendus.size(); i++) {
            out.println("<tr>");
            out.println("<td>"+produitsVendus.get(i).getIdmanaoenchere()+"</td>");
            out.println("<td>"+produitsVendus.get(i).getNom()+"</td>");
            out.println("<td>"+produitsVendus.get(i).getProduit()+"</td>");
            out.println("<td>"+produitsVendus.get(i).getCategorie()+"</td>");
            out.println("<td>"+produitsVendus.get(i).getDaty()+"</td>");
            out.println("<td>"+produitsVendus.get(i).getDuree()+"</td>");
            out.println("<td>"+produitsVendus.get(i).getPrix()+" Ar</td>");
            out.println("<td>"+produitsVendus.get(i).getCommission()+" Ar</td>");
            out.println("</tr>");
        }
    %>
    </tbody>
</table>
<br/>
<h1>Top 3 Catégories les plus vendues</h1>
<table border="1" cellspacing="1" cellpadding="10">
    <thead>
    <tr>
        <th>ID Catégorie</th>
        <th>Nom</th>
        <th>Nombre d'articles vendues</th>
    </tr>
    </thead>
    <tbody>
    <%
        for(int i=0;i<categories_top3.size(); i++) {
            out.println("<tr>");
            out.println("<td>"+categories_top3.get(i).getIdcategorie()+"</td>");
            out.println("<td>"+categories_top3.get(i).getNomcategorie()+"</td>");
            out.println("<td>"+categories_top3.get(i).getNombreVendu()+"</td>");
            out.println("</tr>");
        }
    %>
    </tbody>
</table>
<br/>
<h1>Valider Rechargement</h1>
<table border="1" cellspacing="1" cellpadding="10">
    <thead>
    <tr>
        <th>ID Catégorie</th>
        <th>Nom</th>
        <th>Utilisateur</th>
        <th>Options</th>
    </tr>
    </thead>
    <tbody>
    <%
        for(int i=0;i<rechargements.size(); i++) {
            out.println("<tr>");
            out.println("<td>"+rechargements.get(i).getIdrechargement()+"</td>");
            out.println("<td>"+rechargements.get(i).getValeur()+"</td>");
            out.println("<td>"+rechargements.get(i).getNomutilisateur()+"</td>");
            out.println("<td><a href='Valider?idrechargement="+rechargements.get(i).getIdrechargement()+"&valeur="+rechargements.get(i).getValeur()+"&idutilisateur="+rechargements.get(i).getIdutilisateur()+"'>Accepter</a> | <a href='Refuser?idrechargement="+rechargements.get(i).getIdrechargement()+"'>Refuser</a></td>");
            out.println("</tr>");
        }
    %>
    </tbody>
</table>
<br/>
<a href="Disconnect"><< Se deconnecter</a>
</body>
</html>
