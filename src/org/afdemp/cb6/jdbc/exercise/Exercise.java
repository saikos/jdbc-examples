package org.afdemp.cb6.jdbc.exercise;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import org.afdemp.cb6.jdbc.op.InsertOperation;
import org.afdemp.cb6.jdbc.op.SQLOperation;
import org.afdemp.cb6.jdbc.op.SQLOperationFailedException;

public class Exercise {
    
    public static final void main(String[] args) {
        
        System.out.println("Executing - " + Arrays.asList(args));
        
        try {
            String table     = null;
            String operation = null;

            if (args.length > 0) {
                table = args[0];
                if (!Helper.isValidTable(table)) {
                    throw new IllegalArgumentException("Table not valid: " + table);
                }            
            }

            if (args.length > 1) {
                operation = args[1];
                if (!Helper.isValidOperation(operation)) {
                    throw new IllegalArgumentException("Operation not valid: " + table);
                }
            }

            if (args.length > 2) {                               
                //get the remaining args (from the 3rd and up to the end of the array)   
                String[] otherArgs = new String[args.length - 2];
                System.arraycopy(args, 2, otherArgs, 0, otherArgs.length);
                
                //let's connect to the database
                Connection con = Helper.openConnection();
                
                //create the SQL operation based on the table, operation and otherArgs
                SQLOperation op = Helper.createSQLOperation(table, operation, otherArgs);
                
                Object res = op.execute(con);
                if (op instanceof InsertOperation) {
                    System.out.println("Data insertion was successful, the new id is: " + res);
                }
                else {
                    System.out.println("Data update/deletion was successful, " + res + " rows were affected");
                }
                
                System.exit(0);                                
            }

            throw new IllegalArgumentException("Data missing");
        }        
        catch(SQLException sqle) {
            System.out.println("SQL error (JDBC) - " + sqle.getMessage());
            System.exit(-1);
        }
        catch(SQLOperationFailedException sofe) {
            System.out.println("SQL error (our code) - " + sofe.getMessage());
            System.exit(-2);
        }        
        catch(IllegalArgumentException iae) {
            System.out.println("User input error - " + iae.getMessage());
            System.exit(-3);
        }        
        catch(Throwable t) {
            System.out.println("Unknown error - " + t.getMessage());
            System.exit(-4);
        }
        
    }
    
}
