package com.thesplum.ssp.parser.statement.exception;

/**
 * Represent the common mistakes that occur in the statement treatment.
 * 
 * @author Anderson Acuña
 */
public final class BadStructureStatement extends RuntimeException {
    
    private BadStructureStatement(String msg) {
        super(msg);
    }

    /**
     * Occurs when try to use a statement that hasn't a type defined.
     * 
     * @return Exception with message.
     */
    public static BadStructureStatement getNoTypeStatement() {
        return new BadStructureStatement("The statement hasn't a type defined.");
    }
}
