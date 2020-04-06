package com.debashish.exceltojson.utils;

import com.debashish.exceltojson.pojo.ExcelWorkbook;
import com.debashish.exceltojson.pojo.ExcelWorksheet;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ExcelToJsonConverter {

    public ExcelWorkbook convert(InputStream inputStream)
            throws IOException {
        ExcelWorkbook book = new ExcelWorkbook();

        Workbook wb = WorkbookFactory.create(inputStream);
        int numberOfSheets = wb.getNumberOfSheets();
        for (int i = 0; i < 1; i++) {
            Sheet sheet = wb.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            ExcelWorksheet excelWorksheet = new ExcelWorksheet();
            //Iterate all rows for current sheet
            for (int j = sheet.getFirstRowNum()+1; j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                boolean hasValues = false;
                ArrayList<Object> rowData = new ArrayList<Object>();

                // Iterate the column for current row
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    Cell cell = row.getCell(k);
                    if (cell != null) {
                        Object value = cellToObject(cell);
                        hasValues = hasValues || value != null;
                        rowData.add(value);
                    }
                }
                excelWorksheet.addRow(rowData);
            }
            book.addExcelWorksheet(excelWorksheet);
        }

        return book;
    }

    private Object cellToObject(Cell cell) {

        CellType type = cell.getCellType();

        if (type.equals(CellType.STRING)) {
            return cleanString(cell.getStringCellValue());
        }

        if (type.equals(CellType.BOOLEAN)) {
            return cell.getBooleanCellValue();
        }

        if (type.equals(CellType.NUMERIC)) {

            if (cell.getCellStyle().getDataFormatString().contains("%")) {
                return cell.getNumericCellValue() * 100;
            }

            return numeric(cell);
        }

        return null;

    }

    private String cleanString(String str) {
        return str.replace("\n", "").replace("\r", "");
    }

    private Object numeric(Cell cell) {
        return cell.getNumericCellValue();
    }
}
