package com.thesplum.ssp.parser.statement;

import com.thesplum.ssp.statement.Statement;

/**
 * Establishes the contracts for identify which is the statement.
 * 
 * @author Anderson Acuña
 */
public interface StatementParser {
    
    /**
     * Using the token list identifies the corresponding statement.
     * 
     * This method only support the action to set the type in the inner 
     * statement object in the parser.
     * 
     * @param tokens Token list of the statement.
     */
    void identifyStatement();

    /**
     * Parse the statement to its elements.
     * 
     * Set the internal elements of the statement.
     * 
     * @return Statement with corresponding elements.
     * @implNote If the specific structure doesn't match with the structure of 
     * tokens, the method will throws {@see com.thesplum.ssp.statement.exception.BadStructureStatement}.
     */
    Statement parseStatement();
}
