package CalculatorD;

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
 * @author Dustin Leavins
 */
public class Calculation {
    private LinkedList<CalculationItem> listOfCalculationItems;

    /**
     * Constructor; contains no default calculation.
     */
    public Calculation() {
        listOfCalculationItems = new LinkedList<CalculationItem>();
    }

    /**
     * Appends a number/<code>Fraction</code> to the end of the 
     * calculation.
     * If there is already a number at the end of the calculation, 
     * it is automatically removed.
     */
    public void appendNumber(Fraction f) {
        CalculationItem lastItem;
        boolean lastItemIsNumber;

        try {
            lastItem = listOfCalculationItems.getLast();
            lastItemIsNumber = lastItem.representsNumber();
        }
        catch(NoSuchElementException nsee) {
            lastItemIsNumber = false;
        }

        if (lastItemIsNumber) {
            listOfCalculationItems.removeLast();
        }

        listOfCalculationItems.add(new CalculationNumber(f));
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
        CalculationItem lastItem;
        boolean lastItemIsOperation;

        try {
            lastItem = listOfCalculationItems.getLast();
            lastItemIsOperation = lastItem.representsOperation();
        }
        catch(NoSuchElementException nsee) {
        // operation cannot be the first item in a calculation
            return;         
        }

        if (lastItemIsOperation) {
            listOfCalculationItems.removeLast();
        }

        listOfCalculationItems.add(new CalculationOperation(d));
    }

    public void clear() {
        listOfCalculationItems = new LinkedList<CalculationItem>();
    }

    public Fraction getValue() {
        CalculationItem aItem;
        CalculationItem bItem;
        CalculationItem opItem;
        Fraction returnValue = Fraction.ZERO;
        boolean illegalCalculation = false;

        Operation operation;
        Fraction a = Fraction.ZERO;
        Fraction b = Fraction.ZERO;

        LinkedList<CalculationItem> tempList = 
            new LinkedList<CalculationItem>(listOfCalculationItems);

        // Without the folling if statement, this method always
        // returns 0 if there are less than three elements in
        // listOfCalculationItems

        if (tempList.size() <= 2) {
            aItem = tempList.pop(); // short for aItem

            if (aItem.representsNumber()) {
                return ((CalculationNumber) aItem).getFraction();
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
                opItem.representsNumber() || 
                bItem.representsOperation();

            if (illegalCalculation) {
                returnValue = Fraction.ZERO;
                System.out.println("OperationFailed");
                break;
            }

            a = ((CalculationNumber)aItem).getFraction();
            b = ((CalculationNumber)bItem).getFraction();
            operation = ((CalculationOperation)opItem).getOperation();

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

            tempList.push(new CalculationNumber(returnValue));
        }

        /* The contents of this calculation does not matter; only the
         * end-result does.
         */
        this.clear();
        listOfCalculationItems.addAll(tempList);

        return returnValue.reduce();
    }
}
