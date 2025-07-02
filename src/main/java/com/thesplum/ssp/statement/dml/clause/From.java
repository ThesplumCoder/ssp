package com.thesplum.ssp.statement.dml.clause;

import java.util.ArrayList;
import java.util.List;

import com.thesplum.ssp.statement.dml.SelectStatement;
import com.thesplum.ssp.statement.dml.element.Element;
import com.thesplum.ssp.statement.dml.element.ElementParser;
import com.thesplum.ssp.statement.dml.element.Table;

public class From extends Clause implements ElementParser {
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
        List<Element> elements = new ArrayList<>();

        for (String table : plainElements) {
            elements.add(parseElement(table));
        }

        setElements(elements);
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

    /**
     * Recognize if an element is a subquerie or a table.
     * 
     * @param element Text of the element to recognize.
     * @return An instance of {@see com.thesplum.ssp.statement.dml.SelectStatement} 
     * or {@see com.thesplum.ssp.statement.dml.element.Table}.
     */
    @Override
    public Element parseElement(String element) {
        if (element.startsWith("(") && element.endsWith(")")) {
            return new SelectStatement(element.substring(1, element.length() - 2));
        } else {
            return new Table(element);
        }
    }
}