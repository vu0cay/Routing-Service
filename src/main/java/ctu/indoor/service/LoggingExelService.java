package ctu.indoor.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoggingExelService {

    private static final String FILE_PATH = "src/main/resources/data/log.xlsx";

    public void logExecutionTime(String functionName, long executionTimeMs) {
        try {
            Path path = Paths.get(FILE_PATH).getParent();
            if (path != null) Files.createDirectories(path);

            Workbook workbook;
            File file = new File(FILE_PATH);

            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    workbook = new XSSFWorkbook(fis);
                }
            } else {
                workbook = new XSSFWorkbook();
            }

            Sheet sheet = workbook.getSheet("Execution Log");
            if (sheet == null) {
                sheet = workbook.createSheet("Execution Log");
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Function");
                header.createCell(1).setCellValue("Execution Time (ms)");
                header.createCell(2).setCellValue("Timestamp");
            }

            int lastRowNum = sheet.getLastRowNum() + 1;  // Ensure appending
            Row row = sheet.createRow(lastRowNum);
            row.createCell(0).setCellValue(functionName);
            row.createCell(1).setCellValue(executionTimeMs);
            row.createCell(2).setCellValue(Instant.now().toString());

            try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOut);
            }

            workbook.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
}
