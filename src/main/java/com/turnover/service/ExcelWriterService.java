package com.turnover.service;

import com.turnover.model.Quarter;
import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelWriterService {
    void write(final String fileDirectory, final String fileName, final Workbook workbook) throws Exception;
    void write(final String fileDirectory, final Quarter quarter, final Workbook workbook) throws Exception;
}
