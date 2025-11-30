package com.thesplum.ssp.automata.implementation;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.thesplum.ssp.automata.model.ResultAutomata;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SpaceAutomataOperatorTest {
    private SpaceAutomataOperator sao = new SpaceAutomataOperator();

    @Test
    void historyTest() {
        List<String> positiveTest = Arrays.asList(
            "SELECT a, b, c FROM Words WHERE a LIKE 'Carl%';", 
            "car.tires > 4", 
            "SELECT a, b, c FROM Words WHERE a = 'árbol'"
        );

        List<String> negativeTest = Arrays.asList(
            "car.seats > 4    AND car.tires =  4", 
            "car.owner =  'William Shakespeare' AND car.registry = '  AAA-111'", 
            "SELECT name FROM Cars c WHERE model='Newer sport'"
        );

        for (String s : positiveTest) {
            ResultAutomata processed = sao.history(s);
            assertTrue(processed.getAcceptance());
        }

        for (String s : negativeTest) {
            ResultAutomata processed = sao.history(s);
            assertFalse(processed.getAcceptance());
        }
    }
}
