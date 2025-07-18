package com.thesplum.ssp.automata.implementation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.thesplum.ssp.automata.model.ResultAutomata;
import com.thesplum.ssp.automata.model.State;

public class SpaceAutomataOperatorTest {
    private SpaceAutomataOperator sao = new SpaceAutomataOperator();

    @Test
    void historyTest() {
        List<String> positiveTest = Arrays.asList("SELECT a, b, c FROM Words WHERE a LIKE 'Carl%';");

        for (String s : positiveTest) {
            ResultAutomata processed = sao.history(s);
            assertTrue(processed.getAcceptance());
        }
    }
}
