package com.turnover.service.impl;

import com.turnover.model.Month;
import com.turnover.service.MonthService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.turnover.util.Constants.APRIL;
import static com.turnover.util.Constants.AUGUST;
import static com.turnover.util.Constants.DECEMBER;
import static com.turnover.util.Constants.FEBRUARY;
import static com.turnover.util.Constants.JANUARY;
import static com.turnover.util.Constants.JULY;
import static com.turnover.util.Constants.JUNE;
import static com.turnover.util.Constants.MARCH;
import static com.turnover.util.Constants.MAY;
import static com.turnover.util.Constants.NOVEMBER;
import static com.turnover.util.Constants.OCTOBER;
import static com.turnover.util.Constants.SEPTEMBER;

@Service
public class MonthServiceImpl implements MonthService {

    @Override
    public Month createMonth(final Integer monthNumber, final Integer yearNumber) {
        final String monthName = defineMonthPart(monthNumber) + " " + yearNumber;
        final Integer days = defineDays(monthNumber, yearNumber);
        final Integer saturdays = defineSaturdays(monthNumber, yearNumber);

        return new Month().setName(monthName)
                .setDays(days)
                .setSaturdays(saturdays);
    }

    private String defineMonthPart(final Integer monthNumber) {
        return switch (monthNumber) {
            case 1 -> JANUARY;
            case 2 -> FEBRUARY;
            case 3 -> MARCH;
            case 4 -> APRIL;
            case 5 -> MAY;
            case 6 -> JUNE;
            case 7 -> JULY;
            case 8 -> AUGUST;
            case 9 -> SEPTEMBER;
            case 10 -> OCTOBER;
            case 11-> NOVEMBER;
            case 12 -> DECEMBER;
            default -> throw new IllegalStateException("Unexpected value");
        };
    }

    private int defineDays(final Integer monthNumber, final Integer yearNumber) {
        final YearMonth yearMontObject = YearMonth.of(2000 + yearNumber, monthNumber);
        return yearMontObject.lengthOfMonth();
    }

    private int defineSaturdays(final Integer monthNumber, final Integer yearNumber) {
        LocalDate fromDate = LocalDate.of(2000 + yearNumber, monthNumber, 1);
        LocalDate toDate = fromDate.plusMonths(1);
        int saturday = 0;
        while (!fromDate.isAfter(toDate)) {
            if (fromDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                saturday++;
            }

            fromDate = fromDate.plusDays(1);
        }
        return saturday;
    }
}
