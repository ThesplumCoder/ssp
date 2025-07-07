package com.thesplum.ssp.parser.tokenizer;

/**
 * Conforms the method to identify tokens after use of tokenizer.
 * 
 * @author Anderson Acuña
 */
public interface TokenParser {

    boolean isKeyword(String text);

    boolean isFunction(String text);

    boolean isSymbol(String text);
    
    Token identifyToken(String text);
}
