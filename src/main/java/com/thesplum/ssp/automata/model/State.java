package com.thesplum.ssp.automata.model;

/**
 * Model the state of the automata.
 *  
 * @author Anderson Acuña.
 */
public final class State {
    private int id;
    private char input;

    public State(int id, char input) {
        setId(id);
        setInput(input);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getInput() {
        return input;
    }

    public void setInput(char input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "[id: " + id + ", input: " + input + "]";
    }
}
