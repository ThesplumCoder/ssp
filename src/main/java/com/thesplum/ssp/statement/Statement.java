package com.thesplum.ssp.statement;

import java.util.List;
import com.thesplum.ssp.statement.dml.clause.Clause;

public abstract class Statement extends SqlPlain {

    private List<Clause> clauses;
    
    public Statement(String plainStatement) {
        setContent(plainStatement);
    }

    public void setClauses(List<Clause> clauses) {
        if (clauses != null) {
            this.clauses = clauses;
        }
    }

    public List<Clause> getClauses() {
        return clauses;
    }
}
