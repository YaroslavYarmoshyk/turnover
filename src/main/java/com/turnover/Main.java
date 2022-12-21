package com.turnover;

import com.turnover.model.Quarter;
import com.turnover.model.Store;
import com.turnover.service.*;
import com.turnover.service.impl.*;

import java.util.List;

import static com.turnover.util.Constants.FILE_PATH_RESULT;

public class Main {
    public static void main(String[] args) throws Exception {
        final MonthService monthService = new MonthServiceImpl();
        final QuarterService quarterService = new QuarterServiceImpl(monthService);
        final ExcelReaderService readerService = new ExcelReaderServiceImpl();
        final StoreService storeService = new StoreServiceImpl(readerService);
        final ExcelService excelService = new ExcelServiceImpl();
//        final Quarter quarter = quarterService.createQuarter(1, 23);
        final Quarter quarter = quarterService.createQuarter();
        final List<Store> stores = storeService.getStores(quarter);
//        calculationService.calculateDynamics(stores);
//        final Workbook turnoverPlan = excelService.createTurnoverPlan(stores, quarter);
        final ExcelWriterService excelWriterService = new ExcelWriterServiceImpl();

        excelWriterService.write(FILE_PATH_RESULT, quarter, excelService.createTurnoverPlan(stores, quarter));
    }
}
