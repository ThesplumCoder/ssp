package com.thesplum.ssp.automata.model;

public interface AutomataActions {

    /**
     * Take an input to map a set in the context of automata.
     * 
     * @param input Token to use for mapping.
     * @return An integer that represent a set.
     */
    byte inputToSet(char input);

    /**
     * Take an input and internally set the state of the automata.
     * 
     * @param input Token to use for transition.
     */
    void transition(char input);

    /**
     * Confirms if the automata is in state of acceptation.
     * 
     * @return If actual state is accept state returns true, otherwise returns 
     * false.
     */
    boolean isAccept();
}
