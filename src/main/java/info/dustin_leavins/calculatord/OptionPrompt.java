// Copyright (c) 2014, 2021 Dustin Leavins
// See the file 'LICENSE' for copying permission.

package info.dustin_leavins.calculatord;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.util.Hashtable;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Prompts the user to change GUI options. Inspiration provided by 
 * <a href="https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDialogRunnerProject/src/components/ListDialog.java">
 * ListDialog.java</a>.
 * @author Dustin Leavins
 */
public class OptionPrompt extends JDialog {
    private static GUIOptions guiOptions;
    private static CalculatorJFrame ownerFrame;
    private static OptionPrompt op;

    private JSlider displayFontSizeSlider;
    private JSlider buttonFontSizeSlider;
    private ButtonGroup decimalButtonUsageGroup;
    private JRadioButton useDecimalKeyForDeleteButton;
    private JRadioButton useDecimalKeyForDecimalButton;
    private ButtonGroup displayAlignmentGroup;
    private JRadioButton leftAlignmentButton;
    private JRadioButton rightAlignmentButton;
    private JRadioButton centerAlignmentButton;
    private JComboBox<String> displayModeBox;
    private JButton saveAndQuitButton;
    private JButton quitButton;

    private static final long serialVersionUID = 5593943455855642863L;

    /**
     * Show the prompt to the user.
     * @param currentOptions options to display and manipulate
     * @param f the <code>CalculatorJFrame</code> calling this method
     */
    public static void showPrompt(GUIOptions currentOptions, 
            CalculatorJFrame f) {
        GUIOptions tempOptions = currentOptions;
        op = new OptionPrompt(tempOptions,f);
        op.pack();
        op.setVisible(true);
    }

    /**
     * Private constructor.  Use <code>showPrompt</code> instead.
     * @param currentOptions options to display and manipulate
     * @param f the <code>CalculatorJFrame</code> calling this method
     */
    private OptionPrompt(GUIOptions currentOptions, CalculatorJFrame f) {
        super(f);
        GridLayout promptLayout = new GridLayout(6,1);
        JPanel displayFontSizePanel = new JPanel();
        JPanel buttonFontSizePanel = new JPanel();
        JPanel decimalKeyUsagePanel = new JPanel();
        JPanel displayAlignmentPanel = new JPanel();
        JPanel displayStylePanel = new JPanel();
        JPanel saveAndCancelPanel = new JPanel();

        guiOptions = currentOptions;
        ownerFrame = f;

        this.setTitle("Options");

        displayFontSizeSlider = new JSlider();
        displayFontSizeSlider.setMinimum(GUIOptions.MIN_FONT_SIZE);
        displayFontSizeSlider.setMaximum(GUIOptions.MAX_FONT_SIZE);
        displayFontSizeSlider.setValue(
                OptionPrompt.guiOptions.displayFontSize());
        displayFontSizeSlider.setPaintLabels(true);

        // Label table for displayFontSizeSlider
        Hashtable<Integer, JLabel> tssLabelTable = 
            new Hashtable<Integer, JLabel>();
        tssLabelTable.put(GUIOptions.MIN_FONT_SIZE, 
            new JLabel("Small"));
        tssLabelTable.put(GUIOptions.MAX_FONT_SIZE, 
            new JLabel("Large"));
        displayFontSizeSlider.setLabelTable(tssLabelTable);

        
        decimalButtonUsageGroup = new ButtonGroup();
        useDecimalKeyForDeleteButton = new JRadioButton("Delete");
        useDecimalKeyForDecimalButton = new JRadioButton("Decimal");

        decimalButtonUsageGroup.add(useDecimalKeyForDeleteButton);
        decimalButtonUsageGroup.add(useDecimalKeyForDecimalButton);

        displayAlignmentGroup = new ButtonGroup();
        leftAlignmentButton = new JRadioButton("Left");
        centerAlignmentButton = new JRadioButton("Center");
        rightAlignmentButton = new JRadioButton("Right");

        displayAlignmentGroup.add(leftAlignmentButton);
        displayAlignmentGroup.add(centerAlignmentButton);
        displayAlignmentGroup.add(rightAlignmentButton);


        buttonFontSizeSlider = new JSlider();
        buttonFontSizeSlider.setMinimum(GUIOptions.MIN_FONT_SIZE);
        buttonFontSizeSlider.setMaximum(GUIOptions.MAX_FONT_SIZE);
        buttonFontSizeSlider.setValue(OptionPrompt.guiOptions.buttonFontSize());
        buttonFontSizeSlider.setPaintLabels(true);

        // Label table for buttonFontSizeSlider
        buttonFontSizeSlider.setLabelTable(tssLabelTable);

        if (guiOptions.useDecimalButtonForDelete()) {
            useDecimalKeyForDeleteButton.setSelected(true);
        }
        else {
            useDecimalKeyForDecimalButton.setSelected(true);
        }

        switch (guiOptions.horizontalAlignment()) {
        case (GUIOptions.LEFT_HORIZONTAL_ALIGNMENT):
            leftAlignmentButton.setSelected(true);
            break;
        case (GUIOptions.RIGHT_HORIZONTAL_ALIGNMENT):
            rightAlignmentButton.setSelected(true);
            break;
        default: // case (GuiOptions.CENTER_HORIZONTAL_ALIGNMENT)
            centerAlignmentButton.setSelected(true);
            break;
        }

        String[] displayModeOptions = { "Decimal: 0.3", "Fraction: 3/10" };
        displayModeBox = new JComboBox<String>(displayModeOptions);

        switch (guiOptions.displayMode()) {
        case DECIMAL:
            displayModeBox.setSelectedIndex(0);
            break;
        case FRACTION:
            displayModeBox.setSelectedIndex(1);
            break;
        default:
            break;
        }

        saveAndQuitButton = new JButton("OK");
        quitButton = new JButton("Cancel");

        this.setLayout(promptLayout);
        decimalKeyUsagePanel.setLayout(new BorderLayout());
        displayAlignmentPanel.setLayout(new BorderLayout());
        
        displayFontSizePanel.add(
            new JLabel("Text size of number display:"));
        displayFontSizePanel.add(displayFontSizeSlider);


        buttonFontSizePanel.add(
            new JLabel("Text size of buttons."));
        buttonFontSizePanel.add(buttonFontSizeSlider);


        decimalKeyUsagePanel.add(
            new JLabel("Use decimal key on numpad for:"), 
            BorderLayout.NORTH);
        decimalKeyUsagePanel.add(
            useDecimalKeyForDeleteButton, 
            BorderLayout.CENTER);
        decimalKeyUsagePanel.add(
            useDecimalKeyForDecimalButton, 
            BorderLayout.SOUTH);

        displayAlignmentPanel.add(
            new JLabel("Alignment of input text:"), 
            BorderLayout.NORTH);
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new GridLayout(1,3));
        tempPanel.add(leftAlignmentButton);
        tempPanel.add(centerAlignmentButton);
        tempPanel.add(rightAlignmentButton);
        displayAlignmentPanel.add(tempPanel, BorderLayout.CENTER);

        displayStylePanel.setLayout(new BorderLayout());
        displayStylePanel.add(
            new JLabel("How to display results:"),
            BorderLayout.NORTH);
        displayStylePanel.add(
            displayModeBox,
            BorderLayout.CENTER);

        saveAndCancelPanel.add(saveAndQuitButton);
        saveAndCancelPanel.add(quitButton);

        this.add(displayFontSizePanel);
        this.add(buttonFontSizePanel);
        this.add(decimalKeyUsagePanel);
        this.add(displayAlignmentPanel);
        this.add(displayStylePanel);
        this.add(saveAndCancelPanel);

        saveAndQuitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                int alignment;
                boolean udbd = 
                    useDecimalKeyForDeleteButton.isSelected();
                DisplayMode displayMode;

                if (leftAlignmentButton.isSelected()) {
                    alignment = GUIOptions.LEFT_HORIZONTAL_ALIGNMENT;
                }
                else if (rightAlignmentButton.isSelected()) {
                    alignment = GUIOptions.RIGHT_HORIZONTAL_ALIGNMENT;
                }
                else {
                    alignment = GUIOptions.CENTER_HORIZONTAL_ALIGNMENT;
                }

                if (displayModeBox.getSelectedIndex() == 0) {
                    displayMode = DisplayMode.DECIMAL;
                }
                else {
                    displayMode = DisplayMode.FRACTION;
                }

                OptionPrompt.guiOptions = new GUIOptions(
                    displayFontSizeSlider.getValue(),
                    buttonFontSizeSlider.getValue(),
                    alignment,
                    udbd,
                    displayMode);


                OptionPrompt.op.setVisible(false);
                ownerFrame.setOptions(guiOptions);
            }
        });

        quitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                OptionPrompt.op.setVisible(false);
            }
        });
    }
}
