package com.thesplum.ssp.statement;

import java.util.List;

import com.thesplum.ssp.parser.tokenizer.Token;

/**
 * Establishes the contracts for identify which is the statement.
 * 
 * @author Anderson Acuña
 */
public interface StatementParser {
    
    /**
     * Using the token list identifies the corresponding statement.
     * 
     * @param tokens Token list of the statement.
     * @return The corresponding statement struct.
     */
    Statement identifyStatement(List<Token> tokens);
}
