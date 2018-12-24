package org.afdemp.cb6.jdbc.op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductInsertOperation implements InsertOperation {
    
    private static final String SQL_PRODUCT_INSERT = "insert into product values (default, ?, ?)";
    
    private final String name;
    private final Double price;
    
    public ProductInsertOperation(String name, Double price) {
        this.name  = name;
        this.price = price;
    }        

    @Override
    public Long execute(Connection con) throws SQLException, SQLOperationFailedException {
        PreparedStatement ps = con.prepareStatement(SQL_PRODUCT_INSERT, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setDouble(2, price);
        int cnt = ps.executeUpdate();
        if (cnt == 1) {
            //A new row has been inserted, let's get its key
            
            ResultSet rs = ps.getGeneratedKeys();
            //The generated keys are provided as a result set
            if (rs.next()) {
                //This is a single cell (1 x 1 result set)
                return rs.getLong(1);
            }
        }
        
        
        throw new SQLOperationFailedException("Insert product query failed: either a new row has not been added or the generated key has not been retrieved");
    }        
}
