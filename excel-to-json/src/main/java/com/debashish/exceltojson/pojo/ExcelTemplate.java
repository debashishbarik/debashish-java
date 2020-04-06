package com.debashish.exceltojson.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelTemplate {
    private String name;
    private int age;
    private double salary;
}
