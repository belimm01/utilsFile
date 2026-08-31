package com.example.utilsFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class ExcelUtils {

  private ExcelUtils() {}

  static void writeToExcel(LinkedHashSet<String> content, String fileName) {
    try (var workbook = new XSSFWorkbook()) {
      var sheet = workbook.createSheet("Data");
      int rowNum = 0;
      for (String value : content) {
        var row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(value);
      }
      try (OutputStream out = Files.newOutputStream(Path.of(fileName))) {
        workbook.write(out);
      }
      System.out.println("xlsx written successfully on disk.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
