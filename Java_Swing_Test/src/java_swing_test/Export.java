/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_swing_test;

import com.jniwrapper.win32.jexcel.Application;
import com.jniwrapper.win32.jexcel.Cell;
import com.jniwrapper.win32.jexcel.ExcelException;
import com.jniwrapper.win32.jexcel.FileFormat;
import com.jniwrapper.win32.jexcel.Workbook;
import com.jniwrapper.win32.jexcel.Worksheet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author sockand
 */
public class Export {
    
    
    public Export() throws ExcelException, FileNotFoundException, IOException{
        
        escribirExcel();
    }
    
     public static void escribirExcel() throws ExcelException, FileNotFoundException, IOException
{           Application application = new Application();
            Workbook workbook = application.createWorkbook("Custom title");
       
             //Obtaining a worksheet by its index
        int lastIndex = workbook.getWorksheetCount();
        Worksheet lastWorksheet = workbook.getWorksheet(lastIndex);
        Worksheet worksheet = lastWorksheet;
        
        Cell cell = worksheet.getCell("A1");
        //Setting a string value
        cell.setValue("String value");

        cell = worksheet.getCell("B1");
        //Setting a long value
        cell.setValue(220);
        
        for(int i=0; i < 7;i++){
            cell = worksheet.getCell("B" + i);
        //Setting a long value
        cell.setValue(900+i);
        }
        
        //Saving the workbook to a new file in the default Excel format
        File newXlsFile = new File("C:\\Happyflower.xls");
        workbook.saveAs(newXlsFile, FileFormat.WORKBOOKNORMAL, true);
    
}
}
