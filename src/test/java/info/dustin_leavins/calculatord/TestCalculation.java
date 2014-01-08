package info.dustin_leavins.calculatord;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Simple test class for the <code>Calculation</code> class.
 * @author Dustin Leavins
 */
@RunWith(JUnit4.class)
public class TestCalculation {

    @Test
    public void everything() {
        Calculation calc = new Calculation();
        Fraction a;
        Fraction b;
        Fraction c;
        Fraction result;

        //---Addition Test---
        // (1/3) + (5/3) = (6/3) = 2
        a = new Fraction(1,3);
        b = new Fraction(5,3);
        result = new Fraction(2,1);
        calc.appendNumber(a);
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(b);
        assertTrue(result.equals(calc.getValue()));

        //---Subtraction Test---
        // (1/3) - (5/3) = (-4/3)
        calc.clear();
        result = new Fraction(-4,3);
        calc.appendNumber(a);
        calc.appendOperation(Operation.MINUS);
        calc.appendNumber(b);
        assertTrue(result.equals(calc.getValue()));

        //---Multiplication Test---
        // (1/3) * (5/3) = (5/9)
        calc.clear();
        result = new Fraction(5,9);
        calc.appendNumber(a);
        calc.appendOperation(Operation.MULTIPLY);
        calc.appendNumber(b);
        assertTrue(result.equals(calc.getValue()));

        //---Division Test---
        // (1/3) / (5/3) = (1/5)
        calc.clear();
        result = new Fraction(1, 5);
        calc.appendNumber(a);
        calc.appendOperation(Operation.DIVIDE);
        calc.appendNumber(b);
        assertTrue(result.equals(calc.getValue()));

        //---Mixed Tests---
        calc.clear();
        a = new Fraction(5,1);
        b = new Fraction(4,1);
        c = new Fraction(99,1);

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