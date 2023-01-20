package model;

import connection.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    int idadmin;
    String email;
    String mdp;

    public Admin(int idadmin, String email, String mdp) {
        this.idadmin = idadmin;
        this.email = email;
        this.mdp = mdp;
    }

    public Admin() {
    }

    public int getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static Admin login(Admin admin) throws Exception {
        Connection conn=null;
        PreparedStatement stmt=null;
        try {
            ConnectDB postgresql = model.Connection.getInstancePostgresql();
            conn = postgresql.getConnection();
            String sql = "select * from admin where email='"+admin.getEmail()+"' and mdp='"+admin.getMdp()+"'";
            stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) admin.setIdadmin(resultSet.getInt(1));
        }
        catch(Exception e){
            System.out.println("Exception_"+e.getMessage());
        }
        finally {
            if (conn!=null) conn.close();
            if (stmt!=null) stmt.close();
        }
        if(admin.getIdadmin() == 0) throw new Exception("Non connecté");
        return admin;
    }
}
