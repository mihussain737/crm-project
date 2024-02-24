package com.crm.service.impl;

import com.crm.entity.Lead;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
@Service
public class ExcelHelperService {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "firstName", "lastName", "title", "email", "mobile","address","zipCode","designation","leadType","company" ,"note"};
    static String SHEET = "Tutorials";

    public static ByteArrayInputStream leadsToExcel(List<Lead> leads) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Lead lead : leads) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(lead.getFirstName());
                row.createCell(1).setCellValue(lead.getLastName());
                row.createCell(2).setCellValue(lead.getTitle());
                row.createCell(3).setCellValue(lead.getEmail());
                row.createCell(4).setCellValue(lead.getMobile());
                row.createCell(5).setCellValue(lead.getAddress());
                row.createCell(6).setCellValue(lead.getZipCode());
                row.createCell(7).setCellValue(lead.getDesignation());
                row.createCell(8).setCellValue(lead.getLeadType());
                row.createCell(9).setCellValue(lead.getCompany());
                row.createCell(10).setCellValue(lead.getNote());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}

