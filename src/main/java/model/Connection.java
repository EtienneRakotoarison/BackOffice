package model;

import connection.ConnectDB;

import java.sql.SQLException;

public class Connection {
    public static final String langage = "postgresql://";
    public static final String server = "floppy.db.elephantsql.com";
    public static final String database = "wvsusyqf";
    public static final String port = "5432/";
    public static final String username = "wvsusyqf";
    public static final String password = "LzM44nfas4Vrl6XZTVK1s1eMXeXrej7I";

    public static ConnectDB getInstancePostgresql() throws SQLException {
        return new ConnectDB(langage,server,database,port,username,password);
    }
}
