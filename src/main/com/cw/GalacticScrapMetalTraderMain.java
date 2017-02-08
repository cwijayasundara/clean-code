package main.com.cw;

import main.com.cw.decoder.GalacticQueryDecoder;

/**
 * Created by cwijayasundara on 23/01/2017.
 */

public class GalacticScrapMetalTraderMain {

    private static GalacticQueryDecoder queryDecoder = new GalacticQueryDecoder();

    public static void main(String args[]){
        queryDecoder.decodeGalacticCodeToDecimal();
        queryDecoder.decodeGalacticMetalPrices();
        queryDecoder.decodeGalaticInvalidCodes();
    }
}

