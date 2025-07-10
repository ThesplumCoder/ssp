package com.thesplum.ssp.statement;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Represent each type of possible statement.
 * 
 * @author Anderson Acuña
 */
public enum StatementType {
    SELECT("SELECT"),
    INSERT("INSERT"), 
    UPDATE("UPDATE"), 
    DELETE("DELETE");

    private String initialKeyword;

    StatementType(String initialKeyword) {
        this.initialKeyword = initialKeyword;
    }

    public String getInitKeyword() {
        return initialKeyword;
    }

    public Predicate<String> tester() {
        return Pattern.compile(getInitKeyword(), 2).asPredicate();
    }
}
