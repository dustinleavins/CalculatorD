// Copyright (c) 2014 Dustin Leavins
// See the file 'LICENSE' for copying permission.

package info.dustin_leavins.calculatord;

/**
 * Represents an entry in a calculation.
 * @author Dustin Leavins
 */
class CalculationEntry {
    private boolean hasFraction;
    private Fraction fraction;
    private Operation op;

    private CalculationEntry() {
    }

    /**
     * Does this entry represent an operation?
     * @return true if this entry is an operation; otherwise, false
     */
    public boolean representsOperation() {
        return !hasFraction;
    }

    /**
     * Does this entry represent a fraction?
     * @return true if this entry is an fraction; otherwise, false
     */
    public boolean representsFraction() {
        return hasFraction;
    }

    /**
     * Gets this entry's fraction.
     * @throws IllegalStateException
     *   when this entry does not contain a fraction
     * @return this entry's fraction
     */
    public Fraction getFraction() {
        if (!hasFraction) {
            throw new IllegalStateException();
        } else {
            return fraction;
        }
    }

    /**
     * Gets this entry's operation.
     * @throws IllegalStateException
     *   when this entry does not contain an operation
     * @return this entry's operation
     */
    public Operation getOperation() {
        if (hasFraction) {
            throw new IllegalStateException();
        } else {
            return op;
        }
    }

    /**
     * Returns a new entry containing a fraction.
     */
    public static CalculationEntry fraction(Fraction f) {
        CalculationEntry returnVal = new CalculationEntry();
        returnVal.hasFraction = true;
        returnVal.fraction = f;
        return returnVal;
    }

    /**
     * Returns a new entry containing an operation.
     */
    public static CalculationEntry operation(Operation op) {
        CalculationEntry returnVal = new CalculationEntry();
        returnVal.op = op;
        return returnVal;
    }

}
