package com.turnover;

import com.turnover.model.Quarter;
import com.turnover.service.MonthService;
import com.turnover.service.QuarterService;
import com.turnover.service.StoreService;
import com.turnover.service.impl.MonthServiceImpl;
import com.turnover.service.impl.QuarterServiceImpl;
import com.turnover.service.impl.StoreServiceImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        final MonthService monthService = new MonthServiceImpl();
        final QuarterService quarterService = new QuarterServiceImpl(monthService);
        final StoreService storeService = new StoreServiceImpl();
//        final Quarter quarter = quarterService.createQuarter(1, 23);
        final Quarter quarter = quarterService.createQuarter();
        storeService.getStores(quarter);
    }
}
