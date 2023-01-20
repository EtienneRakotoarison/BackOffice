package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB
{
    String url;
    String langage;
    String server;
    String username;
    String password;
    String database;
    String port;
    Connection connection;
    public ConnectDB(String langage, String server, String database, String port, String username, String password) throws SQLException
    {
        this.username=username;
        this.server=server;
        this.password=password;
        this.database=database;
        this.port=port;
        this.langage=langage;
        initConnection(this.langage,this.server,this.database,this.port,this.username, this.password);
    }
    public ConnectDB(String url) throws SQLException
    {
        this.url=url;
        initConnection(this.url);
    }
    public void initConnection(String langage, String server, String database, String port, String username, String password) throws SQLException
    {
        connection=DriverManager.getConnection("jdbc:"+langage+server+"/"+database,username,password);
    }
    public void initConnection(String url) throws SQLException
    {
        connection=DriverManager.getConnection(url);
    }
    public Connection getConnection()
    {
        return connection;
    }
    public void closeConnection() throws SQLException
    {
        connection.close();
    }
}