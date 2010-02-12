package CalculatorD;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * Handles File IO for reading/writing <code>GUIOptions</code>
 * objects.
 * Files generated and read by <code>OptionsFileManager</code> objects
 * are plaintext with a very basic layout.  Does not use XML, YAML, or any
 * other markup language due to the very small amount of data that needs to be
 * processed.
 * @author Dustin Leavins
 */
public class OptionsFileManager {

    private File optionsFile;

    /**
     * Constructor.
     * @param pathname pathname (along with the name) of the file to 
     * read from and write to.
     */
    public OptionsFileManager(String pathname) {
        this.optionsFile = new File(pathname);

    }

    /**
     * Generates a <code>GUIOption</code> object from the file or 
     * returns the default <code>GUIOptions</code>.
     * This method handles all IO errors so that the method 
     * calling <code>saveOptions</code> not need to be concerned with them.
     * @return options from the file specified in the constructor for
     * <code>this</code>.  If the file cannot be read from, 
     * <code>GUIOptions.defaultOptions()</code> is returned automatically.
     */
    public GUIOptions readOptions() {
        FileReader fr;
        BufferedReader bReader;
        int displayFontSize;
        int buttonFontSize;
        int horizontalAlignment;
        boolean decimalButtonIsDelete;
        String tempString;
        GUIOptions returnOptions;

        try {
            fr = new FileReader(this.optionsFile);
            bReader = new BufferedReader(fr);

            // Reading displayFontSize from file
            tempString = bReader.readLine();
            tempString = tempString.substring(4,tempString.length());
            displayFontSize = Integer.valueOf(tempString);

            // Reading buttonFontSize from file
            tempString = bReader.readLine();
            tempString = tempString.substring(4,tempString.length());
            buttonFontSize = Integer.valueOf(tempString);

            // Reading horizontalAlignment from file
            tempString = bReader.readLine();
            tempString = tempString.substring(3,tempString.length());
            horizontalAlignment = Integer.valueOf(tempString);

            // Reading decimalButtonIsDelete from file
            tempString = bReader.readLine();
            tempString = tempString.substring(4, tempString.length());
            decimalButtonIsDelete = tempString.equals("Yes");

            returnOptions = new GUIOptions(displayFontSize,
                buttonFontSize,
                horizontalAlignment,
                decimalButtonIsDelete);

        }
        catch (IOException ioe) {
            returnOptions = GUIOptions.defaultOptions();
        }

        return returnOptions;
    }

    /**
     * Save the options in the file specified in the constructor for
     * <code>this</code> or throws in IOException.
     * @param o <code>GUIOptions</code> object to save
     * @throws IOException there is an IO error that needs to be handled
     * by the method calling <code>saveOptions</code>
     */
    public void saveOptions(GUIOptions o) throws IOException {
        FileWriter writer;
        int displayFontSize = o.displayFontSize();
        int buttonFontSize = o.buttonFontSize();
        int horizontalAlignment = o.horizontalAlignment();
        boolean decimalButtonIsDelete = o.useDecimalButtonForDelete();
        StringBuffer sb = new StringBuffer();

        try {
            writer = new FileWriter(optionsFile);

            sb.append("dfs:");
            sb.append(displayFontSize + "\n");
            sb.append("bfs:");
            sb.append(buttonFontSize + "\n");
            sb.append("ha:");
            sb.append(horizontalAlignment + "\n");
            sb.append("udb:");
            sb.append(decimalButtonIsDelete ? "Yes\n" : "No\n");

            writer.write(sb.toString());
            writer.close();
        }
        catch(IOException ioe) {
            throw ioe;
        }
    }
}
