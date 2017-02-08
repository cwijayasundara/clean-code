package com.cw.converter;

import main.com.cw.converter.GalacticCodeToDecimalConverter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cwijayasundara on 23/01/2017.
 */
public class GalacticCodeToDecimalConverterTest {

    private GalacticCodeToDecimalConverter romanToArabicConverter;

    private String testString1;
    private String testString2;
    private String testString3;
    private String testString4;
    private String testString5;
    private String testString6;
    private String testString7;
    private String testString8;
    private String testString9;
    private String testString10;

    @Before
    public void setUp(){
         romanToArabicConverter = new GalacticCodeToDecimalConverter();
         testString1 = "MMVI"; //2006
         testString2 = "MCMXLIV"; //1944
         testString3 = "MCMIII"; //1903
         testString4= "LXXXIX";//89
         testString5 = "MMCDXX"; //valid 2420
         testString6 = "XXXVIII"; //valid 38
         testString7 = "MCMXCIX"; //valid 1999
         testString8 = "CCCLIII"; //valid 353
         testString9 = "MMMM"; //invalid according to the galactic rules but a valid roman number
         testString10 = "MMMIIII"; // invalid
    }

    @Test
    public void testRomanNumaralToArabicHappyPath1(){
        int nubmer = romanToArabicConverter.convert(testString1);
        Assert.assertEquals(nubmer,2006);
    }

    @Test
    public void testAComplexRomanLiteral(){
        int nubmer = romanToArabicConverter.convert(testString2);
        Assert.assertEquals(nubmer,1944);
    }

    @Test
    public void testRomanNumaralToArabicHappyPath2(){
        int nubmer = romanToArabicConverter.convert(testString3);
        Assert.assertEquals(nubmer,1903);
    }

    @Test
    public void shouldTestEightyNine(){
        int nubmer = romanToArabicConverter.convert(testString4);
        Assert.assertEquals(nubmer,89);
    }

    @Test
    public void shouldTestAnumberGraterThanTwoThousand(){
        int nubmer = romanToArabicConverter.convert(testString5);
        Assert.assertEquals(nubmer,2420);
    }

    @Test
    public void shouldTestAsmallerNumber(){
        int nubmer = romanToArabicConverter.convert(testString6);
        Assert.assertEquals(nubmer,38);
    }

    @Test
    public void shouldTestAnumberJustLessThanTwoThousand(){
        int nubmer = romanToArabicConverter.convert(testString7);
        Assert.assertEquals(nubmer,1999);
    }

    @Test
    public void shouldTestAnumberWithAmixOfCodes(){
        int nubmer = romanToArabicConverter.convert(testString8);
        Assert.assertEquals(nubmer,353);
    }

    @Test
    public void shouldTestWithAninvalidNumber(){
        int nubmer = romanToArabicConverter.convert(testString9);
        Assert.assertEquals(nubmer,-1);
    }

    @Test
    public void shouldTestWithAnotherInvalidNumber(){
        int nubmer = romanToArabicConverter.convert(testString10);
        Assert.assertEquals(nubmer,-1);
    }


    @After
    public void tierDown() {

    }

}
