package com.thesplum.ssp.automata.model;

import java.util.List;

public final class ResultAutomata {
    private boolean acceptance;
    private List<State> history;

    public ResultAutomata(boolean accept, List<State> history) {
        setAcceptance(accept);
        setHistory(history);
    }

    public boolean getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public List<State> getHistory() {
        return history;
    }

    public void setHistory(List<State> history) {
        if (history != null && !history.isEmpty()) {
            this.history = history;
        } else {
            throw new NullPointerException();
        }
    }
}
