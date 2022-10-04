package com.excel.mysql.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.excel.mysql.model.Audience;

public class ExcelHelper {
	
	//it will check excel format type
	
  public static boolean checkExcelFormat(MultipartFile file) {
	String contentFile=  file.getContentType();
	return (contentFile.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))?
	 true: false;
  }
  
  //it will convert excel file into list
  
  public static List<Audience> convertExceltoAudiences(InputStream is){
	  List<Audience> list= new ArrayList<>();
	   try {

           XSSFWorkbook workbook = new XSSFWorkbook(is);

           XSSFSheet sheet = workbook.getSheet("data");

           int rowNumber = 0;
           Iterator<Row> iterator = sheet.iterator();

           while (iterator.hasNext()) {
               Row row = iterator.next();

               if (rowNumber == 0) {
                   rowNumber++;
                   continue;
               }

               Iterator<Cell> cells = row.iterator();

               int cid = 0;

               Audience a = new Audience();

               while (cells.hasNext()) {
                   Cell cell = cells.next();

                   switch (cid) {
                       case 0:
                           a.setNumbers((int) cell.getNumericCellValue());
                           break;
                       case 1:
                           a.setUsername(cell.getStringCellValue());
                           break;
                       case 2:
                          a.setName(cell.getStringCellValue());
                           break;
                       default:
                           break;
                   }
                   cid++;

               }

               list.add(a);


           }


       } catch (Exception e) {
           e.printStackTrace();
       }
       return list;

   }
  }

