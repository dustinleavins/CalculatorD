package info.dustin_leavins.calculatord;

/**
 * Class implementing <code>CalculationItem</code> that 
 * represents an operation.
 * @author Dustin Leavins
 */
public class CalculationOperation  implements CalculationItem {
    private Operation op;

    /**
     * Constructor.
     * @param op <code>Operation</code> to contain.
     */
    public CalculationOperation(Operation op) {
        this.op = op;
    }

    /**
     * Returns the <code>Operation</code> stored in <code>this</code>
     * @return operation
     */
    public Operation getOperation() {
        return this.op;
    }

    /**
     * <code>this</code> represents an operation, not a number.
     * @return <code>false</code>
     */
    public boolean representsNumber() {
        return false;
    }

    /**
     * <code>this</code> represents an operation.
     * @return <code>true</code>
     */
    public boolean  representsOperation() {
        return true;
    }

}
