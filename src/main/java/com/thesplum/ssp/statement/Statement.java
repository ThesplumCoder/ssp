package com.thesplum.ssp.statement;

import java.util.ArrayList;
import java.util.List;

import com.thesplum.ssp.parser.text.tokenizer.Token;
import com.thesplum.ssp.statement.dml.clause.Clause;

/**
 * Represent the base for any statement.
 * 
 * @author Anderson Acuña
 */
public final class Statement {
    private StatementType type;
    private List<Clause> clauses;
    private List<Token> tokens;

    /**
     * Construct a statement by tokens.
     * 
     * @param tokens List of tokens.
     */
    public Statement(List<Token> tokens) {
        setTokens(tokens);
        clauses = new ArrayList<>();
    }

    /**
     * Set the internal clauses.
     * 
     * Direct use is not recommended, because its a support to subclass 
     * implementations.
     * .
     * @param clauses Statement clauses list.
     */
    protected void setClauses(List<Clause> clauses) {
        if (clauses != null && !clauses.isEmpty()) {
            this.clauses = clauses;
        } else {
            throw new NullPointerException("The list of clauses for statement can't be null nor empty.");
        }
    }

    /**
     * Get the internal clauses.
     * 
     * @return Statement clauses list.
     */
    public List<Clause> getClauses() {
        return clauses;
    }

    /**
     * Set the list of tokens that are the base of statement.
     * 
     * @param tokens Statement tokens list.
     */
    public void setTokens(List<Token> tokens) {
        if (tokens != null && !tokens.isEmpty()) {
            this.tokens = tokens;
        } else {
            throw new NullPointerException("The list of token for statement can't be null nor empty.");
        }
    }

    /**
     * Get the tokens.
     * 
     * @return Statement tokens list.
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Set the type of statement.
     * 
     * @see com.thesplum.ssp.statement.StatementType
     * @param statementType The type of statement.
     */
    public void setType(StatementType statementType) {
        if (statementType != null) {
            type = statementType;
        } else {
            throw new NullPointerException("The statement can't be null");
        }
    }

    /**
     * Get the type of statement.
     * 
     * @return An StatementType that represents the type of statement.
     */
    public StatementType getType() {
        return type;
    }
}
