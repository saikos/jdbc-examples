package org.afdemp.cb6.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 This example demonstrates the usage of the primary 
 JDBC API classes (Connection, Statement, ResultSet) 
 that are required to fetch data from a database.
*/
public class SimpleExample {
    
    public static void main(String[] args) {
        
        try {
            
            //If the driver does not support JDBC 4.0
            //we need to initialize the driver using the 
            //following:
            //Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
            //Driver driver = (Driver)driverClass.newInstance();
            //DriverManager.registerDriver(driver);
            
            //If the driver does support JDBC 4.0 then
            //we can skip the above initialization
            String user     = "eshopAdmin";
            String password = "eshopAdmin123";
            
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eshop?characterEncoding=utf-8&autoReconnect=true",
                user,
                password
            );
            
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * from user order by id desc");                        
            
            while(rs.next()) {
                Long id      = rs.getLong(1);
                String email = rs.getString(2);
                String fName = rs.getString(3);
                
                System.out.println("User: " + id + ", " + email + ", " + fName);
            }                        
        }
        catch(Throwable t) {
            System.out.println(t.getMessage());
            t.printStackTrace();
        }
                
        
    }
    
}
