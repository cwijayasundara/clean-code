package main.com.cw.converter;

/**
 * Created by cwijayasundara on 23/01/2017.
 * The generic interface for number converter. Clients can implement this for RomanToArabic conversion or any other
 * conversions.
 */

public interface NumberConverter {

    /*
     * Override this method to convert the numebr from a Galactic code to Decimal or
      * Roman to Decimal or Decimal to Roma etc
     */
    int convert(final String str);
}
