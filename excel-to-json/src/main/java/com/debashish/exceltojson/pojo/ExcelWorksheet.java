package com.debashish.exceltojson.pojo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ExcelWorksheet {

	private ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
	public void addRow(ArrayList<Object> row) {
		data.add(row);
	}


}
