package com.turnover.service.impl;

import com.turnover.model.Quarter;
import com.turnover.model.Store;
import com.turnover.service.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.turnover.util.Constants.*;

@Service
public class ExcelServiceImpl implements ExcelService {
    private Workbook workbook;

    @Override
    public Workbook createTurnoverPlan(final List<Store> stores, final Quarter quarter) {
        workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet("result");

        createHeader(sheet, quarter);
        return workbook;
    }

    private void createHeader(final Sheet sheet, final Quarter quarter) {
        final String currentMonth = quarter.getMonthName(CURRENT);
        final String currentMonthPrev = quarter.getMonthName(CURRENT_PREV);
        final String firstMonth = quarter.getMonthName(FIRST);
        final String firstMonthPrev = quarter.getMonthName(FIRST_PREV);
        final String secondMonth = quarter.getMonthName(SECOND);
        final String secondMonthPrev = quarter.getMonthName(SECOND_PREV);
        final String thirdMonth = quarter.getMonthName(THIRD);
        final String thirdMonthPrev = quarter.getMonthName(THIRD_PREV);

        final Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(STORE);

        row.createCell(1).setCellValue(AVG_SALES + currentMonth);
        row.createCell(2).setCellValue(AVG_SALES + currentMonthPrev);
        row.createCell(3).setCellValue(AVG_SALES + firstMonthPrev);
        row.createCell(4).setCellValue(AVG_SALES + secondMonthPrev);
        row.createCell(5).setCellValue(AVG_SALES + thirdMonthPrev);

        row.createCell(6).setCellValue(getDynamicName(currentMonth, firstMonthPrev));
        row.createCell(7).setCellValue(getDynamicName(firstMonthPrev, secondMonthPrev));
        row.createCell(8).setCellValue(getDynamicName(secondMonthPrev, thirdMonthPrev));

        row.createCell(9).setCellValue(AVG_SALES_DYNAMIC + firstMonthPrev);
        row.createCell(10).setCellValue(AVG_SALES_DYNAMIC + secondMonthPrev);
        row.createCell(11).setCellValue(AVG_SALES_DYNAMIC + thirdMonthPrev);

        row.createCell(12).setCellValue(DAYS + firstMonth);
        row.createCell(13).setCellValue(DAYS + secondMonth);
        row.createCell(14).setCellValue(DAYS + thirdMonth);

        row.createCell(15).setCellValue(PLAN_BY_DYNAMIC + firstMonth);
        row.createCell(16).setCellValue(PLAN_BY_DYNAMIC + secondMonth);
        row.createCell(17).setCellValue(PLAN_BY_DYNAMIC + thirdMonth);

        row.createCell(18).setCellValue(CORRECTED_PLAN + firstMonth);
        row.createCell(19).setCellValue(CORRECTED_PLAN + secondMonth);
        row.createCell(20).setCellValue(CORRECTED_PLAN + thirdMonth);

        row.createCell(21).setCellValue(TEMP_WO_DYNAMIC);
        row.createCell(22).setCellValue(TEMP_NEGATIVE_DYNAMIC);
        row.createCell(23).setCellValue(StringUtils.capitalize(firstMonth));
        row.createCell(24).setCellValue(StringUtils.capitalize(secondMonth));
        row.createCell(25).setCellValue(StringUtils.capitalize(thirdMonth));

        row.setRowStyle(getHeaderStyle());
    }

    private CellStyle getHeaderStyle() {
        final CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        final XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        return headerStyle;
    }

    private String getDynamicName(final String... months) {
        return DYNAMIC + months[1].substring(0, 3) + "/" + months[0].substring(0, 3);
    }
}
