package main.com.cw.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by cwijayasundara on 23/01/2017.
 * This class is a generic class to read any file from the file system which can be re used.
 */

public class FileReaderUtil {

    public List<String> readFile(String path){

        FileReader fileReader = null;
        BufferedReader bufferedReader =null;

        List<String> fileContentList= new ArrayList<>();

        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            //process the file line by line
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContentList.add(line);
            }
        }catch(FileNotFoundException ex){
            System.out.println("The file can not be found.." + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("file not found");
        }catch(IOException e){
            System.out.println("IOException");
            e.printStackTrace();
            throw new RuntimeException("IOException");
        }finally{
            try {
                if(fileReader != null)
                    fileReader.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            }catch(IOException e){
                System.out.println("Exception while closing the resources ..");
            }
        }
        return fileContentList;
    }

}
