package CalculatorD;

/**
 * Class implementing <code>CalculationItem</code> that 
 * represents a number (stored as a <code>Fraction</code>).
 * @author Dustin Leavins
 */
public class CalculationNumber implements CalculationItem {
    private Fraction f;

    /**
     * Constructor.
     * @param f <code>Fraction</code> to contain.
     */
    public CalculationNumber(Fraction f) {
        this.f = f;
    }

    /**
     * Returns the <code>Fraction</code> stored in <code>this</code>
     * @return operation
     */
    public Fraction getFraction() {
        return f;
    }

    /**
     * <code>this</code> represents a number.
     * @return <code>false</code>
     */

    public boolean representsNumber() {
        return true;
    }

    /**
     * <code>this</code> represents a number, not an operation.
     * @return <code>false</code>
     */
    public boolean  representsOperation() {
        return false;
    }

}
