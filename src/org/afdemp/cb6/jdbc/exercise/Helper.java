package org.afdemp.cb6.jdbc.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.afdemp.cb6.jdbc.op.ProductInsertOperation;
import org.afdemp.cb6.jdbc.op.SQLOperation;

class Helper {
    
    static boolean isValidTable(String table) {
        boolean found = false;
        for (String validTable : Constants.VALID_TABLES) {
            if (validTable.equalsIgnoreCase(table)) {
                found = true;
                break;
            }
        }
        return found;
    }
    
    static boolean isValidOperation(String operation) {
        boolean found = false;
        for (String validOperation : Constants.VALID_OPERATIONS) {
            if (validOperation.equalsIgnoreCase(operation)) {
                found = true;
                break;
            }
        }
        return found;
    }
    
    static Connection openConnection() throws SQLException {
        
        String user     = "eshopAdmin";
        String password = "eshopAdmin123";

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/eshop?serverTimezone=UTC&characterEncoding=utf-8&autoReconnect=true",
            user,
            password
        );                       
    }
       
    static SQLOperation createSQLOperation(String table, String operation, String[] otherArgs) {                
        //table and operation are not null here
        if (table.equalsIgnoreCase(Constants.PRODUCT)) {
            if (operation.equalsIgnoreCase(Constants.INSERT)) {
                return createProductInsert(otherArgs);
            }
            //...
        }
        else if (table.equalsIgnoreCase(Constants.USER)) {
            //...
        }
        else {
            //We are sure this is the FAV case here
            //...
        }       
        throw new RuntimeException("Not implemented yet");
    }    
    
    static ProductInsertOperation createProductInsert(String[] otherArgs) {
        if (otherArgs.length == 2) {            
            String name  = otherArgs[0];
            Double price = null;
            try {
                price = Double.parseDouble(otherArgs[1]);
            }
            catch(NumberFormatException nfe) {
                throw new IllegalArgumentException("Price is invalid: " + otherArgs[1]);
            }
            return new ProductInsertOperation(name, price);
        }
        else {
            throw new IllegalArgumentException("Product insert arguments are invalid");
        }
    }
}
