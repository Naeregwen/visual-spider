package spiders.components.listeners;
import javax.swing.*;
import java.awt.*;// need this to access the color object
/*
 * IntegerVerifier.java
 *
 * Created on November 17, 2003, 11:47 AM
 */

/**
 * Input Verifier to verifier integer text fields
 *
 * Checks for valid integer input, and to see if the number is between a
 * specified max and min value.
 *
 * @author  Mark Pendergast
 */
public class IntegerVerifier extends javax.swing.InputVerifier {
    /** listener to get valid/invalid data reports */
    private VerifierListener listener = null;
    /** blank fields allowed, true for ok, false for error */
    private boolean blankOk = false;
    /** minimum valid value */
    int minValue = Integer.MIN_VALUE;
   /** maximum valid value*/
    int maxValue = Integer.MAX_VALUE;
    /** Creates a new instance of IntegerVerifier 
     *
     * @param alistener VerifierListener to receive invalid/valid data class (null means no listener)
     * @param blankok if true, then the field can be left blank
     * @param min minimum valid value
     * @param max maximum valid value
     */
    public IntegerVerifier(VerifierListener alistener, boolean blankok, int min, int max) {
        listener = alistener;
        blankOk = blankok;
        minValue = min;
        maxValue = max;
    }
    /**
     * Verifies contents of the specified component
     *
     * @param jComponent the component to check
     * @return true if the component is ok, else false
     *
     */
    public boolean verify(javax.swing.JComponent jComponent) {
     JTextField thefield = (JTextField)jComponent;
     String input = thefield.getText();
     int number;
     input = input.trim(); // strip off leading and trailing spaces as these gives Integer.parseInt problems

     if(input.length() == 0 && blankOk)
     {
      thefield.setForeground(Color.black);
      if(listener != null)
         listener.validData(jComponent);
      return true; // if empty, just return true
     }
     else
     if(input.length() == 0 && !blankOk)
     {
      reportError(thefield,"Field cannot be blank!");
      return false; // if empty, just return true
     }
         
     /*
      * try to convert to an integer
      */
     try{
        number = Integer.parseInt(input);
     }
     catch (NumberFormatException e)
     {
      reportError(thefield,"You must enter a valid number");
      return false;
     }
     /*
      * test if its in the range
      */
     if(number < minValue || number > maxValue)
     {

      reportError(thefield,"You must enter a number between "+minValue+" and "+maxValue);
      return false;
     }
     /*
      * report good data
      */  
     thefield.setForeground(Color.black);
     thefield.setText(""+number); // reset what we converted into the component
     if(listener != null)
       listener.validData(jComponent);
     return true; // valid input found  
    }
    /**
     * report error to the listener (if any)
     * @param thefield text field being checked
     * @param message error message to report
     */
    private void reportError(JTextField thefield, String message)
    {
     thefield.setForeground(Color.red);  // paint the text red, return false invalid input
     if(listener != null)
      listener.invalidData(message,thefield);
    }
    
}
