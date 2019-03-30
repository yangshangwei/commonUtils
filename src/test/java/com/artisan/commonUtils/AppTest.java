package com.artisan.commonUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


    @org.junit.Test
    public void ExeclTest() throws IOException {
        //创建HSSFbook的对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet0");

        //创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);

        //设置单元格的值
        cell.setCellValue("单元格的值");

        FileOutputStream outputStream = new FileOutputStream("d:\\workbook.xls");

        wb.write(outputStream);
        outputStream.flush();

    }
}
