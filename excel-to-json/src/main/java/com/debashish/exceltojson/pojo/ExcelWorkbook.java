package com.debashish.exceltojson.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class ExcelWorkbook {


    private Collection<ExcelWorksheet> sheets = new ArrayList<ExcelWorksheet>();

    public void addExcelWorksheet(ExcelWorksheet sheet) {
        sheets.add(sheet);
    }

    public String toJson(boolean pretty, boolean isWriteToFile) {
        try {
            if (pretty && isWriteToFile) {
                return new GsonBuilder().setPrettyPrinting().create().toJson(this);
            } else if (pretty) {
                return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
