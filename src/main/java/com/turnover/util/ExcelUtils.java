package com.turnover.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public final class ExcelUtils {
    public static void createCells(final Sheet sheet, final int startRow, final int startCol, final int endRow, final int endCol) {
        for (int i = startRow; i <= endRow; i++) {
            final Row row = sheet.createRow(i);
            for (int j = startCol; j <= endCol; j++) {
                row.createCell(j);
            }
        }
    }
}
