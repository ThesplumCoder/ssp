package com.thesplum.ssp.process;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizeTest {

    @Test
    void parametrizeTest() {
        List<String> tests = Arrays.<String>asList(
            "car.id = 1", 
            "car.seats > 4 AND car.tires = 4", 
            "car.owner =      'William Shakespeare' AND car.registry    = 'AAA-111'", 
            "SELECT name FROM Cars c WHERE model='Newer sport'"
        ); 
        List<String> expected = Arrays.<String>asList(
            "car.id = ?", 
            "car.seats > ? AND car.tires = ?", 
            "car.owner = ? AND car.registry = ?", 
            "SELECT name FROM Cars c WHERE model = ?");

        Iterator<String> testsIter = tests.iterator();
        Iterator<String> expectIter = expected.iterator();
        while(testsIter.hasNext() && expectIter.hasNext()) {
            String parametrized = Parameterize.parameterize(testsIter.next());
            assertEquals(expectIter.next(), parametrized);            
        }
    }

    @Test
    void adjustSpacesTest() {
        List<String> tests = Arrays.<String>asList(
            "car.id  = 1",
            "id, owner , tires",
            "car.seats > 4    AND car.tires =  4", 
            "car.owner =  'William Shakespeare' AND car.registry = '  AAA-111'", 
            "SELECT name FROM Cars c WHERE model='Newer sport'"
        );
        List<String> expected = Arrays.<String>asList(
            "car.id = 1",
            "id, owner, tires",
            "car.seats > 4 AND car.tires = 4", 
            "car.owner = 'William Shakespeare' AND car.registry = '  AAA-111'", 
            "SELECT name FROM Cars c WHERE model = 'Newer sport'"
        );

        Iterator<String> testsIter = tests.iterator();
        Iterator<String> expectIter = expected.iterator();
        while(testsIter.hasNext() && expectIter.hasNext()) {
            String adjusted = Parameterize.adjustSpaces(testsIter.next());
            assertEquals(expectIter.next(), adjusted);            
        }
    }
}
