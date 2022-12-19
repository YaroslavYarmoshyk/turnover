package com.turnover;

import com.turnover.reader.ExcelReader;
import org.apache.poi.ss.usermodel.CellType;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Reader {
    public static void main(String[] args) throws Exception {
        final String filePath = "C:\\Users\\Ya.Yarmoshyk\\Desktop\\Звіти на java\\План по магазинам\\дані\\001.xlsx";
        ExcelReader excelReader = new ExcelReader(filePath);
        List<ExcelReader.Row> rows = excelReader.readSheet("Жовтень 21");
        List<ExcelReader.Cell> cells = rows.stream().flatMap(row -> row.getCells().stream()).toList();
        Optional<ExcelReader.Cell> first = cells.stream()
                .filter(cell -> cell.getType() == CellType.STRING)
                .filter(cell -> Objects.nonNull(cell.getStringCellValue()) && cell.getStringCellValue().equalsIgnoreCase("окт.-21")).findFirst();
    }
}
