package com.thesplum.ssp.automata.model;

/**
 * Group general functions that made with automatas.
 * 
 * @author Anderson Acuña
 */
public interface AutomataOperator<A> {
    
    /**
     * Use the input and the automata to produce the history of transitions.
     * 
     * @param str Full string for pass to automata.
     * @return State for each token of the string.
     */
    ResultAutomata history(String str);
}
