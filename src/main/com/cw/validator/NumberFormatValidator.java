package main.com.cw.validator;

/**
 * Created by cwijayasundara on 23/01/2017.
 * This is a generic number format validator
 */
public interface NumberFormatValidator {

    /* Clients can implement this method to check if a given galactic code is in the
     * correct format
     */
    boolean isValidFormat(final String galacticCode);
}
