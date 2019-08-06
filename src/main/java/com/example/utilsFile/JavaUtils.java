package com.example.utilsFile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashSet;

@SpringBootApplication
public class JavaUtils {

    public static void main(String[] args) {

        SpringApplication.run(JavaUtils.class, args);

        String fileName = "/home/mkyoung01/T-Mobile/DataHadoop/lob/XTC_SERVICE_DETAIL";
        LinkedHashSet<String> content = FIleUtils.readTxt(fileName + ".txt");
        ExcelUtils.writeToExcel(content, fileName + ".xlsx");
    }

}
