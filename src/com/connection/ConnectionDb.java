package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;  

public class ConnectionDb 
{     
    private static Connection con;

    public static Connection Connection() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://localhost/mvc_db";
                String user = "root";
                String password = "";

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

                con = DriverManager.getConnection(url, user, password);
            } catch (SQLException exception) {
                System.out.println("Gagal Koneksi Ke Database");
            }
        }
        return con;

    }
    
    
/*
    
    static Connection con;
    
    public static Connection connection() 
    {        
        if (con == null) 
        {
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("mvc_db");
            data.setUser("root");
            data.setPassword("");
         
            try 
            {
                con = data.getConnection();
            }catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        }      
        return con;
    }  
 
    */
    

}