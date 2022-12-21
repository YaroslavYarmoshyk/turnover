package com.turnover.service.impl;

import com.turnover.reader.ExcelReader;
import com.turnover.service.ExcelReaderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelReaderServiceImpl implements ExcelReaderService {

    @Override
    public List<ExcelReader.Row> read(final String filePath, final String sheetName) throws Exception {
        final ExcelReader reader = new ExcelReader();
        return reader.readSheet(filePath, sheetName);
    }
}
