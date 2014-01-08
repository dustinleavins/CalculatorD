package info.dustin_leavins.calculatord;

import java.math.BigInteger;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestFraction {

    @Test
    public void stringConstructor() {
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
        f = new Fraction("-0.1"); // -1/10
        correctNumerator = BigInteger.valueOf(-1);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);
    }

    @Test
    public void integerConstructor() {
        Fraction f;
        BigInteger correctNumerator = BigInteger.valueOf(10);
        BigInteger correctDenominator = BigInteger.valueOf(1);

        // Integer
        f = new Fraction(10,1);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Fraction, Positive, Less than 1
        f = new Fraction(1,10);
        correctNumerator = BigInteger.valueOf(1);
        correctDenominator = BigInteger.valueOf(10);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Fraction, Negative, Greater than -1
        f = new Fraction(-1,10);
        correctNumerator = BigInteger.valueOf(-1);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Fraction, Negative, greater than -1, negative denominator
        // Fraction should change the negative denominator to
        // positive and make the numerator negative.
        f = new Fraction(1,-10);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Denominator = 0
        // Should throw ArithmeticException
        try {
            f = new Fraction(1,0);
            fail();
        }
        catch (ArithmeticException ae) {
            // Do nothing; it's okay!
        }
    }

    @Test
    public void testLongConstructor() {
        Fraction f;
        BigInteger correctNumerator = BigInteger.valueOf(10);
        BigInteger correctDenominator = BigInteger.valueOf(1);

        // Integer
        f = new Fraction(10L,1L);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Fraction, Positive, Less than 1
        f = new Fraction(1L,10L);
        correctNumerator = BigInteger.valueOf(1);
        correctDenominator = BigInteger.valueOf(10);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Fraction, Negative, Greater than -1
        f = new Fraction(-1L,10L);
        correctNumerator = BigInteger.valueOf(-1);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Fraction, Negative, greater than -1, negative denominator
        // Fraction should change the negative denominator to
        // positive and make the numerator negative.
        f = new Fraction(1L,-10L);
        assertEquals(f.getNumerator(), correctNumerator);
        assertEquals(f.getDenominator(), correctDenominator);

        // Denominator = 0
        // Should throw ArithmeticException
        try {
            f = new Fraction(1L,0L);
            fail();
        }
        catch (ArithmeticException ae) {
            // Do nothing; it's okay!
        }
    }

    @Test
    public void testDoubleValue() {
        Fraction f = new Fraction("0.2");
        double d = 0.2D;
        double fAsDouble = f.doubleValue();
        System.out.println(f.toString());

        assertTrue(0 == Double.compare(f.doubleValue(), d));
    }

    @Test
    public void testAddition() {
        Fraction x;
        Fraction y;
        Fraction sum;

        // 1 + 3 = 4
        x = new Fraction(1,1);
        y = new Fraction(3,1);
        sum = new Fraction(4,1);
        assertTrue(sum.equals(x.add(y)));

        // -5/2 + 5/2 = 0/4
        x = new Fraction(-5,2);
        y = new Fraction(5,2);
        sum = new Fraction(0,4);
        assertTrue(sum.equals(x.add(y)));

    }

    @Test
    public void testSubtraction() {
        Fraction x;
        Fraction y;
        Fraction difference;

        // 5/2 - 5/2 = 0/4
        x = new Fraction(5,2);
        difference = new Fraction(0,4);
        assertTrue(difference.equals(x.subtract(x)));

        // 11 - 10 = 1
        x = new Fraction(11,1);
        y = new Fraction(10,1);
        difference = new Fraction(1,1);
        assertTrue(difference.equals(x.subtract(y)));
    }

    @Test
    public void testMultiply() {
        Fraction x;
        Fraction y;
        Fraction product;

        // (1/10) * -10 = (-10/10)
        x = new Fraction(1,10);
        y = new Fraction(-10,1);
        product = new Fraction(-10,10);
        assertTrue(product.equals(x.multiply(y)));
    }

    @Test
    public void testNegative() {
        Fraction f = new Fraction(9,3);
        Fraction negativeF = new Fraction(-9,3);

        assertTrue(negativeF.equals(f.negative()));
    }

    @Test
    public void testDivide() {
        Fraction x;
        Fraction y;
        Fraction quotient;

        // (1) / (-10) = (-1/10)
        x = new Fraction(1,1);
        y = new Fraction(-10,1);
        quotient = new Fraction(-1,10);
        assertTrue(quotient.equals(x.divide(y)));
    }

    @Test
    public void testInverse() {
        Fraction f = new Fraction(-1,99);
        Fraction result = new Fraction(-99,1);

        assertTrue(f.inverse().equals(result));

        // Denominator = 0
        // Should throw ArithmeticException
        f = new Fraction(0,100);

        try {
            result = f.inverse();
            fail();
        }
        catch (ArithmeticException ae) {
            // Do nothing!
        }
    }

    @Test
    public void testEquals() {
        Fraction x = new Fraction(3,10);
        Fraction y = new Fraction("0.3");
        boolean firstCondition, secondCondition;

        // Symmetry test - true
        firstCondition = x.equals(y);
        secondCondition = y.equals(x);
        assertTrue(firstCondition == secondCondition);

        // Transitive test - true
        Fraction z = new Fraction(3,10);
        boolean thirdCondition;

        firstCondition = x.equals(y);
        secondCondition = y.equals(z);
        thirdCondition = x.equals(z);
        assertTrue((firstCondition && secondCondition) == thirdCondition);

        // Symmetry test - false
        y = new Fraction(10,3);
        firstCondition = x.equals(y);
        secondCondition = y.equals(x);
        assertTrue(firstCondition == secondCondition);

        // null test
        assertFalse(x.equals(null));

        // Non-Fraction class test
        assertFalse(x.equals(BigInteger.valueOf(4)));
    }

    @Test
    public void testHashCode() {
        Fraction f1 = new Fraction(3,1);
        Fraction f2 = new Fraction("3");

        assertEquals(f1.hashCode(), f2.hashCode());
    }
}
