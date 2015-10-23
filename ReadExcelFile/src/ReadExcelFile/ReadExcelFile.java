/*
CC-BY-SA 2015 Frank Tamborello
This library is free software; you can redistribute it and/or modify it under 
the terms of Creative Commons Attribute-ShareAlike 4.0 International License:
http://creativecommons.org/licenses/by-sa/4.0/
This library is distributed in the hope that it will be useful,but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.
*/
package ReadExcelFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;


public class ReadExcelFile {
    
    String path;
    JFileChooser fc;
    Workbook wb;

    public ReadExcelFile() {
        this.fc = new JFileChooser();
        fc.showOpenDialog(null);
    }
    
    /**
     *
     * @param args
     * @return String[] encoding the chosen path
     */
    public String getPath()
    {
        try
        {
            java.io.File FAFile = fc.getSelectedFile();
            path = FAFile.getPath();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
            return path;
    }
    
    public void getFile() throws FileNotFoundException, InvalidFormatException {
        System.out.println(String.format("FER.getFile says path is: %s", path));
    
        try {
            InputStream inp = new FileInputStream(path);
            this.wb = WorkbookFactory.create(inp);
        }
        
        catch 
            (IOException | InvalidFormatException | EncryptedDocumentException 
                e) 
            {System.out.println(e.getMessage());}
    }
    
    public ArrayList<String> searchFile(String[] searchTerms) {
        ArrayList<String> searchResults = new ArrayList<String>();
        Iterator<Sheet> sheetIterator = this.wb.iterator();
        DataFormatter df = new DataFormatter();
        while(sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next(); 
            Iterator<Cell> cellIterator = row.iterator();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = df.formatCellValue(cell);
                // Iterate through the search terms on cell;
                int i;
                for (i=0; i<searchTerms.length; i++) {
                 if (cellValue.equals(searchTerms[i])) {
                    // concatenate results with a comma,
                    // then split each result on the UI side.
                    searchResults.add(String.format("%s,%s,%s", 
                            cellValue,
                            Integer.toString(cell.getRowIndex()), 
                            Integer.toString(cell.getColumnIndex())));
                 }
                }
            }
        }
        }
        return searchResults;
    }
}