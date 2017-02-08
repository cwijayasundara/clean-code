package com.cw.mapper;

import main.com.cw.mapper.GalacticInputMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by cwijayasundara on 23/01/2017.
 */

public class GalacticInputMapperTest {

    private GalacticInputMapper galacticInputMapper = new GalacticInputMapper();

    // input.txt has all the records for the test cases
    private String inputFilePath = "src/test/com/cw/resources/input.txt";

    @Before
    public void setUp() throws Exception {
        galacticInputMapper.setFilePath(inputFilePath);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shoudTestTokenToRomanMapperWithSampleValues(){
        Map<String, String> tokenToRomanMap = galacticInputMapper.tokenToRomanMapper();
        Assert.assertNotNull(tokenToRomanMap);
        Assert.assertEquals(tokenToRomanMap.get("glob"),"I");
        Assert.assertEquals(tokenToRomanMap.get("prok"),"V");
        Assert.assertEquals(tokenToRomanMap.get("pish"),"X");
        Assert.assertEquals(tokenToRomanMap.get("tegj"),"L");
    }

    @Test
    public void shouldTestMetalToDecimalPriceMappingWIthSampleValues(){
        Map<String, Float> metalToDecMap = galacticInputMapper.metalToDecimalPriceMapper();
        Assert.assertNotNull(metalToDecMap);
        Assert.assertEquals(metalToDecMap.get("Silver"),new Float("17.0"));
        Assert.assertEquals(metalToDecMap.get("Gold"),new Float("14450.0"));
        Assert.assertEquals(metalToDecMap.get("Iron"),new Float("195.5"));
    }

    @Test
    public void shouldTestCodeToDecimalMappingWithSampleCode(){
        Map<String, List<String>> metalToDecMap = galacticInputMapper.getValidAndInvalidCodeQueryList();
        Assert.assertNotNull(metalToDecMap);
        List<String> validQueryList = metalToDecMap.get("validStrList");
        Assert.assertNotNull(validQueryList);
        Assert.assertEquals(validQueryList.get(0),"how much is pish tegj glob glob ?");
        List<String> invalidQueryList = metalToDecMap.get("invalidStrList");
        Assert.assertNotNull(invalidQueryList);
        Assert.assertEquals(invalidQueryList.get(0),"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    }

    @Test
    public void shouldTestMetalRelatedQueriesWithSampleData(){
        List<String> metalToDecMap = galacticInputMapper.getMetalPriceQueryList();
        Assert.assertNotNull(metalToDecMap);
        Assert.assertEquals(metalToDecMap.get(0),"how many Credits is glob prok Silver ?");
        Assert.assertEquals(metalToDecMap.get(1),"how many Credits is glob prok Gold ?");
        Assert.assertEquals(metalToDecMap.get(2),"how many Credits is glob prok Iron ?");
    }

}