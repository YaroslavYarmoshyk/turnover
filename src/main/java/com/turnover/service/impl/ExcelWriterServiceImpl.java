package com.turnover.service.impl;

import com.turnover.model.Quarter;
import com.turnover.service.ExcelWriterService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

import static com.turnover.util.Constants.*;

@Service
public class ExcelWriterServiceImpl implements ExcelWriterService {

    @Override
    public void write(final String fileDirectory, final String fileName, final Workbook workbook) throws Exception {
        final String fileLocation = fileDirectory + fileName;

        final FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }

    @Override
    public void write(final String fileDirectory, final Quarter quarter, final Workbook workbook) throws Exception {
        final String firstMonth = quarter.getMonthName(FIRST);
        final String secondMonth = quarter.getMonthName(SECOND);
        final String thirdMonth = quarter.getMonthName(THIRD);
        final  String yearPart = firstMonth.substring(firstMonth.length() - 2);
        final String fileName = getFileName(yearPart, firstMonth, secondMonth, thirdMonth);

        write(fileDirectory, fileName, workbook);
    }

    private String getFileName(final String yearPart, final String... months) {
        final StringBuilder stringBuilder = new StringBuilder(QUARTER_PLAN);
        for (String month : months) {
            final String monthName = month.substring(0, month.length() - 3);
            if (stringBuilder.length() == QUARTER_PLAN.length()) {
                stringBuilder.append("(").append(monthName);
            } else {
                stringBuilder.append(" - ").append(monthName);
            }
        }
        return stringBuilder.append(")_")
                .append(yearPart)
                .append(".xlsx")
                .toString();
    }
}
