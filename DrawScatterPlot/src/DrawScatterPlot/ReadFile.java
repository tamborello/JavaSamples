/*
CC-BY-SA 2015 Frank Tamborello
This library is free software; you can redistribute it and/or modify it under 
the terms of Creative Commons Attribute-ShareAlike 4.0 International License:
http://creativecommons.org/licenses/by-sa/4.0/
This library is distributed in the hope that it will be useful,but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.
*/
package DrawScatterPlot;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile {
    private File file;
    public ReadFile (File givenFile) {
        file = givenFile;
    }
    
    public String[] OpenFile() throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader textReader = new BufferedReader(fr);
        
        int numberOfLines = readLines();
        String[] textData = new String[numberOfLines];
        
        int i;
        for (i=0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();
        }
        textReader.close();
//        System.out.println(textData[0]);
        return textData;
    }
    
    int readLines() throws IOException {
        
        FileReader file_to_read = new FileReader(file);
        BufferedReader bf = new BufferedReader(file_to_read);
        
        String aLine;
        int numberOfLines = 0;
        
        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        bf.close();
        
        return numberOfLines;
    }
}