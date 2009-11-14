package CalculatorD;

/**
 * Class containing the <code>main</code> method of the Calculator
 * program.
 * @author Dustin Leavins
 */
public class Main {

    /**
     * Main.
     * @param args not used
     */
    public static void main (String [] args)
    {
        CalculatorJFrame cfj = new CalculatorJFrame();
        cfj.setDefaultCloseOperation(CalculatorJFrame.EXIT_ON_CLOSE);
        cfj.pack();
        cfj.setVisible(true);
    }
}
