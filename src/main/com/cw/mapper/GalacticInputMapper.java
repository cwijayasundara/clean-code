package main.com.cw.mapper;

import main.com.cw.converter.GalacticCodeToDecimalConverter;
import main.com.cw.converter.NumberConverter;
import main.com.cw.util.FileReaderUtil;

import java.util.*;

/**
 * Created by cwijayasundara on 23/01/2017.
 *
 * This class uses the FileReader to read the records and expose () to put the records into
 * different buckets and perform the basic logic such as to convert the galactic codes to decimal etc.
 *
 * I tend to keep the methods short and to the single responsibility pattern and code to be self explanatory
 * hence did not add a lot of comments but put details where I felt required.
 *
 * Also I tend to keep away from class level <b></b>mutable properties </b> that would work in a single threaded env
 * but breaks with concurrency if not synced.
 *
 */

public class GalacticInputMapper {

    private final FileReaderUtil fileReaderUtil = new FileReaderUtil();

    // program to interfaces
    private final NumberConverter romanNumberToArabicNumberConverter = new GalacticCodeToDecimalConverter();

    /*should be injected through a .properties file + adding a getter() and a setter() to inject env specific paths
     *and test.properties in unit tests
     */
    private String filePath = "src/main/com/cw/resources/input.txt";

    private final String  codeTokenStr="is";

    private final String regExPattern = "((?<=:)|(?=:))|( )";

    private final String creditsStr = "credits";

    private final String howMuch = "how much";

    private final String questionMark = "?";

    private final String howManyCredits = "how many Credits";

    private final String validStrList = "validStrList";

    private final String invalidStrList = "invalidStrList";

    // common method to get the file content from the file reader util
    private List<String> getFileContent(){
        return fileReaderUtil.readFile(filePath);
    }

    /* This method returns a Map<String, String> containing values like {tegj=L, glob=I, prok=V, pish=X}
     * <b>Expect the records to be of <code> <is> <roman char> format eg: glob is I </b>
     */
    public Map<String, String> tokenToRomanMapper(){

        List<String> fileInput = getFileContent(); // input records from the FileReader
        Map<String, String> tokenToIntegerMap = new HashMap<>();

        Iterator itr = fileInput.iterator();

        while(itr.hasNext()){
            String line =(String) itr.next();
            String token[]= line.split(regExPattern); // split the line to tokens

                if(token.length ==3 && (token[1].equalsIgnoreCase(codeTokenStr))){
                    tokenToIntegerMap.put(token[0], token[token.length -1]);
            }
        }
        return tokenToIntegerMap;
    }

    /* This method returns a map of <metal, price-in-decimal>
     * Expect the records to be of <b>format : <code> <code> <metal> <is> <value> <credits> format </b>
     * eg : glob glob Silver is 34 Credits
     */
    public Map<String,Float> metalToDecimalPriceMapper(){

        Map<String, String> tokenToRomanMap= tokenToRomanMapper();
        Map<String,Float> metalToPriceMap = new HashMap<>();

        List<String> fileInputList = getFileContent();
        Iterator itr = fileInputList.iterator();

        while(itr.hasNext()) {
            String line = (String) itr.next();
            // isolate the lines that gives the metal prices in credits
            if (line.toLowerCase().endsWith(creditsStr)) {
                String token[] = line.split(regExPattern);//["glob", "glob", "Silver", "is", "34", "Credits"]
                String romanValueOfTheMetal = tokenToRomanMap.get(token[0]) + tokenToRomanMap.get(token[1]); // glob glob = II
                int decimalValueOfTheMetal = romanNumberToArabicNumberConverter.convert(romanValueOfTheMetal);//II = 2
                Float totalValue = Float.parseFloat(token[4]); // total price for II Silver
                if(decimalValueOfTheMetal != 0) // to avoid infinities..
                metalToPriceMap.put(token[2],totalValue/decimalValueOfTheMetal); // {Silver=17.0}
            }

        }
        return metalToPriceMap;
    }
    /* This method returns a Map<String, List<String>> with valid queries and invalid queries>
     * The logic to determine a valid query vs an invalid query is as below.
     * if a line contains a valid <code> like pish or glob that maps to a Roman letter then its a valid query
     * Else if a line does not contain any valid code its an invalid query
     */

    public Map<String, List<String>> getValidAndInvalidCodeQueryList(){

        Map<String, List<String>> codeToDecimalMap = new HashMap<>();// return Map

        Map<String, String> tokenToRomanMap= tokenToRomanMapper();
        List<String> fileInputList = getFileContent();
        List<String> creditToDecimalValidQueryList = new ArrayList<>(); // to hold the valid queries
        List<String> creditToDecimalInvalidQueryList = new ArrayList<>(); // to hold invalid queries
        Set codeKeySet = tokenToRomanMap.keySet(); // valid codekey set ie; pish, glob etc

        Iterator itr = fileInputList.iterator();

        while(itr.hasNext()){
            String line = (String) itr.next();
            // isolate the lines with code to decimal mapping
            if (line.toLowerCase().contains(howMuch) && line.toLowerCase().contains(questionMark)){
                Iterator keySetIterator = codeKeySet.iterator();
                while(keySetIterator.hasNext()){
                    if(line.toLowerCase().contains(keySetIterator.next().toString())) {
                        creditToDecimalValidQueryList.add(line); // add lines containing valid codes like pish
                        codeToDecimalMap.put(validStrList,creditToDecimalValidQueryList);
                        break;
                    }
                    else {
                        creditToDecimalInvalidQueryList.add(line);// add lines that does not contain valid codes
                        codeToDecimalMap.put(invalidStrList, creditToDecimalInvalidQueryList);
                        break;
                    }
                }
            }
        }
       return codeToDecimalMap;
    }

    /* This method returns a List<String> queries about the metal prices
     * eg: how many Credits is glob prok Silver ?
     */
    public List<String> getMetalPriceQueryList(){

        List<String> fileInputList = getFileContent();
        Iterator itr = fileInputList.iterator();
        List<String> metalPriceQueryList = new ArrayList<>();

        while(itr.hasNext()){
            String line = (String)itr.next();
            if(line.contains(howManyCredits) && line.contains(questionMark)) {
                metalPriceQueryList.add(line);
            }
        }
        return metalPriceQueryList;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
