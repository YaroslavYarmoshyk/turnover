package com.turnover.service.impl;

import com.turnover.model.Month;
import com.turnover.model.Quarter;
import com.turnover.model.Store;
import com.turnover.reader.ExcelReader;
import com.turnover.service.StoreService;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import static com.turnover.enums.Regions.isRegion;
import static com.turnover.util.Constants.*;

@Service
public class StoreServiceImpl implements StoreService {

    @Override
    public List<Store> getStores(Quarter quarter) throws Exception {
        final ExcelReader reader = new ExcelReader(FILE_PATH);
        final String currentMonthName = quarter.getMonths().get(CURRENT).getName();
        final List<Store> stores = getCurrentStores(currentMonthName, reader);
        final String currentMonthNamePrev = quarter.getMonths().get(CURRENT_PREV).getName();
        final String firstMonthNamePrev = quarter.getMonths().get(FIRST_PREV).getName();
        final String secondMonthNamePrev = quarter.getMonths().get(SECOND_PREV).getName();
        final String thirdMonthNamePrev = quarter.getMonths().get(THIRD_PREV).getName();

        populateAvgSales(stores, reader.readSheet(currentMonthName), Store::setAvgSalPrevMonthActualYear);
        populateAvgSales(stores, reader.readSheet(currentMonthNamePrev), Store::setAvgSalPrevMonth);
        populateAvgSales(stores, reader.readSheet(firstMonthNamePrev), Store::setAvgSalFirstMonth);
        populateAvgSales(stores, reader.readSheet(secondMonthNamePrev), Store::setAvgSalSecondMonth);
        populateAvgSales(stores, reader.readSheet(thirdMonthNamePrev), Store::setAvgSalThirdMonth);

        populateDays(stores, quarter);
        return stores;
    }

    private List<Store> getCurrentStores(final String currentMonthName, final ExcelReader reader) throws Exception {
        final List<Store> stores = new ArrayList<>();
        final List<ExcelReader.Row> rows = reader.readSheet(currentMonthName);
        for (ExcelReader.Row row : rows) {
            final ExcelReader.Cell storeCell = row.getCells().get(0);
            if (isValidStore(storeCell)) {
                final String storeCellName = storeCell.getStringCellValue();
                final String storeName = storeCellName.contains(TOTAL) ? TOTAL : storeCellName;
                final Store store = new Store().setName(storeName);
                stores.add(store);
            }
        }
        return stores;
    }

    private void populateAvgSales(final List<Store> stores,
                                  final List<ExcelReader.Row> rows,
                                  final BiConsumer<Store, BigDecimal> consumer) {
        final int salesColIndex = getColIndexByName(rows.get(0).getCells(), SALES_PER_DAY);
        for (Store store : stores) {
            if (isRegion(store.getName())) {
                continue;
            }
            final ExcelReader.Row row = getRowByStore(rows, store.getName());
            if (!row.getCells().isEmpty()) {
                final ExcelReader.Cell salesCell = row.getCells().get(salesColIndex);
                consumer.accept(store, getSalesFromCell(salesCell));
            } else {
                consumer.accept(store, BigDecimal.ZERO);
            }
        }
    }

    private void populateAvgSalesRegions(final List<Store> stores) {
        Store region = new Store();
        for (Store store: stores) {
//            if (isRegion(store))
        }
    }

    private void populateDays(final List<Store> stores, final Quarter quarter) {
        final Month firstMonth = quarter.getMonths().get(FIRST);
        final Month secondMonth = quarter.getMonths().get(SECOND);
        final Month thirdMonth = quarter.getMonths().get(THIRD);
        for (Store store: stores) {
            if (isRegion(store.getName())) {
                continue;
            }
            store.setFirstMonthDays(defineDaysQuantity(store.getName(), firstMonth.getDays(), firstMonth.getSundays()));
            store.setSecondMonthDays(defineDaysQuantity(store.getName(), secondMonth.getDays(), secondMonth.getSundays()));
            store.setThirdMonthDays(defineDaysQuantity(store.getName(), thirdMonth.getDays(), thirdMonth.getSundays()));
        }
    }

    private Integer defineDaysQuantity(final String storeName,
                                       final Integer days,
                                       final Integer saturdays) {
        if (NO_SUNDAY_STORES.contains(storeName)) {
            return days - saturdays;
        }
        if (SANITARY_DAY_STORES.contains(storeName)) {
            return days - 1;
        }
        return days;
    }

    private ExcelReader.Row getRowByStore(final List<ExcelReader.Row> rows, final String storeName) {
        return rows.stream()
                .filter(row -> isValidStore(row.getCells().get(0)) && row.getCells().get(0).getStringCellValue().equalsIgnoreCase(storeName))
                .findFirst()
                .orElse(new ExcelReader.Row());
    }

    private boolean isValidStore(final ExcelReader.Cell cell) {
        try {
            final String cellValue = cell.getStringCellValue();
            return !Strings.isBlank(cellValue) && !cellValue.contains(".-");
        } catch (final Exception e) {
            return false;
        }
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

    private BigDecimal getSalesFromCell(final ExcelReader.Cell cell) {
        if (Objects.equals(cell.getType(), CellType.STRING)) {
            final String str = cell.getStringCellValue();
            if (Strings.isBlank(str)) {
                return BigDecimal.ZERO;
            }
            try {
                return BigDecimal.valueOf(Long.parseLong(str.replace("\u00a0", "")));
            } catch (final Exception e) {
                return BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }
}
