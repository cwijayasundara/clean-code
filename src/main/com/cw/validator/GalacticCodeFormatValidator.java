package main.com.cw.validator;

import java.util.regex.Pattern;

/**
 * Created by cwijayasundara on 23/01/2017.
 * Galactic code format validator using regex
 */

public class GalacticCodeFormatValidator implements NumberFormatValidator {

    // regex to validate the galactic code
    private final static Pattern regex = Pattern.compile("^(M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})|[IDCXMLV])$");

    public boolean isValidFormat(final String galacticCode){
        return galacticCode !=null && !"".equals(galacticCode) && regex.matcher(galacticCode.toUpperCase()).matches();
    }

}
