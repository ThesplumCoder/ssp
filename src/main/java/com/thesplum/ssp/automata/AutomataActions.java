package com.thesplum.ssp.automata;

public interface AutomataActions {

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
