package model;

import connection.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Rechargement {
    int idrechargement;
    double valeur;
    int idutilisateur;
    String nomutilisateur;
    int statut;

    public Rechargement(int idrechargement, double valeur, int idutilisateur, String nomutilisateur, int statut) {
        this.idrechargement = idrechargement;
        this.valeur = valeur;
        this.idutilisateur = idutilisateur;
        this.nomutilisateur = nomutilisateur;
        this.statut = statut;
    }

    public Rechargement() {
    }

    public int getIdrechargement() {
        return idrechargement;
    }

    public void setIdrechargement(int idrechargement) {
        this.idrechargement = idrechargement;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getNomutilisateur() {
        return nomutilisateur;
    }

    public void setNomutilisateur(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public static ArrayList<Rechargement> getRechargementsAdmin() throws SQLException {
        ArrayList<Rechargement> rechargementAdmin = new ArrayList<Rechargement>();
        java.sql.Connection conn=null;
        PreparedStatement stmt=null;
        try {
            ConnectDB postgresql = model.Connection.getInstancePostgresql();
            conn = postgresql.getConnection();
            String sql = "select * from v_rechargement_admin where statut=0 order by id_rechargement;";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next())
            {
                Rechargement rechargement = new Rechargement(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5));
                rechargementAdmin.add(rechargement);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (conn!=null) conn.close();
            if (stmt!=null) stmt.close();
        }
        return rechargementAdmin;
    }

    public static void recharger(String idrechargement, double valeur, String idutilisateur) throws SQLException {
        java.sql.Connection conn=null;
        Statement stmt=null;
        try {
            ConnectDB postgresql = model.Connection.getInstancePostgresql();
            conn = postgresql.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate("update rechargement set statut=1 where id_rechargement="+idrechargement);
            stmt.executeUpdate("update utilisateur set valeur=valeur+"+valeur+" where id_utilisateur="+idutilisateur);
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

    public static void refuser(String idrechargement) throws SQLException {
        java.sql.Connection conn=null;
        Statement stmt=null;
        try {
            ConnectDB postgresql = model.Connection.getInstancePostgresql();
            conn = postgresql.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate("update rechargement set statut=2 where id_rechargement="+idrechargement);
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
