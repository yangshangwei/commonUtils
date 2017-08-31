package com.artisan.commonUtils.office.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 

 * @ClassName: ExcelUtil

 * @Description: http://yunzhu.iteye.com/blog/1836696
 * 				 http://ray-yui.iteye.com/blog/1922157
 * 				http://blog.csdn.net/u011851478/article/details/52292861

 * @author: Mr.Yang

 * @date: 2017年8月30日 上午12:55:11
 */
public class ExcelUtil {
	 private File excelFile;  
	  
	    private InputStream fileInStream;  
	  
	    private Workbook workBook;  
	  
	    public ExcelUtil(File file) throws Exception {  
	        this.excelFile = file;  
	        this.fileInStream = new FileInputStream(this.excelFile);  
	        this.workBook = WorkbookFactory.create(this.fileInStream);  
	    }  
	  
	   
	  
	    /** 
	     * 写入一组值 
	     *  
	     * @param sheetNum 
	     *            写入的sheet的名称 
	     * @param fillRow 
	     *            是写入行还是写入列 
	     * @param startRowNum 
	     *            开始行号 
	     * @param startColumnNum 
	     *            开始列号 
	     * @param contents 
	     *            写入的内容数组 
	     * @throws Exception 
	     */  
	    public void writeArrayToExcel(String sheetName, boolean fillRow,  
	            int startRowNum, int startColumnNum, Object[] contents)  
	            throws Exception {  
	        Sheet sheet = this.workBook.getSheet(sheetName);  
	        writeArrayToExcel(sheet, fillRow, startRowNum, startColumnNum, contents);  
	    }  
	  
	    private void writeArrayToExcel(Sheet sheet, boolean fillRow,  
	            int startRowNum, int startColumnNum, Object[] contents)  
	            throws Exception {  
	        for (int i = 0, length = contents.length; i < length; i++) {  
	            int rowNum;  
	            int columnNum;  
	            // 以行为单位写入  
	            if (fillRow) {  
	                rowNum = startRowNum;  
	                columnNum = startColumnNum + i;  
	            }  
	            // 　以列为单位写入  
	            else {  
	                rowNum = startRowNum + i;  
	                columnNum = startColumnNum;  
	            }  
	            this.writeToCell(sheet, rowNum, columnNum,  
	                    convertString(contents[i]));  
	        }  
	    }  
	  
	    /** 
	     * 向一个单元格写入值 
	     *  
	     * @param sheetNum 
	     *            sheet的编号 
	     * @param rowNum 
	     *            行号 
	     * @param columnNum 
	     *            列号 
	     * @param value 
	     *            写入的值 
	     * @throws Exception 
	     */  
	    public void writeToExcel(int sheetNum, int rowNum, int columnNum,  
	            Object value) throws Exception {  
	        Sheet sheet = this.workBook.getSheetAt(sheetNum);  
	        this.writeToCell(sheet, rowNum, columnNum, value);  
	    }  
	  
	    /** 
	     * 向一个单元格写入值 
	     *  
	     * @param sheetName 
	     *            sheet的名称 
	     * @param columnRowNum 
	     *            单元格的位置 
	     * @param value 
	     *            写入的值 
	     * @throws Exception 
	     */  
	    public void writeToExcel(String sheetName, int rowNum, int columnNum,  
	            Object value) throws Exception {  
	        Sheet sheet = this.workBook.getSheet(sheetName);  
	        this.writeToCell(sheet, rowNum, columnNum, value);  
	    }  
	  
	    /** 
	     * 向一个单元格写入值 
	     *  
	     * @param sheetNum 
	     *            sheet的编号 
	     * @param columnRowNum 
	     *            单元格的位置 
	     * @param value 
	     *            写入的值 
	     * @throws Exception 
	     */  
	    public void writeToExcel(int sheetNum, String columnRowNum, Object value)  
	            throws Exception {  
	        Sheet sheet = this.workBook.getSheetAt(sheetNum);  
	        this.writeToCell(sheet, columnRowNum, value);  
	    }  
	  
	    /** 
	     * 向一个单元格写入值 
	     *  
	     * @param sheetNum 
	     *            sheet的名称 
	     * @param columnRowNum 
	     *            单元格的位置 
	     * @param value 
	     *            写入的值 
	     * @throws Exception 
	     */  
	    public void writeToExcel(String sheetName, String columnRowNum, Object value)  
	            throws Exception {  
	        Sheet sheet = this.workBook.getSheet(sheetName);  
	        this.writeToCell(sheet, columnRowNum, value);  
	    }  
	  
	    private void writeToCell(Sheet sheet, String columnRowNum, Object value)  
	            throws Exception {  
	        int[] rowNumColumnNum = convertToRowNumColumnNum(columnRowNum);  
	        int rowNum = rowNumColumnNum[0];  
	        int columnNum = rowNumColumnNum[1];  
	        this.writeToCell(sheet, rowNum, columnNum, value);  
	    }  
	  
	    /** 
	     * 将单元格的行列位置转换为行号和列号 
	     *  
	     * @param columnRowNum 
	     *            行列位置 
	     * @return 长度为2的数组，第1位为行号，第2位为列号 
	     */  
	    private static int[] convertToRowNumColumnNum(String columnRowNum) {  
	        columnRowNum = columnRowNum.toUpperCase();  
	        char[] chars = columnRowNum.toCharArray();  
	        int rowNum = 0;  
	        int columnNum = 0;  
	        for (char c : chars) {  
	            if ((c >= 'A' && c <= 'Z')) {  
	                columnNum = columnNum * 26 + ((int) c - 64);  
	            } else {  
	                rowNum = rowNum * 10 + new Integer(c + "");  
	            }  
	        }  
	        return new int[] { rowNum - 1, columnNum - 1 };  
	    }  
	  
	    private void writeToCell(Sheet sheet, int rowNum, int columnNum,  
	            Object value) throws Exception {  
	        Row row = sheet.getRow(rowNum);  
	        Cell cell = row.getCell(columnNum);  
	        if (cell == null) {  
	            cell = row.createCell(columnNum);  
	        }  
	        cell.setCellValue(convertString(value));  
	    }  
	  
	    /** 
	     * 读取一个单元格的值 
	     *  
	     * @param sheetName 
	     *            sheet的名称 
	     * @param columnRowNum 
	     *            单元格的位置 
	     * @return 
	     * @throws Exception 
	     */  
	    @SuppressWarnings({ "unused", "deprecation" })
		private static Object getColumnValue(Row row, int i) {  
	        Cell cell = row.getCell(i);  
	        int type = cell.getCellType();  
	        switch (type) {  
	        case Cell.CELL_TYPE_STRING:  
	            return (Object)cell.getStringCellValue();  
	        case Cell.CELL_TYPE_NUMERIC:  
	            Double value = cell.getNumericCellValue();  
	            return (Object)(value.intValue());  
	        case Cell.CELL_TYPE_BLANK:  
	            return (Object)"";  
	        case Cell.CELL_TYPE_BOOLEAN:  
	            return (Object)cell.getBooleanCellValue();  
	        case Cell.CELL_TYPE_FORMULA:  
	            return (Object)cell.getArrayFormulaRange().formatAsString();  
	        default:  
	            return null;  
	        }  
	    }  
	  
	    /** 
	     * 获取单元格中的值 
	     *  
	     * @param cell 单元格 
	     * @return 
	     */  
	    @SuppressWarnings({ "unused", "deprecation" })
		private static Object getCellValue(Cell cell) {  
	        int type = cell.getCellType();  
	        switch (type) {  
	        case Cell.CELL_TYPE_STRING:  
	            return (Object) cell.getStringCellValue();  
	        case Cell.CELL_TYPE_NUMERIC:  
	            Double value = cell.getNumericCellValue();  
	            return (Object) (value.intValue());  
	        case Cell.CELL_TYPE_BOOLEAN:  
	            return (Object) cell.getBooleanCellValue();  
	        case Cell.CELL_TYPE_FORMULA:  
	            return (Object) cell.getArrayFormulaRange().formatAsString();  
	        case Cell.CELL_TYPE_BLANK:  
	            return (Object) "";  
	        default:  
	            return null;  
	        }  
	    }  
	  
	    /** 
	     * 插入一行并参照与上一行相同的格式 
	     *  
	     * @param sheetNum 
	     *            sheet的编号 
	     * @param rowNum 
	     *            插入行的位置 
	     * @throws Exception 
	     */  
	    public void insertRowWithFormat(int sheetNum, int rowNum) throws Exception {  
	        Sheet sheet = this.workBook.getSheetAt(sheetNum);  
	        insertRowWithFormat(sheet, rowNum);  
	    }  
	  
	    /** 
	     * 插入一行并参照与上一行相同的格式 
	     *  
	     * @param sheetName 
	     *            sheet的名称 
	     * @param rowNum 
	     *            插入行的位置 
	     * @throws Exception 
	     */  
	    public void insertRowWithFormat(String sheetName, int rowNum)  
	            throws Exception {  
	        Sheet sheet = this.workBook.getSheet(sheetName);  
	        insertRowWithFormat(sheet, rowNum);  
	    }  
	  
	    private void insertRowWithFormat(Sheet sheet, int rowNum) throws Exception {  
	        sheet.shiftRows(rowNum, rowNum + 1, 1);  
	        Row newRow = sheet.createRow(rowNum);  
	        Row oldRow = sheet.getRow(rowNum - 1);  
	        for (int i = oldRow.getFirstCellNum(); i < oldRow.getLastCellNum(); i++) {  
	            Cell oldCell = oldRow.getCell(i);  
	            if (oldCell != null) {  
	                CellStyle cellStyle = oldCell.getCellStyle();  
	                newRow.createCell(i).setCellStyle(cellStyle);  
	            }  
	        }  
	    }  
	  
	    /** 
	     * 重命名一个sheet 
	     *  
	     * @param sheetNum 
	     *            sheet的编号 
	     * @param newName 
	     *            新的名称 
	     */  
	    public void renameSheet(int sheetNum, String newName) {  
	        this.workBook.setSheetName(sheetNum, newName);  
	    }  
	  
	    /** 
	     * 重命名一个sheet 
	     *  
	     * @param oldName 
	     *            旧的名称 
	     * @param newName 
	     *            新的名称 
	     */  
	    public void renameSheet(String oldName, String newName) {  
	        int sheetNum = this.workBook.getSheetIndex(oldName);  
	        this.renameSheet(sheetNum, newName);  
	    }  
	  
	    /** 
	     * 删除一个sheet 
	     *  
	     * @param sheetName 
	     *            sheet的名称 
	     */  
	    public void removeSheet(String sheetName) {  
	        this.workBook.removeSheetAt(this.workBook.getSheetIndex(sheetName));  
	    }  
	  
	    /** 
	     * 写入Excel文件并关闭 
	     */  
	    public void writeAndClose() {  
	        if (this.workBook != null) {  
	            try {  
	                FileOutputStream fileOutStream = new FileOutputStream(  
	                        this.excelFile);  
	                this.workBook.write(fileOutStream);  
	                if (fileOutStream != null) {  
	                    fileOutStream.close();  
	                }  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        if (this.fileInStream != null) {  
	            try {  
	                this.fileInStream.close();  
	            } catch (Exception e) {  
	            }  
	        }  
	    }  
	  
	    private static String convertString(Object value) {  
	        if (value == null) {  
	            return "";  
	        } else {  
	            return value.toString();  
	        }  
	    }  
}
