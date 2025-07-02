package com.thesplum.ssp.statement.dml.clause;

import java.util.ArrayList;
import java.util.List;

import com.thesplum.ssp.statement.dml.element.Filter;

public class Where extends Clause {
    private static final String KEYWORD = "WHERE";

    public Where(String whereClause) {
        setClause(whereClause);
        identifyElements();
    }

    public void setClause(String whereClause) {
        if(whereClause != null) {
            setContent(whereClause);
        }
    }

    /**
     * Identify filters on clause.
     */
    protected void identifyElements() {
        String[] plainElements = cleanKeyword().split(",");
        List<Filter> filters = new ArrayList<>();
        
        for (String filter : plainElements) {
            filters.add(new Filter(filter));
        }

        setElements(filters);
    }

    /**
     * Remove the WHERE keyword in the clause.
     * 
     * @return Plain text without WHERE keyword.
     */
    protected String cleanKeyword() {
        int initIdx = getContent().indexOf(KEYWORD);

        return getContent().substring(initIdx + KEYWORD.length() + 1, getContent().length());
    }
}
