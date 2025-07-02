package com.thesplum.ssp.statement.dml.clause;

import java.util.List;

import com.thesplum.ssp.statement.SqlPlain;
import com.thesplum.ssp.statement.dml.element.Element;

public abstract class Clause extends SqlPlain {

    private List<Element> elements;


    protected void setElements(List<Element> elements) {
        if (elements != null) {
            this.elements = elements;
        }
    }

    protected List<Element> getElements() {
        return elements;
    }
    
    /**
     * Permits set a clause using plain SQL text. 
     * 
     * Each clause have to responsability to confirms if the fragment fulfills 
     * the specific restrictions.
     * 
     * @param clause plain SQL text that math to the clause fragment.
     */
    protected abstract void setClause(String clause);

    /**
     * Identify the allowed subelements present in particular clause.
     */
    protected abstract void identifyElements();


    /**
     * Remove the specific keyword that identifies the clause.
     * 
     * @return String without clause keyword.
     */
    protected abstract String cleanKeyword();
}
