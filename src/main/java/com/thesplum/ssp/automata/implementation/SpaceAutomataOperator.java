package com.thesplum.ssp.automata.implementation;

import java.util.ArrayList;
import java.util.List;

import com.thesplum.ssp.automata.model.AutomataOperator;
import com.thesplum.ssp.automata.model.ResultAutomata;
import com.thesplum.ssp.automata.model.State;

public final class SpaceAutomataOperator implements AutomataOperator<SpaceAutomata> {

    @Override
    public ResultAutomata history(String str) {
        char[] chars = str.toCharArray();
        List<State> hist = new ArrayList<>();
        SpaceAutomata sa = new SpaceAutomata();

        for (char c : chars) {
            sa.transition(c);
            hist.add(new State(sa.getState(), c));
        }

        return new ResultAutomata(sa.isAccept(), hist);
    }
    
}
