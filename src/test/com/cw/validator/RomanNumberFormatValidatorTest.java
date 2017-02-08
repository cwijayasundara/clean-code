package com.cw.validator;

import main.com.cw.validator.GalacticCodeFormatValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cwijayasundara on 23/01/2017.
 */
public class RomanNumberFormatValidatorTest {

    private GalacticCodeFormatValidator galacticCodeFormatValidator = new GalacticCodeFormatValidator();

    private String formatValidaitonString1;
    private String formatValidaitonString2;
    private String formatValidaitonString3;
    private String formatValidaitonString4;
    private String formatValidaitonString5;
    private String formatValidaitonString6;
    private String formatValidaitonString7;
    private String formatValidaitonString8;
    private String formatValidaitonString9;
    private String formatValidaitonString10;
    private String formatValidaitonString11;

    @Before
    public void setUp(){
         formatValidaitonString1= "LXXXIX";//89
         formatValidaitonString2 = "MCMIII"; // valid
         formatValidaitonString3= "XXXX";//invalid
         formatValidaitonString4 = "XXXIX";// valid
         formatValidaitonString5 = "MCMLXXXIV"; //valid
         formatValidaitonString6 = "MMCDXX"; //valid 2420
         formatValidaitonString7 = "XXXVIII"; //valid 38
         formatValidaitonString8 = "MCMXCIX"; //valid 1999
         formatValidaitonString9 = "CCCLIII"; //valid 353
         formatValidaitonString10 = "MMMM"; //invalid according to the galactic rules but a valid roman number
         formatValidaitonString11 = "MMMIIII"; // invalid

    }

    @Test
    public void shouldTestWithAvalidNumberWith3xxx(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString1);
        Assert.assertEquals(isValid,true);
    }

    @Test
    public void shouldTestWithAvalidNumberWith3iii(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString2);
        Assert.assertEquals(isValid,true);
    }

    @Test
    public void shouldTestWithAnInvalidNumberxxxx(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString3);
        Assert.assertEquals(isValid,false);
    }

    @Test
    public void shouldTestWithAnInvalidNumberdld(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString4);
        Assert.assertEquals(isValid,true);
    }

    @Test
    public void shouldTestWithAValidNumberWithThreexxx(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString5);
        Assert.assertEquals(isValid,true);
    }

    @Test
    public void shouldTestWithAnumberGraterThanTwoThousand(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString6);
        Assert.assertEquals(isValid,true);
    }

    @Test
    public void shouldTestWithAsmallerNumber(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString7);
        Assert.assertEquals(isValid,true);
    }

    @Test
    public void shouldTestWithAnumberCloseToTwoThousand(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString8);
        Assert.assertEquals(isValid,true);
    }

    @Test
    public void shoudTestWithAnumberWithAcombinationOfTwoPairsOfThree(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString9);
        Assert.assertEquals(isValid,true);
    }

    @Test
    public void shouldTestWithAnInvalidNumber(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString10);
        Assert.assertEquals(isValid,false);
    }

    @Test
    public void shouldTestWithArandomNumber(){
        boolean isValid = galacticCodeFormatValidator.isValidFormat(formatValidaitonString11);
        Assert.assertEquals(isValid,false);
    }

}
