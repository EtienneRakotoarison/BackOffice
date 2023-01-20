package model;

import connection.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Produit {
    // select *, (commission/0.2) prix from v_produits_plus_chers limit 3;
    int idmanaoenchere;
    String nom;
    String produit;
    String categorie;
    String daty;
    int duree;
    double commission;
    double prix;

    public Produit(int idmanaoenchere, String nom, String produit, String categorie, String daty, int duree, double commission, double prix) {
        this.idmanaoenchere = idmanaoenchere;
        this.nom = nom;
        this.produit = produit;
        this.categorie = categorie;
        this.daty = daty;
        this.duree = duree;
        this.commission = commission;
        this.prix = prix;
    }

    public Produit() {
    }

    public int getIdmanaoenchere() {
        return idmanaoenchere;
    }

    public void setIdmanaoenchere(int idmanaoenchere) {
        this.idmanaoenchere = idmanaoenchere;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDaty() {
        return daty;
    }

    public void setDaty(String daty) {
        this.daty = daty;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public static ArrayList<Produit> getProduitsTop3() throws SQLException {
        ArrayList<Produit> produits = new ArrayList<Produit>();
        java.sql.Connection conn=null;
        PreparedStatement stmt=null;
        try {
            ConnectDB postgresql = model.Connection.getInstancePostgresql();
            conn = postgresql.getConnection();
            String sql = "select *, (commission/0.2) prix from v_produits_plus_chers limit 3;";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Produit produit = new Produit(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), String.valueOf(resultSet.getDate(5)), resultSet.getInt(6), resultSet.getDouble(7), resultSet.getDouble(8));
                produits.add(produit);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (conn!=null) conn.close();
            if (stmt!=null) stmt.close();
        }
        return produits;
    }
}
