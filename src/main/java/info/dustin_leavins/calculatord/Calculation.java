package info.dustin_leavins.calculatord;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Represents a calculation that consists of <code>Operation</code>s and
 * <code>Fraction</code>s.
 * There are two major methods involved in making a calculation.
 * <code>appendNumber</code> appends a <code>Fraction</code> to the end of 
 * the calculation. <code>appendOperation</code> appends an 
 * <code>Operation</code> to the end of the calculation.  Calculations use
 * infix notation, and this notation is adhered to in such a manner as to
 * require no intervention by the class using a <code>Calculation</code>
 * object.
 * The calculation's order of operations is strictly left to right, meaning
 * that multiplication (and division) have the same precendence as addition
 * (and subtraction).
 * @author Dustin Leavins
 */
public class Calculation {
    private LinkedList<CalculationEntry> listOfCalculationItems;

    /**
     * Constructor; contains no default calculation.
     */
    public Calculation() {
        listOfCalculationItems = new LinkedList<CalculationEntry>();
    }

    /**
     * Appends a number/<code>Fraction</code> to the end of the 
     * calculation.
     * If there is already a number at the end of the calculation, 
     * it is automatically removed.
     */
    public void appendNumber(Fraction f) {
        if (listOfCalculationItems.size() == 0) {
          listOfCalculationItems.add(CalculationEntry.fraction(f));
          return;
        }

        CalculationEntry lastItem = listOfCalculationItems.getLast();

        if (lastItem.representsFraction()) {
            listOfCalculationItems.removeLast();
        }

        listOfCalculationItems.add(CalculationEntry.fraction(f));
    }

    /**
     * Appends an operation/<code>Operation</code> to the end of the 
     * calculation.
     * If there is already an operation at the end of the calculation,
     * it is automatically removed.
     * If the calculation is empty, no operation is added (remember,
     * this class uses infix notation for calculations).
     */
    public void appendOperation(Operation d) {
        if (listOfCalculationItems.size() == 0) {
            // operation cannot be the first item in a calculation
            return;
        }

        CalculationEntry lastItem = listOfCalculationItems.getLast();

        if (lastItem.representsOperation()) {
            listOfCalculationItems.removeLast();
        }

        listOfCalculationItems.add(CalculationEntry.operation(d));
    }

    /**
     * Clears the current calculation of all numbers and operations.
     */
    public void clear() {
        listOfCalculationItems.clear();
    }

    /**
     * Calculates and returns the value of the current calculation,
     * ignoring operations at the very end of the calculation.
     * Example calculations and results:
     * <ul>
     *     <li>(1/2) + (1/2) = 1</li>
     *     <li>(1/2) + = (1/2)</li>
     *     <li>(1/2) + 1 * 2 = 3</li>
     * </ul>
     * All operators have the same precedence.
     * @return value of current calculation
     */
    public Fraction getValue() {
        CalculationEntry aItem;
        CalculationEntry bItem;
        CalculationEntry opItem;
        Fraction returnValue = Fraction.ZERO;
        boolean illegalCalculation = false;

        Operation operation;
        Fraction a = Fraction.ZERO;
        Fraction b = Fraction.ZERO;

        LinkedList<CalculationEntry> tempList = 
            new LinkedList<CalculationEntry>(listOfCalculationItems);

        // Without the folling if statements, this method always
        // returns 0 if there are less than three elements in
        // listOfCalculationItems
        if (tempList.size() == 0) {
            return Fraction.ZERO;
        } else if (tempList.size() <= 2) {
            aItem = tempList.pop(); // short for aItem

            if (aItem.representsFraction()) {
                return aItem.getFraction();
            }
            else{
                return Fraction.ZERO;
            }
        }

        while (tempList.size() >= 3) {
            aItem = tempList.pop();
            opItem = tempList.pop();
            bItem = tempList.pop();

            illegalCalculation = aItem.representsOperation() ||
                opItem.representsFraction() || 
                bItem.representsOperation();

            if (illegalCalculation) {
                // TODO: Throw exception?
                returnValue = Fraction.ZERO;
                System.out.println("OperationFailed");
                break;
            }

            a = aItem.getFraction();
            b = bItem.getFraction();
            operation = opItem.getOperation();

            switch(operation) {
                case PLUS:
                    returnValue = a.add(b);
                    break;
                case MINUS:
                    returnValue = a.subtract(b);
                    break;
                case MULTIPLY:
                    returnValue = a.multiply(b);
                    break;
                case DIVIDE:
                    returnValue = a.divide(b);
                    break;
                default:
                    break;
            }

            tempList.push(CalculationEntry.fraction(returnValue));
        }

        /* The contents of this calculation does not matter; only the
         * end-result does.
         */
        this.clear();
        listOfCalculationItems.addAll(tempList);

        return returnValue.reduce();
    }
}
