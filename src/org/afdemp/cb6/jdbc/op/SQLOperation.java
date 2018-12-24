package org.afdemp.cb6.jdbc.op;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Execute a single Insert, Update or Delete SQL query and return its result. 
 */
public interface SQLOperation<T> {
        
    T execute(Connection con) throws SQLException, SQLOperationFailedException;
        
}
