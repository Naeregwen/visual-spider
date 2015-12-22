package spiders.components.listeners;
/*
 * VerifyierReporter.java
 *
 * Created on September 17, 2004, 11:47 AM
 */

/**
 * Inteface used for a verifier to report bad data back to the owning frame.  The frame
 * can then choose to enable/disable controls and display error messages
 *
 * @author  Mark Pendergast
 */
public interface VerifierListener{
    
    /**
     * method called when a verifier detects bad data
     * 
     * @param message - error message
     * @param component - component just checked
     */
    public abstract void invalidData(String message, javax.swing.JComponent component);
     /**
     * method called when a verifier detects good data
     *
     * @param component - component just checked
     */
    public abstract void validData(javax.swing.JComponent component);
    
}
