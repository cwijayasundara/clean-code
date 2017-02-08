package main.com.cw.decoder;

import main.com.cw.converter.NumberConverter;
import main.com.cw.converter.GalacticCodeToDecimalConverter;
import main.com.cw.mapper.GalacticInputMapper;

import java.util.*;

/**
 * Created by cwijayasundara on 23/01/2017.
 *
 * This class has the logic to decode the inter galactic codes and prices and give out the responses.
 *
 */

public class GalacticQueryDecoder {

    // create an instance of the galacticInputMapper
    private GalacticInputMapper galacticInputMapper = new GalacticInputMapper();

    private NumberConverter numberConverter = new GalacticCodeToDecimalConverter();

    private final String validList = "validStrList";

    private final String invalidList = "invalidStrList";

    private final String invalidQueryString = "I have no idea what you are talking about";

    private final String regExPattern = "((?<=:)|(?=:))|( )";

    private final String credits = "Credits";

    /* This is a generic method to track any number of galactic codes and convert them to the curresponding Roman numaral
     * hence to the decimal value.
    */
    public String decodeGalacticCodeToDecimal() {

        Map<String, List<String>> validInvalidCodeMap = getAllGalacticQueries();
        List<String> validQueryList = new ArrayList<>();
        Map<String, String> tokenToRomanMap = galacticInputMapper.tokenToRomanMapper();
        Set<String> codeSet = getGalacticCodeSet(tokenToRomanMap);
        String finalDisplayString = null;

        if (validInvalidCodeMap != null) validQueryList = validInvalidCodeMap.get(validList);
        Iterator validListIterator = validQueryList.listIterator();

        // outer loop that loops the List<String>
        while (validListIterator.hasNext()) {
            String line = (String) validListIterator.next();
            String token[] = line.split(regExPattern);
            List<String> galacticCodeList = new ArrayList<>();
            List<String> romanCodeList = new ArrayList<>();

            //inner loop to loop through the tokens of the query ie; "how", "much", "is", "pish", "tegj", "glob", "glob", "?"
            for (int i = 0; i < token.length; i++) {
                Iterator keySetIterator = codeSet.iterator();
                // 2nd inner loop to iterate through the token set ie; "pish", "tegj", "glob", "prok"
                while (keySetIterator.hasNext()) {
                    // if the token matches the keyset code then put the token ie;  "pish", "tegj", "glob", "glob"
                    if (token[i].equalsIgnoreCase((String) keySetIterator.next())) {
                        galacticCodeList.add(token[i]);
                    }
                }
            }
                List<StringBuilder> outList = getRomanCodeAsString(galacticCodeList,romanCodeList,tokenToRomanMap);
                StringBuilder galacticCodeStr = outList.get(0);// as I know the index
                StringBuilder romanCodeStr = outList.get(1);// as I know the index

                int decimalValueForGalacticCodes = numberConverter.convert(romanCodeStr.toString());

                finalDisplayString = galacticCodeStr + "is " + decimalValueForGalacticCodes;
                System.out.println(finalDisplayString);
        }
        return finalDisplayString;
    }

    private Set<String> getGalacticCodeSet(Map<String, String> tokenToRomanMap) {
        return tokenToRomanMap.keySet();
    }

    private Map<String, List<String>> getAllGalacticQueries() {
        return galacticInputMapper.getValidAndInvalidCodeQueryList();
    }

    private List<StringBuilder> getRomanCodeAsString(List<String> galCodeLst, List<String> romanCodeLst,
                                               Map<String,String> tokenToRomanMap){
        StringBuilder galacticCodeStr = new StringBuilder();
        StringBuilder romanCodeStr = new StringBuilder();
        List<StringBuilder> outList = new ArrayList<>();
        if (galCodeLst != null) {
            // convert the galactic code to curresponding Roman number
            for (int i = 0; i < galCodeLst.size(); i++) {
                romanCodeLst.add(tokenToRomanMap.get(galCodeLst.get(i)));
                galacticCodeStr.append(galCodeLst.get(i) + " ");
            }
            /* convert the content of the List to a string */
            for (String s : romanCodeLst) {
                romanCodeStr.append(s);
            }
        }
        outList.add(galacticCodeStr);
        outList.add(romanCodeStr);
        return outList;
    }

    // This method gives the error string for the invalid queries.
    public List<String> decodeGalaticInvalidCodes() {
        Map<String, List<String>> validInvalidCodeMap = getAllGalacticQueries();
        List<String> invalidQueryList = new ArrayList<>();
        List<String> outPutList = new ArrayList<>();

        if (validInvalidCodeMap != null) invalidQueryList = validInvalidCodeMap.get(invalidList);
        Iterator invalidQueryItr = invalidQueryList.listIterator();
        while (invalidQueryItr.hasNext()) {
            if (invalidQueryItr.next() != null) {
                System.out.println(invalidQueryString);
                outPutList.add(invalidQueryString);
            }
        }
        return outPutList;
    }

    /* This method decodes the galactic metal prices to decimal
     *
     */
    public List<String> decodeGalacticMetalPrices() {

        Map<String, String> tokenToRomanMap = galacticInputMapper.tokenToRomanMapper();
        Set<String> codeSet = getGalacticCodeSet(tokenToRomanMap);
        Map<String, Float> metalToDecimalPriceMap = galacticInputMapper.metalToDecimalPriceMapper();
        Set<String> metalSet = metalToDecimalPriceMap.keySet();
        List<String> metalPriceList = new ArrayList<>(); // return List<String>

        List<String> metalPriceQueryList = galacticInputMapper.getMetalPriceQueryList();
        Iterator metalPriceItr = metalPriceQueryList.iterator();

        //outer loop to loop through the metal queries like how many Credits is glob prok Silver ?
        while (metalPriceItr.hasNext()) {
            String queryLine = (String) metalPriceItr.next(); // extract the query
            String token[] = queryLine.split(regExPattern); // break the query to tokens
            List<String> galacticCodeList = new ArrayList<>(); // List to contain the code list from the query
            String metalName = null;
            String completeTxt; // String to hold the final string from the line
            List<String> romanCodeList = new ArrayList<>();
            // inner loop 1 to go through the token arr ; ie: "how", "many", "Credits", "is", "glob", "prok", "Silver", "?"
            for (int i = 0; i < token.length; i++) {
                Iterator keySetIterator = codeSet.iterator();
                // inner loop 2 to go through the galactic code set
                while (keySetIterator.hasNext()) {
                    if (token[i].equalsIgnoreCase((String) keySetIterator.next())) {
                        galacticCodeList.add(token[i]);
                    }
                }
                Iterator metalnameItr = metalSet.iterator();
                while(metalnameItr.hasNext()){
                    if(token[i].equalsIgnoreCase((String)metalnameItr.next())){
                        metalName = token[i];
                    }
                }
            }
            List<StringBuilder> outList = getRomanCodeAsString(galacticCodeList,romanCodeList,tokenToRomanMap);
            StringBuilder galacticCodeStr = outList.get(0);// as I know the index
            StringBuilder romanCodeStr = outList.get(1);// as I know the index

            int decimalValueForGalacticCodes = numberConverter.convert(romanCodeStr.toString());

            Float metalUnitPrice = metalToDecimalPriceMap.get(metalName);
            //calculate the total price
            Float totalPrice = metalUnitPrice * decimalValueForGalacticCodes;

            completeTxt = galacticCodeStr + metalName + " is " + totalPrice.toString() + " " + credits;
            metalPriceList.add(completeTxt);
            System.out.println(completeTxt);
        }
        return metalPriceList;
    }
}
