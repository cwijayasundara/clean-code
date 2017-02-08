package com.cw.decoder;

import main.com.cw.decoder.GalacticQueryDecoder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by cwijayasundara on 23/01/2017.
 */
public class GalacticQueryDecoderTest {

    private final GalacticQueryDecoder galacticQueryDecoder = new GalacticQueryDecoder();

    @Before
    public void setUp() throws Exception{
    }

    @Test
    public void decodeGalacticCodeToDecimal() throws Exception {
       String finalStr = galacticQueryDecoder.decodeGalacticCodeToDecimal();
       Assert.assertEquals(finalStr, "pish tegj glob glob is 42");
    }

    @Test
    public void decodeGalaticInvalidCodes() throws Exception {
        List<String> isInvalidQuery = galacticQueryDecoder.decodeGalaticInvalidCodes();
        Assert.assertEquals(isInvalidQuery.get(0), "I have no idea what you are talking about");
    }

    @Test
    public void decodeGalacticMetalPrices() throws Exception {
        List<String> metalPriceList = galacticQueryDecoder.decodeGalacticMetalPrices();
        Assert.assertNotNull(metalPriceList);
        Assert.assertEquals(metalPriceList.get(0), "glob prok Silver is 68.0 Credits");
        Assert.assertEquals(metalPriceList.get(1), "glob prok Gold is 57800.0 Credits");
        Assert.assertEquals(metalPriceList.get(2), "glob prok Iron is 782.0 Credits");
    }

    @After
    public void tierDown() throws Exception{

    }

}