package com.anthony.file;

import com.alibaba.fastjson.JSON;
import com.anthony.common.Pair;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by chend on 2017/6/15.
 */
public class ResultWriter {

    private HSSFWorkbook wb;
    private String fileName;
    private HSSFCellStyle cellStyle;

    public ResultWriter() {
        this.fileName = JSON.toJSONString(new Date()) + ".xls";
        this.wb = new HSSFWorkbook();

        this.cellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        this.cellStyle.setDataFormat(format.getFormat("@"));
    }

    public void buildExcel(List<Pair<Pair<String, String>, Pair<String, String>>> dataList, String sheetName) {
        System.out.println("Start build Excel sheet: " + sheetName);
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFRow header = sheet.createRow(0);
        setRow(header, "date", "week", "price", "volume");

        for (int i = 0; i != dataList.size(); ++i) {
            Pair<Pair<String, String>, Pair<String, String>> data = dataList.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            setRow(row, data.getKey().getKey(), data.getKey().getValue(), data.getValue().getKey(), data.getValue().getValue());
        }

        sheet.autoSizeColumn(0);
        System.out.println("Finish build Excel sheet: " + sheetName);
    }

    private void setRow(HSSFRow row, String date, String week, String price, String volume) {
        row.createCell(0).setCellValue(date);
        row.createCell(1).setCellValue(week);
        row.createCell(2).setCellValue(price);
        row.createCell(3).setCellValue(volume);

        row.setRowStyle(cellStyle);
    }

    public void save() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                wb.write(file);
                wb.close();
                System.out.println("result was saved in " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fileName = "new " + fileName;
            save();
        }
    }
}
