package CalculatorD.Test;
import CalculatorD.Fraction;
import java.math.BigInteger;
import org.junit.*;
import static org.junit.Assert.*;

public class TestFraction {
    @Test
    public void testStringConstructor() {
        Fraction f;
        BigInteger correctNumerator = BigInteger.valueOf(10);
        BigInteger correctDenominator = BigInteger.valueOf(1);

        // Integer
        f = new Fraction("10");
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Fraction, Positive, Less than 1
        f = new Fraction("0.1"); // 1/10
        correctNumerator = BigInteger.valueOf(1);
        correctDenominator = BigInteger.valueOf(10);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Fraction, Negative, Greater than -1
        f = new Fraction("-0.1); // -1/10
        correctNumerator = BigInteger.valueOf(-1);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);
    }

    @Test
    public void testIntegerConstructor() {
        // TODO - Stub
    }

    @Test
    public void testLongConstructor() {
        // TODO - Stub
    }

    @Test
    public void testDoubleValue() {
        // TODO - Stub
    }

    @Test
    public void testAddition() {
        // TODO - Stub
    }

    @Test
    public void testSubtraction() {
        // TODO - Stub
    }

    @Test
    public void testMultiply() {
        // TODO - Stub
    }

    @Test
    public void testNegative() {
        // TODO - Stub
    }

    @Test
    public void testDivide() {
        // TODO - Stub
    }

    @Test
    public void testInverse() {
        // TODO - Stub
    }

    @Test
    public void testEquals() {
        // TODO - Stub
    }

    public static void main (String [] args)
    {
        org.junit.runner.JUnitCore.main("CalculatorD.Test.TestFraction");
    }
}
