/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_swing_test;

import com.jniwrapper.win32.jexcel.Application;
import com.jniwrapper.win32.jexcel.ExcelException;
import com.jniwrapper.win32.jexcel.Workbook;
import com.jniwrapper.win32.jexcel.Worksheet;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author sockand
 */
public class Export {
    
    
    public Export() throws ExcelException, FileNotFoundException{
        
        escribirExcel();
    }
    
     public static void escribirExcel() throws ExcelException, FileNotFoundException
{           Application application = new Application();
            Workbook workbook = application.createWorkbook("Custom title");
       
             //Obtaining a worksheet by its index
        //int lastIndex = workbook.getWorksheetsCount();
        //Worksheet lastWorksheet = workbook.getWorksheet(lastIndex);
}
}
