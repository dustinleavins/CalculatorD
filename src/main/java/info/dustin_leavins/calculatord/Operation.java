package info.dustin_leavins.calculatord;

/**
 * Represents a calculator operation.
 * @author Dustin Leavins
 */
public enum Operation {
    /**
     * Addition.
     */
    PLUS {
        Fraction apply(Fraction l, Fraction r) {
            return l.add(r);
        } 
    }, 

    /**
     * Subtraction.
     */
    MINUS {
        Fraction apply(Fraction l, Fraction r) {
            return l.subtract(r);
        } 
    },

    /**
     * Multiplication.
     */
    MULTIPLY {
        Fraction apply(Fraction l, Fraction r) {
            return l.multiply(r);
        } 
    },

    /**
     * Division
     */
    DIVIDE {
        Fraction apply(Fraction l, Fraction r) {
            return l.divide(r);
        } 
    };

    abstract Fraction apply(Fraction left, Fraction right);
}
