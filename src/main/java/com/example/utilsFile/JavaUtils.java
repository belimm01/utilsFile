package com.example.utilsFile;

import java.util.LinkedHashSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaUtils {

  public static void main(String[] args) {
    SpringApplication.run(JavaUtils.class, args);

    var fileName = "/home/mkyoung01/T-Mobile/DataHadoop/lob/XTC_SERVICE_DETAIL";
    LinkedHashSet<String> content = FileUtils.readTxt(fileName + ".txt");
    ExcelUtils.writeToExcel(content, fileName + ".xlsx");
  }
}
