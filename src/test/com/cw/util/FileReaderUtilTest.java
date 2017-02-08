package com.cw.util;

import main.com.cw.util.FileReaderUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by cwijayasundara on 23/01/2017.
 */
public class FileReaderUtilTest {

    private FileReaderUtil fileReaderUtil = new FileReaderUtil();

    private final String inputFilePath ="src/test/com/cw/resources/input.txt";

    private final String outputFilePath = "src/test/com/cw/resources/output.txt";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldTestInputFileLoading() throws Exception {
        List<String> fileOutput = fileReaderUtil.readFile(inputFilePath);
        Assert.assertNotNull(fileOutput);
        Assert.assertEquals(fileOutput.size(),12);
        Assert.assertEquals(fileOutput.get(0), "glob is I");
        Assert.assertEquals(fileOutput.get(4), "glob glob Silver is 34 Credits");
        Assert.assertEquals(fileOutput.get(11), "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    }

    @Test
    public void shouldTestOutputFileLoading() throws Exception {
        List<String> fileOutput = fileReaderUtil.readFile(outputFilePath);
        Assert.assertNotNull(fileOutput);
        Assert.assertEquals(fileOutput.size(),5);
        Assert.assertEquals(fileOutput.get(0), "pish tegj glob glob is 42");
        Assert.assertEquals(fileOutput.get(2), "glob prok Gold is 57800 Credits");
        Assert.assertEquals(fileOutput.get(4), "I have no idea what you are talking about");
    }

}