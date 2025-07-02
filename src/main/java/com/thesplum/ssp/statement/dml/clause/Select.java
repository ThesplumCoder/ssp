package com.thesplum.ssp.statement.dml.clause;

import java.util.ArrayList;
import java.util.List;

import com.thesplum.ssp.statement.dml.SelectStatement;
import com.thesplum.ssp.statement.dml.element.Column;
import com.thesplum.ssp.statement.dml.element.Element;
import com.thesplum.ssp.statement.dml.element.ElementParser;

public class Select extends Clause implements ElementParser {
    private static final String KEYWORD = "SELECT";
    
    public Select(String selectClause) {
        setClause(selectClause);
        identifyElements();
    }

    public void setClause(String selectClause) {
        setContent(selectClause);
    }

    /**
     * Identify the information selectors in the clause.
     */
    protected void identifyElements() {
        String[] plainElements = cleanKeyword().split(",");
        List<Element> elements = new ArrayList<>();

        for (String element : plainElements) {
            elements.add(parseElement(element));
        }

        setElements(elements);
    }

    /**
     * Remove the SELECT keyword in the clause.
     * 
     * @return Plain text without SELECT keyword.
     */
    @Override
    protected String cleanKeyword() {
        int initIdx = getContent().indexOf(KEYWORD);

        return getContent().substring(initIdx + KEYWORD.length() + 1, getContent().length());
    }

    /**
     * Recognize if an element is a subquerie or a column.
     * 
     * @param element Text of the element to recognize. 
     * @return An instance of {@see com.thesplum.ssp.statement.dml.SelectStatement} 
     * or {@see com.thesplum.ssp.statement.dml.element.Column}.
     */
    @Override
    public Element parseElement(String element) {
        if (element.startsWith("(") && element.endsWith(")")) {
            return new SelectStatement(element.substring(1, element.length() - 2));
        } else {
            return new Column(element);
        }
    }

}
