package com.turnover.service.impl;

import com.turnover.service.ExcelFormatService;
import com.turnover.util.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelFormatServiceImpl implements ExcelFormatService {
    private int lastFormatRow;
    private int lastFormatColumn;
    private Workbook workbook;
    private Sheet sheet;
    private List<Integer> regionRows;

    @Override
    public void formatTurnOver(final Workbook workbook, final List<Integer> regionRows) {
        initialize(workbook, regionRows);
        formatHeader();
        formatDataStyle();
        formatValues();
        resizeColumns();
    }

    private void initialize(final Workbook workbook,
                            final List<Integer> regionRows) {
        this.workbook = workbook;
        this.sheet = workbook.getSheetAt(0);
        this.regionRows = regionRows;
        lastFormatRow = regionRows.get(regionRows.size() - 1);
        lastFormatColumn = sheet.getRow(0).getLastCellNum() - 7;
    }


    private void formatHeader() {
        sheet.getRow(0).setHeight((short) (300 * 3));
        final CellStyle darkGreyStyle = getHeaderStyle(IndexedColors.GREY_80_PERCENT);
        final CellStyle darkGreenStyle = getHeaderStyle(IndexedColors.DARK_GREEN);

        ExcelUtils.applyCellStyle(sheet, darkGreyStyle, 0, 0);
        ExcelUtils.applyCellStyle(sheet, darkGreyStyle, 0, lastFormatColumn, 0, lastFormatColumn + 6);

        ExcelUtils.applyCellStyle(sheet, darkGreenStyle, 0, 1);
        ExcelUtils.applyCellStyle(sheet, darkGreenStyle, 0, 18, 0, 18 + 2);

        ExcelUtils.applyCellStyle(sheet, darkGreyStyle, 0, 2, 0, 2 + 4);
        ExcelUtils.applyCellStyle(sheet, darkGreyStyle, 0, 6, 0, 6 + 11);
    }

    private void formatDataStyle() {
        final CellStyle dataStyle = getDataStyle();
        ExcelUtils.applyCellStyle(sheet, dataStyle, 1, 0, lastFormatRow, lastFormatColumn);
        final CellStyle headerStyle = getHeaderStyle(IndexedColors.GREY_50_PERCENT);
        for (int i = 0; i < regionRows.size() - 1; i++) {
            int regionRow = regionRows.get(i);
            ExcelUtils.applyCellStyle(sheet, headerStyle, regionRow, 0, regionRow, lastFormatColumn);
        }
        final CellStyle totalStyle = getHeaderStyle(IndexedColors.GREY_80_PERCENT);
        final int totalRow = regionRows.get(regionRows.size() - 1);
        ExcelUtils.applyCellStyle(sheet, totalStyle, totalRow, 0, totalRow, lastFormatColumn);
        ExcelUtils.applyBackgroundColor(workbook, sheet, 1, 24, 1, 26, IndexedColors.YELLOW);
        ExcelUtils.applyBorders(workbook, sheet, 1, 21, 1, 26, BorderStyle.THIN);
        formatStores();
    }

    private void formatStores() {
        for (int i = 1; i <= lastFormatRow; i++) {
            final CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.cloneStyleFrom(sheet.getRow(i).getCell(0).getCellStyle());
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            ExcelUtils.applyCellStyle(sheet, cellStyle, i, 0);
        }
    }

    private void formatValues() {
        final String numberFormat = "#,##0";
        final String percentFormat = "0.0%";

        ExcelUtils.applyDataFormat(workbook, sheet, 1, 1, lastFormatRow, 5, numberFormat);
        ExcelUtils.applyDataFormat(workbook, sheet, 1, 9, lastFormatRow, 9 + 11, numberFormat);

        ExcelUtils.applyDataFormat(workbook, sheet, 1, 6, lastFormatRow, 6 + 2, percentFormat);
        ExcelUtils.applyDataFormat(workbook, sheet, 1, 21, 1, 23, percentFormat);
        ExcelUtils.applyDataFormat(workbook, sheet, 1, 24, 1, 24 + 2, numberFormat);
    }

    private void resizeColumns() {
        for (int i = 1; i < lastFormatColumn + 6; i++) {
            sheet.setColumnWidth(i, 3000);
        }
        sheet.autoSizeColumn(0);
        sheet.createFreezePane(0, 1);
    }

    private CellStyle getHeaderStyle(final IndexedColors indexedColor) {
        final CellStyle cellStyle = workbook.createCellStyle();
        setDefaultSettings(cellStyle);
        cellStyle.setWrapText(true);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setFillForegroundColor(indexedColor.getIndex());

        final XSSFFont font = getFont(IndexedColors.WHITE, true);
        cellStyle.setFont(font);

        return cellStyle;
    }

    private CellStyle getDataStyle() {
        final CellStyle cellStyle = workbook.createCellStyle();
        setDefaultSettings(cellStyle);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        final XSSFFont font = getFont(IndexedColors.BLACK, false);
        cellStyle.setFont(font);

        return cellStyle;
    }

    private void setDefaultSettings(CellStyle cellStyle) {
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
    }

    private XSSFFont getFont(final IndexedColors indexedColor, final boolean isBold) {
        final XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Calibri");
        font.setFontHeightInPoints((short) 11);
        font.setColor(indexedColor.getIndex());
        font.setBold(isBold);
        return font;
    }
}
