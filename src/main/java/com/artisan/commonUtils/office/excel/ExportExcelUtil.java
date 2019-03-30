package com.artisan.commonUtils.office.excel;

//import com.unisinsight.framework.common.exception.BaseException;
//import com.unisinsight.ists.disposition.common.exception.DispositionErrorCode;
//import com.unisinsight.traffic.common.utils.annotation.Title;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @author wangdanqing wang.danqing@unisinsight.com
 * @date 2019/2/12
 */
public class ExportExcelUtil {

    /**
     * 下拉框格式设置行数
     */
    private static final Integer DOWN_ROW_NUM = 1000;

    /**
     * 构造方法
     */
    private ExportExcelUtil() {
    }

    /**
     * 导出excel
     *
     * @param response 输出流
     * @param colMap   列名map
     * @param downMap  有下拉框的列map
     */
    public static void  createExcelTemplate(HttpServletResponse response, Map<String, Object> colMap,
                                           Map<Integer, List<String>> downMap) {

        //创建工作薄
        Workbook wb = new XSSFWorkbook();
        try {
            //表头样式
            CellStyle cellStyle = wb.createCellStyle();
            // 创建一个居中格式
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            //字体样式
            Font fontStyle = wb.createFont();
            fontStyle.setFontName("新宋体");
            fontStyle.setFontHeightInPoints(Short.valueOf("12"));
            fontStyle.setBold(true);
            cellStyle.setFont(fontStyle);

            //示例样式
            CellStyle cellStyle1 = wb.createCellStyle();
            //自动换行
            cellStyle1.setWrapText(true);
            cellStyle1.setAlignment(HorizontalAlignment.CENTER);
            cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
            Font font =  wb.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
            cellStyle1.setFont(font);


            //新建sheet
            XSSFSheet sheet1 = (XSSFSheet) wb.createSheet("批量布控");
            //生成sheet1内容 第一个sheet的第一行为标题
            Row rowFirst = sheet1.createRow(0);

            //第二行示例
            Row rowSecond = sheet1.createRow(1);
            List<String> cellValues = new ArrayList<>();
            cellValues.add("违法犯罪嫌疑");
            cellValues.add("渝ACD123");
            cellValues.add("蓝");
            cellValues.add("2019/1/23");
            cellValues.add("2019/6/23");
            cellValues.add("区域布控");
            cellValues.add("区域代码，多个用“;”分隔，例如：123;456");
            cellValues.add("1");
            cellValues.add("张三-12345678910;李四-12345678910");
            cellValues.add("这是一个示例，填写时请删除");
            cellValues.add("用户id，多个用“;”分隔；第一个表示一审，第二个表示二审，以此类推");
            Integer i = 0;
            for (String cellValue : cellValues) {
                Cell cell1 = rowSecond.createCell(i++);
                cell1.setCellValue(cellValue);
                cell1.setCellStyle(cellStyle1);
            }

            //写标题
            Set<Map.Entry<String, Object>> set = colMap.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = set.iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();

                String colName = next.getKey();
                Object colNum = next.getValue();

                Integer colNum1 = Integer.valueOf((String) colNum);
                //获取每列第一行的单元格
                Cell cell = rowFirst.createCell(colNum1);
                //设置每列的列宽
                sheet1.setColumnWidth(colNum1, Integer.valueOf("4000"));
                //加样式
                cell.setCellStyle(cellStyle);
                //往单元格里写数据
                cell.setCellValue(colName);
            }

            //设置下拉框数据
            Set<Map.Entry<Integer, List<String>>> set1 = downMap.entrySet();
            Iterator<Map.Entry<Integer, List<String>>> iterator1 = set1.iterator();

            while (iterator1.hasNext()) {
                Map.Entry<Integer, List<String>> next = iterator1.next();
                //下拉列
                Integer colNum = next.getKey();
                //下拉对象
                List<String> downValue = next.getValue();
                //设置下拉框
                sheet1.addValidationData(setDataValidation(sheet1, downValue, colNum)); //超过255个报错
            }

            ServletOutputStream out = response.getOutputStream();

            out.flush();
            wb.write(out);
            out.close();
        } catch (Exception e) {
//            throw BaseException.of(DispositionErrorCode.EXCEL_CREATE_ERROR.of());
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                //Workbook流关闭异常
            }
        }
    }

    /**
     * 设置单元格下拉框,下拉列表元素不多的情况(255以内的下拉)
     *
     * @param sheet    sheet对象
     * @param textList 下拉信息
     * @param colNum   下拉框设置列
     * @return 数据有效性验证对象
     */
    private static DataValidation setDataValidation(Sheet sheet, List<String> textList, int colNum) {

        DataValidationHelper helper = sheet.getDataValidationHelper();
        //加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList.toArray(new String[textList.size()]));
        constraint.setExplicitListValues(textList.toArray(new String[textList.size()]));

        //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(1, DOWN_ROW_NUM, colNum, colNum);

        return helper.createValidation(constraint, regions);
    }

    /**
     * 中文文件名编码设置
     *
     * @param request   请求
     * @param pFileName 文件名
     * @return 转码后文件名
     */
    public static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName) {

        String filename = null;
        String agent = request.getHeader("USER-AGENT");

        if (null != agent) {

            try {
                if (-1 != agent.indexOf("Firefox")) {
                    //Firefox
                    filename = "=?UTF-8?B?" + (new String(pFileName.getBytes("UTF-8"))) + "?=";
                } else if (-1 != agent.indexOf("Chrome")) {
                    //Chrome
                    filename = new String(pFileName.getBytes(), "ISO8859-1");
                } else {
                    //IE7+
                    filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
                    //替换空格
//                    filename = StringUtils.replace(filename, "+", "%20");
                }
            } catch (UnsupportedEncodingException e) {
//                throw BaseException.of(DispositionErrorCode.FILE_NAME_FORMAT.of());
            }
        } else {
            filename = pFileName;
        }

        return filename;
    }

    /**
     * 解析excel
     *
     * @param book     ：excel
     * @param sheetNum ：获取的sheet
     * @param rowNum   : 获取的行数
     * @param tClass   ：excel数据封装的数据对象类型
     * @param <T>      解析对象
     * @return 解析对象
     * @author jian.jiao
     */
//    public static <T> T parseExcel(Workbook book, Integer sheetNum, int rowNum, Class<T> tClass) {
//
//        Sheet sheet = book.getSheetAt(sheetNum);
//        Row row = sheet.getRow(rowNum);
//        if (null == row) {
//            return null;
//        }
//
//        T t;
//        try {
//            t = tClass.newInstance();
//        } catch (Exception e) {
////            throw BaseException.of(DispositionErrorCode.EXCEL_SET_VALUE_ERROR.of());
//        }
//
//        Field[] files = t.getClass().getDeclaredFields();
//        for (int i = 0; i < files.length; i++) {
//            Field field = files[i];
//
//            Title title = field.getAnnotation(Title.class);
//            if (null == title) {
//                continue;
//            }
//            int position = title.position();
//            Cell cell = row.getCell(position, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//
//
//            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//            CellType type = cell.getCellType();
//            String value;
//            switch (type) {
//                case BLANK:
//                    value = "";
//                    break;
//                case BOOLEAN:
//                    value = String.valueOf(cell.getBooleanCellValue());
//                    break;
//                case NUMERIC:
//                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                        value = format.format(cell.getDateCellValue());
//                    } else {
//                        value = BigDecimal.valueOf(cell.getNumericCellValue()).toString();
//                    }
//                    break;
//                case STRING:
//                    value = cell.getStringCellValue();
//                    break;
//                default:
//                    value = cell.getStringCellValue();
//            }
//            setValue(t, field, value);
//        }
//        return t;
//    }

    /**
     * 对象属性设值
     *
     * @param field 属性
     * @param value 值
     * @param <T>   设值对象
     * @param t     设值对象
     * @return
     */
    private static <T> void setValue(T t, Field field, String value) {

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        field.setAccessible(true);

        Class<?> fieldType = field.getType();

        try {
            if (fieldType.equals(Date.class)) {
                field.set(t, format.parseObject(value));
            } else if (fieldType.equals(Integer.class)) {
                field.set(t, Integer.valueOf(value));
            } else if (fieldType.equals(Long.class)) {
                field.set(t, Long.valueOf(value));
            } else if (fieldType.equals(BigDecimal.class)) {
                if (value.equals("")) {
                    field.set(t, BigDecimal.ZERO);
                } else {
                    field.set(t, new BigDecimal(value));
                }
            } else {
                field.set(t, value);
            }
        } catch (IllegalAccessException e) {
//            throw BaseException.of(DispositionErrorCode.EXCEL_SET_VALUE_ERROR.of());
        } catch (ParseException e) {
//            throw BaseException.of(DispositionErrorCode.EXCEL_PARSE_DATE_ERROR.of());
        }

    }

}
