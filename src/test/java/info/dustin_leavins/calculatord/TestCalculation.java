// Copyright (c) 2014 Dustin Leavins
// See the file 'LICENSE' for copying permission.

package info.dustin_leavins.calculatord;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Simple test class for the <code>Calculation</code> class.
 * @author Dustin Leavins
 */
@RunWith(JUnit4.class)
public class TestCalculation {

    private Calculation calc;

    @Before
    public void setUp() {
        calc = new Calculation();
    }

    @Test
    public void appendOperationFirst() {
        calc.appendOperation(Operation.PLUS);
        assertTrue(calc.getValue().equals(Fraction.ZERO));
    }

    @Test
    public void addition() {
        // (1/3) + (5/3) = (6/3) = 2
        Fraction result = new Fraction(2,1);

        calc.appendNumber(new Fraction(1,3));
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(new Fraction(5,3));
        assertTrue(result.equals(calc.getValue()));
    }

    @Test
    public void subtraction() {
        // (1/3) - (5/3) = (-4/3)
        Fraction result = new Fraction(-4,3);
        
        calc.appendNumber(new Fraction(1,3));
        calc.appendOperation(Operation.MINUS);
        calc.appendNumber(new Fraction(5,3));
        assertTrue(result.equals(calc.getValue()));
    }

    @Test
    public void multiply() {
        // (1/3) * (5/3) = (5/9)
        Fraction result = new Fraction(5,9);

        calc.appendNumber(new Fraction(1,3));
        calc.appendOperation(Operation.MULTIPLY);
        calc.appendNumber(new Fraction(5,3));
        assertTrue(result.equals(calc.getValue()));
    }

    @Test
    public void divide() {
        // (1/3) / (5/3) = (1/5)
        Fraction result = new Fraction(1, 5);

        calc.appendNumber(new Fraction(1,3));
        calc.appendOperation(Operation.DIVIDE);
        calc.appendNumber(new Fraction(5,3));
        assertTrue(result.equals(calc.getValue()));
    }

    @Test
    public void mixedTests() {
        Fraction a = new Fraction(5,1);
        Fraction b = new Fraction(4,1);
        Fraction c = new Fraction(99,1);
        Fraction result;

        result = new Fraction(100,1);
        calc.appendNumber(a);
        calc.appendOperation(Operation.MINUS);
        calc.appendNumber(b);
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(c);
        assertTrue(result.equals(calc.getValue()));

        calc.clear();
        result = new Fraction(100,1);
        calc.appendNumber(a);
        calc.appendOperation(Operation.MULTIPLY);
        calc.appendOperation(Operation.MINUS);
        calc.appendNumber(b);
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(c);
        assertTrue(result.equals(calc.getValue()));

        calc.clear();
        result = new Fraction(103,1);
        calc.appendNumber(a);
        calc.appendNumber(b);
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(c);

        assertTrue(result.equals(calc.getValue()));
    }
}
