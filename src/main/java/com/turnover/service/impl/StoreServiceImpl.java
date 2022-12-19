package com.turnover.service.impl;

import com.turnover.model.Quarter;
import com.turnover.model.Store;
import com.turnover.reader.ExcelReader;
import com.turnover.service.StoreService;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static com.turnover.util.Constants.CURRENT;
import static com.turnover.util.Constants.FILE_PATH;

@Service
public class StoreServiceImpl implements StoreService {
    private static List<Store> stores;

    @Override
    public List<Store> getStores(Quarter quarter) throws Exception {
        final List<Store> stores = new ArrayList<>();
        final ExcelReader reader = new ExcelReader(FILE_PATH);

        final String currentMonthName = quarter.getMonths().get(CURRENT).getName();
        reader.readSheet(currentMonthName);

        return null;
    }

    private List<Store> getCurrentStores(final String currentMonthName, final ExcelReader reader) throws Exception {
        final List<ExcelReader.Row> rows = reader.readSheet(currentMonthName);

    }

    private int getColIndexByName(final List<ExcelReader.Cell> cells, final String colName) {
        for (int i = 0; i < cells.size(); i++) {
            final String name = getColName(cells.get(i));
            if (Objects.equals(name, colName)) {
                return i;
            }
        }
        throw new RuntimeException("Cannot define column number by name " + colName);
    }

    private String getColName(final ExcelReader.Cell cell) {
        if (Objects.equals(cell.getType(), CellType.STRING)) {
            return cell.getStringCellValue();
        }
        return null;
    }

    private List<ExcelReader.Row> getMonthData(final ExcelReader reader, final String monthName) throws Exception {
        return reader.readSheet(monthName);
    }

    private <T, R> void populateStores(final List<ExcelReader.Row> rows, final Function<T, R> function) {


    }
}
