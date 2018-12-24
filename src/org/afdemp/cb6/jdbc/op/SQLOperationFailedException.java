package org.afdemp.cb6.jdbc.op;

/**
 * A custom exception used by SQLOperations to report that something 
 * failed in `our` code has failed.
 */
public class SQLOperationFailedException extends Exception{

    public SQLOperationFailedException(String msg) {
        super(msg);
    }
    
}
