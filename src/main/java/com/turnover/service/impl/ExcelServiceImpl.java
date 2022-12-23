package com.turnover.service.impl;

import com.turnover.enums.Regions;
import com.turnover.model.Quarter;
import com.turnover.model.Store;
import com.turnover.service.ExcelService;
import com.turnover.util.FormulasUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.turnover.util.Constants.*;
import static java.util.Objects.nonNull;

@Service
public class ExcelServiceImpl implements ExcelService {
    private Map<String, int[]> regionRows = new HashMap<>();
    private Workbook workbook;

    @Override
    public Workbook createTurnoverPlan(final List<Store> stores, final Quarter quarter) {
        workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet("result");

        createHeader(sheet, quarter);
        populateSheet(sheet, stores);
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
        row.createCell(23).setCellValue(TEMP_MAX_DYNAMIC);

        row.createCell(24).setCellValue(StringUtils.capitalize(firstMonth));
        row.createCell(25).setCellValue(StringUtils.capitalize(secondMonth));
        row.createCell(26).setCellValue(StringUtils.capitalize(thirdMonth));

        row.setRowStyle(getHeaderStyle());
    }

    private void populateSheet(final Sheet sheet, final List<Store> stores) {
        int rowIndex = 1;
        for (Store store : stores) {
            final Row row = sheet.createRow(rowIndex++);
            setValueByColumn(row, 0, store.getName());
            setValueByColumn(row, 1, store.getAvgSalPrevMonthActualYear());
            setValueByColumn(row, 2, store.getAvgSalPrevMonth());
            setValueByColumn(row, 3, store.getAvgSalFirstMonth());
            setValueByColumn(row, 4, store.getAvgSalSecondMonth());
            setValueByColumn(row, 5, store.getAvgSalThirdMonth());

            setValueByColumn(row, 6, store.getFirstDynamic());
            setValueByColumn(row, 7, store.getSecondDynamic());
            setValueByColumn(row, 8, store.getThirdDynamic());

            final int rowNum = row.getRowNum();
            final int totalRow = stores.size();
            if (!Regions.isRegion(store.getName())) {
                row.createCell(9).setCellFormula(getAvgDynFormula(rowNum, 9, true));
                row.createCell(10).setCellFormula(getAvgDynFormula(rowNum, 10, false));
                row.createCell(11).setCellFormula(getAvgDynFormula(rowNum, 11, false));

                row.createCell(12).setCellValue(store.getFirstMonthDays());
                row.createCell(13).setCellValue(store.getSecondMonthDays());
                row.createCell(14).setCellValue(store.getThirdMonthDays());

                row.createCell(15).setCellFormula(getPlanByDynamicFormula(rowNum, 15));
                row.createCell(16).setCellFormula(getPlanByDynamicFormula(rowNum, 16));
                row.createCell(17).setCellFormula(getPlanByDynamicFormula(rowNum, 17));

                row.createCell(18).setCellFormula(getCorrectedPlanFormula(rowNum, totalRow, 18));
                row.createCell(19).setCellFormula(getCorrectedPlanFormula(rowNum, totalRow, 19));
                row.createCell(20).setCellFormula(getCorrectedPlanFormula(rowNum, totalRow, 20));
            }
        }
        sheet.getRow(1).createCell(21).setCellValue(0.03);
        sheet.getRow(1).createCell(22).setCellValue(-0.05);
        sheet.getRow(1).createCell(23).setCellValue(0.15);
    }

    private String getAvgDynFormula(final int row, final int currentCol, final boolean isFirstMonth) {
        final String dynamicCell = new CellReference(row, currentCol - 3).formatAsString();
        final String avgCell = new CellReference(row, isFirstMonth ? 1 : currentCol - 1).formatAsString();
        final String noDynCell = new CellReference(1, 21).formatAsString();
        final String maxDynCell = new CellReference(1, 23).formatAsString();
        final String negativeDycCell = new CellReference(1, 22).formatAsString();
        return FormulasUtils.getAvgDynamicFormula(dynamicCell, avgCell, noDynCell, maxDynCell, negativeDycCell);

    }

    private String getPlanByDynamicFormula(final int row, final int currentCol) {
        final String avgDynCell = new CellReference(row, currentCol - 6).formatAsString();
        final String daysCell = new CellReference(row, currentCol - 3).formatAsString();
        return FormulasUtils.getPlanByDynamicFormula(avgDynCell, daysCell);
    }

    private String getCorrectedPlanFormula(final int row, final int totalRow, final int currentCol) {
        final String planByDynCell = new CellReference(row, currentCol - 3).formatAsString();
        final String totalCell = new CellReference(totalRow, currentCol - 3).formatAsString();
        final String totalAmountCell = new CellReference(1, currentCol + 6).formatAsString();
        return FormulasUtils.getCorrectedPlanFormula(planByDynCell, totalCell, totalAmountCell);
    }

    private <T> void setValueByColumn(final Row row, final int col, final T value) {
        final Cell cell = row.createCell(col);
        if (nonNull(value)) {
            if (value instanceof BigDecimal) {
                if (Objects.equals(value, BigDecimal.ZERO)) {
                    cell.setCellValue("");
                } else {
                    cell.setCellValue(((BigDecimal) value).doubleValue());
                }
            } else {
                cell.setCellValue((String) value);
            }
        }
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