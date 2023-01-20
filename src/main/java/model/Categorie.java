package model;

import connection.ConnectDB;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

public class Categorie {
    int idcategorie;
    String nomcategorie;

    int nombreVendu;

    public Categorie(int idcategorie, String nomcategorie) {
        this.idcategorie = idcategorie;
        this.nomcategorie = nomcategorie;
    }

    public Categorie() {
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public int getNombreVendu() {
        return nombreVendu;
    }

    public void setNombreVendu(int nombreVendu) {
        this.nombreVendu = nombreVendu;
    }

    public static ArrayList<Categorie> getCategories() throws SQLException {
        ArrayList<Categorie> categories = new ArrayList<Categorie>();
        Connection conn=null;
        PreparedStatement stmt=null;
        try {
            ConnectDB postgresql = model.Connection.getInstancePostgresql();
            conn = postgresql.getConnection();
            String sql = "select * from categorie";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Categorie categorie = new Categorie(resultSet.getInt(1), resultSet.getString(2));
                categories.add(categorie);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (conn!=null) conn.close();
            if (stmt!=null) stmt.close();
        }
        return categories;
    }

    public static ArrayList<Categorie> getCategoriesTop3() throws SQLException {
        ArrayList<Categorie> categories = new ArrayList<Categorie>();
        Connection conn=null;
        PreparedStatement stmt=null;
        try {
            ConnectDB postgresql = model.Connection.getInstancePostgresql();
            conn = postgresql.getConnection();
            String sql = "select * from v_categorie_les_plus_vendus limit 3";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Categorie categorie = new Categorie(resultSet.getInt(1), resultSet.getString(2));
                categorie.setNombreVendu(resultSet.getInt(3));
                categories.add(categorie);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (conn!=null) conn.close();
            if (stmt!=null) stmt.close();
        }
        return categories;
    }

    public static void insert(String categorie) throws SQLException {
        Connection conn=null;
        Statement stmt=null;
        try {
            ConnectDB postgresql = model.Connection.getInstancePostgresql();
            conn = postgresql.getConnection();
            String sql = "insert into categorie (nom_categorie) values ('"+categorie+"')";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.executeUpdate("commit");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (conn!=null) conn.close();
            if (stmt!=null) stmt.close();
        }
    }
}
