package com.turnover;

import com.turnover.model.Quarter;
import com.turnover.reader.ExcelReader;
import com.turnover.service.MonthService;
import com.turnover.service.QuarterService;
import com.turnover.service.impl.MonthServiceImpl;
import com.turnover.service.impl.QuarterServiceImpl;
import org.apache.poi.ss.usermodel.CellType;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        final MonthService monthService = new MonthServiceImpl();
        final QuarterService quarterService = new QuarterServiceImpl(monthService);
        final Quarter quarter = quarterService.createQuarter(11, 22);

//        final String filePath = "C:\\Users\\Ya.Yarmoshyk\\Desktop\\Звіти на java\\План по магазинам\\дані\\001.xlsx";
//        ExcelReader excelReader = new ExcelReader(filePath);
//        List<ExcelReader.Row> rows = excelReader.readSheet("Жовтень 21");
//        List<ExcelReader.Cell> cells = rows.stream().flatMap(row -> row.getCells().stream()).toList();
    }
}
