package com.debashish.exceltojson;

import com.debashish.exceltojson.pojo.Person;
import com.debashish.exceltojson.utils.ExcelToJavaBeans;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.util.List;

@SpringBootApplication
public class ExcelToJsonApplication implements CommandLineRunner {


    @Value("classpath:workbook.xlsx")
    private Resource excelfile;

    @Value("classpath:personConfig.xml")
    private Resource jXLSConfigFile;

    public static void main(String[] args) {
        SpringApplication.run(ExcelToJsonApplication.class, args);
    }

    public Resource getExcelfile() {
        return excelfile;
    }

    public Resource getjXLSConfigFile() {
        return jXLSConfigFile;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Person> persons = ExcelToJavaBeans.parseExcelFileToBeans(getExcelfile().getInputStream(),
                getjXLSConfigFile().getInputStream());

System.out.println("---------->"+persons);




       /* Map<String, Object> properties = new HashMap<String, Object>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonGeneratorFactory jsonGeneratorFactory = Json.createGeneratorFactory(properties);
        String jsonFIle = "";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JsonGenerator jsonGenerator = jsonGeneratorFactory.createGenerator(byteArrayOutputStream, Charset.defaultCharset());

        InputStream inp;
        try {
            Resource resourceFIle = this.getExcelfile();
            Workbook workbook = WorkbookFactory.create(resourceFIle.getInputStream());

            // Start with obtaining the first sheet of the workbook.
            Sheet sheet = workbook.getSheetAt(0);

            jsonGenerator.writeStartObject();

            for (Iterator<Row> rowsIT = sheet.rowIterator(); rowsIT.hasNext(); ) {
                Row row = rowsIT.next();
                for (Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext(); ) {
                    Cell cell = cellsIT.next();
                    jsonGenerator.write("name", cell.getStringCellValue());
                }
            }


            jsonGenerator.writeEnd();
            jsonGenerator.close();
            System.out.println("------->" + byteArrayOutputStream.toString());

        } catch (FileNotFoundException | EncryptedDocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
        ExcelToJsonConverter excelToJsonConverter = new ExcelToJsonConverter();
        ExcelWorkbook excelWorkbook = excelToJsonConverter.convert(this.getExcelfile().getInputStream());
        String file = excelWorkbook.toJson(true, false);

        System.out.println(file);*/


    }

}
