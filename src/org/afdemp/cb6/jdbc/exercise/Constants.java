package org.afdemp.cb6.jdbc.exercise;

import java.util.Arrays;
import java.util.List;

public class Constants {
    
    static final String PRODUCT = "PROD";
    static final String USER    = "USER";
    static final String FAV     = "FAV";
    
    static final String INSERT = "INSERT";
    static final String UPDATE = "UPDATE";
    static final String DELETE = "DELETE";
    
    static final List<String> VALID_TABLES = Arrays.asList(
        PRODUCT,
        USER,
        FAV
    );
    
    static final List<String> VALID_OPERATIONS = Arrays.asList(
        INSERT,
        UPDATE,
        DELETE
    );        
    
}
