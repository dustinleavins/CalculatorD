package info.dustin_leavins.calculatord;

/**
 * Common interface for two classes:
 * <ul>
 *     <li><code>CalculationOperation</code></li>
 *     <li><code>CalculationNumber</code></li>
 * </ul>
 * Another class, <code>Calculation</code>, uses this interface
 * to store both <code>CalculationOperation</code> objects and
 * <code>CalculationNumber</code> operations in the same collection.
 * @author Dustin Leavins
 */
public interface CalculationItem {
    /**
     * Does <code>CalculationItem</code> represent a number?
     * @return <code>true</code> if yes, <code>false</code> otherwise
     */
    public boolean representsNumber();

    /**
     * Does <code>CalculationItem</code> represent an operation?
     * @return <code>true</code> if yes, <code>false</code> otherwise
     */
    public boolean representsOperation();
}
