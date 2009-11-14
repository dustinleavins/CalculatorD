package CalculatorD;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import java.io.IOException;

/**
 * User interface of the calculator program.
 * @author Dustin Leavins
 */
public class CalculatorJFrame extends JFrame {

    private JButton[] numberButtons;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton decimalButton;
    private JButton deleteButton;
    private JButton equalsButton;
    private JButton clearButton;
    private JMenuItem optionsMenuItem;
    private final CalculatorTextField numberTextField = 
        new CalculatorTextField();

    /**
     * Represents the calculation that the user is currently working with.
     */
    private Calculation currentCalculation;

    /**
     * Represents the current user interface options in use.
     */
    private GUIOptions currentGUIOptions;

    /**
     * Manages IO from the options file.
     */
    private OptionsFileManager  optionsFileManager;

    private static final String NAME_OF_OPTIONS_FILE = "CalculatorOptions.cfg";

    /**
     * Variable used in making a call to 
     * <code>OptionPrompt.showPrompt</code> that allows the option prompt
     * to make changes in the display of <code>this</code>.
     */
    private static CalculatorJFrame thisFrame;

    /**
     * Order of the number orders as they appear from left to right
     * and from top to bottom.
     */
    private static int[] NUMBER_ORDER = {7,8,9,4,5,6,1,2,3,0};

    private static final long serialVersionUID = -1997088966855942014L;

    /**
     * Constructor.
     */
    public CalculatorJFrame() {
        super("Calc");
        JPanel numberButtonsPanel = new JPanel();
        JPanel operationsPanel = new JPanel();
        JPanel addSubPanel = new JPanel();
        JPanel mulDividePanel = new JPanel();
        JPanel decimalDeletePanel = new JPanel();

        thisFrame = this;

        currentCalculation = new Calculation();

        numberButtons = new JButton[NUMBER_ORDER.length];

        this.setMinimumSize(new Dimension(50,50));

        this.setLayout(new BorderLayout());
        numberButtonsPanel.setLayout(new GridLayout(4,4));
        operationsPanel.setLayout(new GridLayout(5,1));
        addSubPanel.setLayout(new GridLayout(1,2));
        mulDividePanel.setLayout(new GridLayout(1,2));
        decimalDeletePanel.setLayout(new GridLayout(1,2));

        for (int i = 0; i < NUMBER_ORDER.length; ++i) {
            numberButtons[NUMBER_ORDER[i]] = 
                new JButton(Integer.toString(NUMBER_ORDER[i]));

            numberButtonsPanel.add(numberButtons[NUMBER_ORDER[i]]);
        }

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        deleteButton = new JButton("Delete");
        equalsButton = new JButton("=");
        clearButton = new JButton("Clear");

        addSubPanel.add(addButton);
        addSubPanel.add(subtractButton);
        mulDividePanel.add(multiplyButton);
        mulDividePanel.add(divideButton);
        decimalDeletePanel.add(decimalButton);
        decimalDeletePanel.add(deleteButton);

        operationsPanel.add(clearButton);
        operationsPanel.add(decimalDeletePanel);
        operationsPanel.add(addSubPanel);
        operationsPanel.add(mulDividePanel);
        operationsPanel.add(equalsButton);

        this.add(numberTextField, BorderLayout.NORTH);
        this.add(numberButtonsPanel, BorderLayout.WEST);
        this.add(operationsPanel, BorderLayout.EAST);

        JMenuBar calcMenuBar = new JMenuBar();
        JMenu calcMenu = new JMenu();
        optionsMenuItem = new JMenuItem("Options");

        calcMenuBar.add(optionsMenuItem);
        calcMenuBar.add(calcMenu);
        this.setJMenuBar(calcMenuBar);

        // KeyAdapter for numberTextField;
        numberTextField.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                switch(c) {
                case('0'):
                case('1'):
                case('2'):
                case('3'):
                case('4'):
                case('5'):
                case('6'):
                case('7'):
                case('8'):
                case('9'):
                    numberButtons[c-'0'].doClick();
                    break;
                case('+'):
                    addButton.doClick();
                    break;
                case('-'):
                    subtractButton.doClick();
                    break;
                case('*'):
                    multiplyButton.doClick();
                    break;
                case('/'):
                    divideButton.doClick();
                    break;
                case('='):
                case('\n'):
                    equalsButton.doClick();
                    break;
                case('.'):
                    if (currentGUIOptions.useDecimalButtonForDelete()) {
                        deleteButton.doClick();
                    }
                    else {
                        decimalButton.doClick();
                    }
                    break;
                default:
                    break;
                }
            }
        });


        // ActionListener setup

        // numberButtons
        class NumberButtonsListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                String oldNTAText = numberTextField.getText();
                String textToAppend = ((JButton) ae.getSource()).getText();

                if (oldNTAText.equals("0")) {
                    oldNTAText = "";
                }

                // If displayMode is current true, then
                // numberTextField is currently showing
                // the value of a calculation.
                // Instead of appending to this value,
                // the number to add is shown on the display
                // instead of the result.
                if (numberTextField.getDisplayMode()) {
                    numberTextField.setDisplayMode(false);
                    numberTextField.setText(textToAppend);
                }
                else if (oldNTAText.length() < numberTextField.getColumns()) {
                    numberTextField.setText(oldNTAText + textToAppend);
                }

                numberTextField.requestFocus();
            }
        };

        for (int i = 0; i < NUMBER_ORDER.length; ++i) {
            numberButtons[i].addActionListener(new NumberButtonsListener());
        }

        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if (numberTextField.getDisplayMode()) {
                    currentCalculation.appendOperation(Operation.PLUS);
                }
                else {
                    String t = numberTextField.getText();

                    currentCalculation.appendNumber(new Fraction(t));
                    currentCalculation.appendOperation(Operation.PLUS);

                    numberTextField.setText(currentCalculation.getValue());

                    numberTextField.setDisplayMode(true);
                }

                numberTextField.requestFocus();
            }
        });

        subtractButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if (numberTextField.getDisplayMode()) {
                    currentCalculation.appendOperation(Operation.MINUS);
                }
                else {
                    String t = numberTextField.getText();

                    currentCalculation.appendNumber(new Fraction(t));
                    currentCalculation.appendOperation(Operation.MINUS);

                    numberTextField.setText(currentCalculation.getValue());

                    numberTextField.setDisplayMode(true);
                }

                numberTextField.requestFocus();
            }
        });

        multiplyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if (numberTextField.getDisplayMode()) {
                    currentCalculation.appendOperation(Operation.MULTIPLY);
                }
                else {
                    String t = numberTextField.getText();

                    currentCalculation.appendNumber(new Fraction(t));
                    currentCalculation.appendOperation(Operation.MULTIPLY);

                    numberTextField.setText(currentCalculation.getValue());

                    numberTextField.setDisplayMode(true);

                }

                numberTextField.requestFocus();
            }
        });

        divideButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if (numberTextField.getDisplayMode()) {
                    currentCalculation.appendOperation(Operation.DIVIDE);
                }
                else {
                    String t = numberTextField.getText();

                    currentCalculation.appendNumber(new Fraction(t));
                    currentCalculation.appendOperation(Operation.DIVIDE);

                    numberTextField.setText(currentCalculation.getValue());

                    numberTextField.setDisplayMode(true);
                }

                numberTextField.requestFocus();
            }
        });

        equalsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if (numberTextField.getDisplayMode()) {
                    // Do absolutely nothing!
                }
                else {
                    String t = numberTextField.getText();
                    currentCalculation.appendNumber(new Fraction(t));

                    numberTextField.setText(currentCalculation.getValue());

                    numberTextField.setDisplayMode(true);
                }

                numberTextField.requestFocus();
            }
        });

        decimalButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if (numberTextField.getDisplayMode()) {
                    numberTextField.setDisplayMode(false);
                    numberTextField.setText("0.");
                }
                else if (!(numberTextField.getText().contains("."))) {
                    String t = numberTextField.getText();
                    numberTextField.setText(t + ".");

                }

                numberTextField.requestFocus();
            }
        });


        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                String s = numberTextField.getText();
                s = s.substring(0, s.length() - 1);

                if (s.equals("")) {
                    s = "0";
                }

                numberTextField.setText(s);
                numberTextField.requestFocus();
            }
        });


        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                clearCalculationAndDisplay();
                numberTextField.requestFocus();
            }
        });

        optionsMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                OptionPrompt.showPrompt(currentGUIOptions, thisFrame);

                numberTextField.requestFocus();
            }
        });

        this.clearCalculationAndDisplay();

        optionsFileManager = new OptionsFileManager(NAME_OF_OPTIONS_FILE);

        setOptions(optionsFileManager.readOptions());
    }

    /**
     * Make and save changes to the GUI such that the changes follow the 
     * options given by the <code>GUIOptions</code> object.
     * This is used by <code>OptionPrompt</code> to actually change
     * settings from there.
     * @param guiO options to use and save
     */
    public void setOptions(GUIOptions guiO){
        this.currentGUIOptions = guiO;
        numberTextField.setFont(new Font(Font.MONOSPACED, 
            Font.PLAIN, guiO.displayFontSize()));

        numberTextField.setHorizontalAlignment(guiO.horizontalAlignment());

        // The "use decimal button for delete" option is handled
        // by the KeyListener for numberTextField.

        for (int i = 0; i < NUMBER_ORDER.length; ++i) {
            numberButtons[i].setFont(new Font(Font.MONOSPACED, 
                Font.PLAIN, 
                guiO.buttonFontSize()));
        }

        addButton.setFont(new Font(Font.MONOSPACED, 
            Font.PLAIN, 
            guiO.buttonFontSize()));
        subtractButton.setFont(new Font(Font.MONOSPACED, 
            Font.PLAIN, 
            guiO.buttonFontSize()));
        multiplyButton.setFont(new Font(Font.MONOSPACED,
            Font.PLAIN, 
            guiO.buttonFontSize()));
        divideButton.setFont(new Font(Font.MONOSPACED,
            Font.PLAIN, 
            guiO.buttonFontSize()));
        decimalButton.setFont(new Font(Font.MONOSPACED,
            Font.PLAIN, 
            guiO.buttonFontSize()));
        deleteButton.setFont(new Font(Font.MONOSPACED,
            Font.PLAIN, 
            guiO.buttonFontSize()));
        equalsButton.setFont(new Font(Font.MONOSPACED,
            Font.PLAIN, 
            guiO.buttonFontSize()));
        clearButton.setFont(new Font(Font.MONOSPACED,
            Font.PLAIN, 
            guiO.buttonFontSize()));

        try {
            optionsFileManager.saveOptions(currentGUIOptions);
            System.out.println("Successfully saved options.");
        }
        catch (IOException ioe) {
            // TODO - Do something of substance with this
            System.out.println("Unable to save options");
        }

        this.pack();
    }

    /**
     * Clears <code>currentCalculation</code> and 
     * <code>numberTextField</code>.
     * Both must always be done at the same time, so this private method
     * is convenient.
     */
    private void clearCalculationAndDisplay() {
        numberTextField.setText("0");
        currentCalculation.clear();
    }
}
