package com.thesplum.ssp.statement.dml.clause;

import java.util.ArrayList;
import java.util.List;

import com.thesplum.ssp.statement.dml.element.Table;

public class From extends Clause {
    private static final String KEYWORD = "FROM";

    public From(String fromClause) {
        setClause(fromClause);
        identifyElements();
    }

    public void setClause(String fromClause) {
        if(fromClause != null) {
            setContent(fromClause);
        }
    }

    /**
     * Identify the tables in clause.
     * 
     * Set the found tables internally.
     */
    protected void identifyElements() {
        String[] plainElements = cleanKeyword().split(",");
        List<Table> tables = new ArrayList<>();

        for (String table : plainElements) {
            tables.add(new Table(table));
        }

        setElements(tables);
    }

    /**
     * Remove the FROM keyword in the clause.
     * 
     * @return Plain text without FROM keyword.
     */
    protected String cleanKeyword() {
        int initIdx = getContent().indexOf(KEYWORD);

        return getContent().substring(initIdx + KEYWORD.length() + 1, getContent().length());
    }
}
