package com.thesplum.ssp.automata.implementation;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import com.thesplum.ssp.automata.model.AutomataActions;

public class SpaceAutomata implements AutomataActions {
    private final int INITIAL = 1;
    private final int[] ACCEPT_STATES = {1, 3, 5, 7, 8, 9};
    private final Predicate<String> SET_1 = Pattern.compile("[\\w]").asPredicate();
    private final Predicate<String> SET_2 = Pattern.compile("[.@/$%]").asPredicate();
    private final Predicate<String> SET_3 = Pattern.compile("[<>=!]").asPredicate();
    private final Predicate<String> SET_4 = Pattern.compile("[\\s]").asPredicate();
    private final Predicate<String> SET_5 = Pattern.compile("[']").asPredicate();
    private final Predicate<String> SET_6 = Pattern.compile("[,]").asPredicate();
    private final Predicate<String> SET_7 = Pattern.compile("[;]").asPredicate();

    private int state = INITIAL;

    /**
     * Return the actual state of automata.
     * 
     * @return An id that represent the state.
     */
    public int getState() {
        return state;
    }

    /**
     * Map the input to any following set:
     * 
     * + 1 -> {a-z, A-Z, 0-9}
     * + 2 -> {., @, /, $, %}
     * + 3 -> {<, >, =, !}
     * + 4 -> Whitespace
     * + 5 -> {'}
     * + 6 -> {,}
     * + 7 -> {;}
     * 
     * @param input Character to map.
     * @return An integer that represent the set.
     */
    @Override
    public byte inputToSet(char input) {
        String str = String.valueOf(input);
        if (SET_1.test(str)) {
            return 1;
        } else if (SET_2.test(str)) {
            return 2;
        } else if (SET_3.test(str)) {
            return 3;
        } else if (SET_4.test(str)) {
            return 4;
        } else if (SET_5.test(str)) {
            return 5;
        } else if (SET_6.test(str)) {
            return 6;
        } else if (SET_7.test(str)) {
            return 7;
        } else {
            throw new IllegalStateException("The input doesn't belong any set: " + str);
        }
    }

    @Override
    public void transition(char input) {
        byte mapped = inputToSet(input);
        switch(state) {
            case 1 -> state1(mapped);
            case 2 -> state2(mapped);
            case 3 -> state3(mapped);
            case 4 -> state4(mapped);
            case 5 -> state5(mapped);
            case 6 -> state6(mapped);
            case 7 -> state7(mapped);
            case 8 -> state8(mapped);
            case 9 -> state9(mapped);
            default -> throw new IllegalStateException("The state " + state + " is not supported.");
        }
    }

    @Override
    public boolean isAccept() {
        return IntStream.of(ACCEPT_STATES).anyMatch(x -> x == state);
    }

    private void state1(byte set) {
        switch (set) {
            case 1 -> state = 1;
            case 2 -> state = 1;
            case 3 -> state = 4;
            case 4 -> state = 5;
            case 5 -> state = 7;
            case 6 -> state = 2;
            case 7 -> state = 1;
            default -> throw new UnsupportedOperationException("Transition not supported: " + set);
        }
    }

    private void state2(byte set) {
        switch (set) {
            case 1 -> state = 4;
            case 2 -> state = 4;
            case 3 -> state = 4;
            case 4 -> state = 3;
            case 5 -> state = 4;
            case 6 -> state = 4;
            case 7 -> state = 4;
            default -> throw new UnsupportedOperationException("Transition not supported: " + set);
        }
    }

    private void state3(byte set) {
        switch (set) {
            case 1 -> state = 1;
            case 2 -> state = 1;
            case 3 -> state = 6;
            case 4 -> state = 4;
            case 5 -> state = 7;
            case 6 -> state = 4;
            case 7 -> state = 1;
            default -> throw new UnsupportedOperationException("Transition not supported: " + set);
        }
    }

    private void state4(byte set) {
        switch (set) {
            case 1 -> state = 4;
            case 2 -> state = 4;
            case 3 -> state = 4;
            case 4 -> state = 4;
            case 5 -> state = 4;
            case 6 -> state = 4;
            case 7 -> state = 4;
            default -> throw new UnsupportedOperationException("Transition not supported: " + set);
        }
    }

    private void state5(byte set) {
        switch (set) {
            case 1 -> state = 1;
            case 2 -> state = 1;
            case 3 -> state = 6;
            case 4 -> state = 4;
            case 5 -> state = 7;
            case 6 -> state = 4;
            case 7 -> state = 1;
            default -> throw new UnsupportedOperationException("Transition not supported: " + set);
        }
    }

    private void state6(byte set) {
        switch (set) {
            case 1 -> state = 4;
            case 2 -> state = 4;
            case 3 -> state = 6;
            case 4 -> state = 5;
            case 5 -> state = 4;
            case 6 -> state = 4;
            case 7 -> state = 4;
            default -> throw new UnsupportedOperationException("Transition not supported: " + set);
        }
    }

    private void state7(byte set) {
        switch (set) {
            case 1 -> state = 7;
            case 2 -> state = 7;
            case 3 -> state = 7;
            case 4 -> state = 7;
            case 5 -> state = 8;
            case 6 -> state = 7;
            case 7 -> state = 7;
            default -> throw new UnsupportedOperationException("Transition not supported: " + set);
        }
    }

    private void state8(byte set) {
        switch (set) {
            case 1 -> state = 4;
            case 2 -> state = 4;
            case 3 -> state = 4;
            case 4 -> state = 9;
            case 5 -> state = 4;
            case 6 -> state = 2;
            case 7 -> state = 1;
            default -> throw new UnsupportedOperationException("Transition not supported: " + set);
        }
    }
    
    private void state9(byte set) {
        switch (set) {
            case 1 -> state = 1;
            case 2 -> state = 1;
            case 3 -> state = 6;
            case 4 -> state = 4;
            case 5 -> state = 7;
            case 6 -> state = 4;
            case 7 -> state = 1;
            default -> throw new UnsupportedOperationException("Transition not supported: " + set);
        }
    }
}