package com.turnover.service;

import com.turnover.model.Quarter;
import com.turnover.model.Store;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    Workbook createTurnoverPlan(final List<Store> stores, final Quarter quarter) throws IOException;
}
