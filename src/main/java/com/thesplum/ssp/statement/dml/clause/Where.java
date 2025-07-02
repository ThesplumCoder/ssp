package com.thesplum.ssp.statement.dml.clause;

import java.util.ArrayList;
import java.util.List;

import com.thesplum.ssp.statement.dml.SelectStatement;
import com.thesplum.ssp.statement.dml.element.Element;
import com.thesplum.ssp.statement.dml.element.ElementParser;
import com.thesplum.ssp.statement.dml.element.Filter;

public class Where extends Clause implements ElementParser {
    private static final String KEYWORD = "WHERE";

    public Where(String whereClause) {
        setClause(whereClause);
        identifyElements();
    }

    @Override
    public void setClause(String whereClause) {
        if(whereClause != null) {
            setContent(whereClause);
        }
    }

    /**
     * Identify filters on clause.
     */
    @Override
    protected void identifyElements() {
        String[] plainElements = cleanKeyword().split(",");
        List<Element> filters = new ArrayList<>();
        
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
    @Override
    protected String cleanKeyword() {
        int initIdx = getContent().indexOf(KEYWORD);

        return getContent().substring(initIdx + KEYWORD.length() + 1, getContent().length());
    }

    /**
     * Recognize if an element is a subquerie or a filter.
     * 
     * @param element Texto of the element to recognize.
     * @return An instance of {@see com.thesplum.ssp.statement.dml.SelectStatement} 
     * or {@see com.thesplum.ssp.statement.dml.element.Filter}.
     */
    @Override
    public Element parseElement(String element) {
        if (element.startsWith("(") && element.endsWith(")")) {
            return new SelectStatement(element.substring(1, element.length() - 2));
        } else {
            return new Filter(element);
        }
    }
}
