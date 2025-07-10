package com.thesplum.ssp.parser.text.tokenizer;

/**
 * Conforms the method to identify tokens after use of tokenizer.
 * 
 * @author Anderson Acuña
 */
public interface TokenParser {

    /**
     * Test if the text match to keyword.
     * 
     * @param text Text to test.
     * @return If the text match with a keyword it returns true, else return 
     * false.
     */
    boolean isKeyword(String text);

    /**
     * Test if the text match to function.
     * 
     * @param text Text to test.
     * @return If the text match with a function it returns true, else return 
     * false.
     */
    boolean isFunction(String text);

    /**
     * Test if the text match to symbol.
     * 
     * @param text Text to test.
     * @return If the text match with a symbol it returns true, else return 
     * false.
     */
    boolean isSymbol(String text);
    
    /**
     * Identify the token that represent the plain text.
     * 
     * @param text Text to match with token.
     * @return A token that represent the plain text.
     */
    Token identifyToken(String text);
}
