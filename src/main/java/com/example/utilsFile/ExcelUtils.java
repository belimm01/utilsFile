package com.example.utilsFile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashSet;

class ExcelUtils {

    static void writeToExcel(LinkedHashSet<String> content, String fileName) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Data");
        int rowNum = 0;

        for (String key : content) {
            //create a row of excelsheet
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;
            Cell cell = row.createCell(cellNum++);
            cell.setCellValue(key);
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
            System.out.println("xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
