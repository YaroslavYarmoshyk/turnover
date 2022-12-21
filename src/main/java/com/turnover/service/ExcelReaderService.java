package com.turnover.service;

import com.turnover.reader.ExcelReader;

import java.util.List;

public interface ExcelReaderService {
    List<ExcelReader.Row> read(final String filePath, final String sheetName) throws Exception;
}
