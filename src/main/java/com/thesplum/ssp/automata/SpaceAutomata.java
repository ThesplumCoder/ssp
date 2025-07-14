package com.thesplum.ssp.automata;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class SpaceAutomata implements AutomataActions {
    private final int INITIAL = 1;
    private final int[] ACCEPT_STATES = {1, 2, 4};
    
    private int state = INITIAL;
    private Predicate<String> aSet = Pattern.compile("[\\w]").asPredicate();
    private Predicate<String> sSet = Pattern.compile("[\\W&&[^'\\s]]").asPredicate();

    @Override
    public void transition(char input) {
        String str = String.valueOf(input);
        switch(state) {
            case 1 -> state1(str);
            case 2 -> state2(str);
            case 3 -> state3(str);
            case 4 -> state4(str);
            default -> throw new IllegalStateException("The state " + state + " is not supported.");
        }
    }

    @Override
    public boolean isAccept() {
        return IntStream.of(ACCEPT_STATES).anyMatch(x -> x == state);
    }

    private void state1(String input) {
        if (input.equals(" ")) {
            state = 2;
        } else if (input.equals("'")) {
            state = 4;
        } else {
            state = 1;
        }
    }

    private void state2(String input) {
        if (input.equals(" ")) {
            state = 3;
        } else if (input.equals("'")) {
            state = 4;
        } else {
            state = 1;
        }
    }

    private void state3(String input) {
        if (input.equals(" ")) {
            state = 3;
        } else if (input.equals("'")) {
            state = 4;
        } else {
            state = 1;
        }
    }

    private void state4(String input) {
        if (input.equals("'")) {
            state = 1;
        } else {
            state = 4;
        }
    }
    
}
