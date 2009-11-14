package CalculatorD;

/**
 * Simple test class for the <code>Calculation</code> class.
 * @author Dustin Leavins
 */
public class Test_Calculation {
    /**
     * Main
     * @param args unused
     */
    public static void main (String [] args)
    {
        Calculation calc = new Calculation();
        Fraction a;
        Fraction b;
        Fraction c;

        System.out.println("---Addition Tests---");
        a = new Fraction(1,1);
        b = new Fraction(1,1);
        calc.appendNumber(a);
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(b);
        System.out.println("1 + 1 = " + calc.getValue());

        calc.clear();
        a = new Fraction(1,3);
        b = new Fraction(5,3);
        calc.appendNumber(a);
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(b);
        System.out.println("(1/3) + (5/3) = " + calc.getValue());

        System.out.println("---Subtraction Test---");
        calc.clear();
        calc.appendNumber(a);
        calc.appendOperation(Operation.MINUS);
        calc.appendNumber(b);
        System.out.println("(1/3) - (5/3) = " + calc.getValue());

        System.out.println("---Multiplication Test---");
        calc.clear();
        calc.appendNumber(a);
        calc.appendOperation(Operation.MULTIPLY);
        calc.appendNumber(b);
        System.out.println("(1/3) * (5/3) = " + calc.getValue());

        System.out.println("---Division Test---");
        calc.clear();
        calc.appendNumber(a);
        calc.appendOperation(Operation.DIVIDE);
        calc.appendNumber(b);
        System.out.println("(1/3) / (5/3) = " + calc.getValue());

        System.out.println("---Mixed Tests---");
        calc.clear();
        a = new Fraction(5,1);
        b = new Fraction(4,1);
        c = new Fraction(99,1);
        calc.appendNumber(a);
        calc.appendNumber(b);
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(c);
        System.out.println("5 4 + 99 = 4 + 99 = " + calc.getValue());

        calc.clear();
        a = new Fraction(5,1);
        b = new Fraction(4,1);
        c = new Fraction(99,1);
        calc.appendNumber(a);
        calc.appendOperation(Operation.MINUS);
        calc.appendNumber(b);
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(c);
        System.out.println("5 - 4 + 99 = " + calc.getValue());

        calc.clear();
        a = new Fraction(5,1);
        b = new Fraction(4,1);
        c = new Fraction(99,1);
        calc.appendNumber(a);
        calc.appendOperation(Operation.MULTIPLY);
        calc.appendOperation(Operation.MINUS);
        calc.appendNumber(b);
        calc.appendOperation(Operation.PLUS);
        calc.appendNumber(c);
        System.out.println("5 *- 4 + 99 = 5 - 4 + 99 = " + 
            calc.getValue());
    }
}
