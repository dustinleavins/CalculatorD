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
    }

    public static void main (String [] args)
    {
        org.junit.runner.JUnitCore.main("CalculatorD.Test.TestFraction");
    }
}
