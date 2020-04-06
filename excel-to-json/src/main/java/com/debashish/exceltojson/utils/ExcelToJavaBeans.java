package com.debashish.exceltojson.utils;

import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelToJavaBeans {
    /**
     * Parses an excel file into a list of beans.
     *
     * @param <T>            the type of the bean
     * @param jxlsConfigFile the jxls config file describing how to map rows to beans
     * @return the list of beans or an empty list there are none
     * @throws Exception if there is a problem parsing the file
     */
    public static <T> List<T> parseExcelFileToBeans(final InputStream xlsfileInputStream,
                                                    final InputStream jxlsConfigFile)
            throws Exception {
        final XLSReader xlsReader = ReaderBuilder.buildFromXML(jxlsConfigFile);
        final List<T> result = new ArrayList<>();
        final Map<String, Object> beans = new HashMap<>();
        beans.put("result", result);
        // try  {
        xlsReader.read(xlsfileInputStream, beans);
        // }
        return result;
    }
}
