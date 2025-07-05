package com.thesplum.ssp.parser;

import java.util.ArrayList;
import java.util.List;
import com.thesplum.ssp.statement.dml.clause.Clause;
import com.thesplum.ssp.statement.dml.clause.From;
import com.thesplum.ssp.statement.dml.clause.Select;
import com.thesplum.ssp.statement.dml.clause.Where;

public final class Splitter {

    /**
     * Split the plain SQL query in three main clauses: SELECT, FROM and WHERE.
     * @param query Plain SQL query.
     * @return List of found clauses.
     */
    public static List<Clause> querySplitter(String query) {
        ArrayList<Clause> clauses = new ArrayList<>();
        
        if (query != null && !query.isBlank()) {
            String[] parts = query.split(" ");

            String stackClause = parts[0];
            int count = 2;
            do {
                String part = parts[count - 1];
                if (part.equals("SELECT") || part.equals("FROM") || part.equals("WHERE")) {
                    clauses.add(clauseConstructor(stackClause));
                    stackClause = part;
                } else {
                    stackClause += " " + part;
                }
                count += 1;
                if (count > parts.length) {
                    clauses.add(clauseConstructor(stackClause));
                }
            } while (count <= parts.length);
        }
        return clauses;
    }

    /**
     * Identify the object clause to math with plain clause string. 
     * @param plainClause Plain clause string. 
     * @return An object clause.
     */
    public static Clause clauseConstructor(String plainClause) {
        Clause res;

        if (plainClause.startsWith("SELECT")) {
            res = new Select(plainClause);
        } else if (plainClause.startsWith("FROM")) {
            res = new From(plainClause);
        } else {
            res = new Where(plainClause);
        }

        return res;
    }
}
