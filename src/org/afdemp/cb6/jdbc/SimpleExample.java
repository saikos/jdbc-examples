package org.afdemp.cb6.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 This example demonstrates the usage of the primary 
 JDBC API classes (DriverManager, Connection, Statement, 
 PreparedStatement, ResultSet) that are required to 
 manipulate data held in a database.
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
                "jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC&characterEncoding=utf-8&autoReconnect=true",
                user,
                password
            );                       
            
            String newFullName = "Πασπαράκης, Γιώργος";
            String newEmail = "geospa@cb6";
            
            /*
            NEVER, EVER (EVER) DO THIS                                    
            String sqlUpdate = "update user " +
                               "set fullname = '" + newFullName + "', " +
                               "email = '" + newEmail + "' " +
                               "where id = 6";                                
            
            int cnt = stmt.executeUpdate(sqlUpdate);
            System.out.println(cnt);
            */
            
            //Always use PreparedStatement to execute 
            //dynamic / parameterized queries
            String sqlUpdate = "update user set fullname = ?, email = ? where id = 6";            
            PreparedStatement ps = con.prepareStatement(sqlUpdate);
            
            ps.setString(1, newFullName);
            ps.setString(2, newEmail);
            
            int cnt = ps.executeUpdate();
            System.out.println("Lines afffected: " + cnt);
            
            //You can use Statement to execute "static" queries
            //(non-dynamic, ones that accept no parameters)
            Statement stmt = con.createStatement();        
            ResultSet rs = stmt.executeQuery("SELECT * from user order by id desc");                        
            
            while(rs.next()) {
                Long id      = rs.getLong(1);
                String email = rs.getString(2);
                String fName = rs.getString(3);
                
                System.out.println("User: " + id + ", " + email + ", " + fName);
            }                        
            
            //Finally, you can use PreparedStatement
            //to execute the "static" queries, too
            PreparedStatement ps2 = con.prepareStatement("select * from product");
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()) {
                Long id      = rs2.getLong(1);
                String name  = rs2.getString(2);
                Double price = rs2.getDouble(3);
                        
                System.out.println("Product: " + id + ", " + name + ", " + price);       
            }  
            
            //Always use PreparedStatement for
            //accessing a database, if possible :)
        }
        catch(Throwable t) {
            System.out.println(t.getMessage());
            t.printStackTrace();
        }
                
        
    }
    
}
