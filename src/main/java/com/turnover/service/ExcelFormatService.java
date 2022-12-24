package com.turnover.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface ExcelFormatService {
    void formatTurnOver(final Workbook workbook, final List<Integer> regionRows);
}
