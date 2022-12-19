package com.turnover.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.math3.util.Pair;

import java.time.YearMonth;
import java.util.Map;

import static com.turnover.util.Constants.*;

@Data
@Accessors(chain = true)
public class Quarter {
    private Map<String, Month> months;

//    static {
//
//    }
//
//    public Quarter() {
//    }
//
//    public Quarter(final Integer monthNumber, final Integer yearNumber) {
//        planedMonthNumber = monthNumber;
//        planedYearNumber = yearNumber;
//        populateMonths();
//    }
//
//    private void populateMonths() {
//        final String currentMonth = defineMonthsName(planedMonthNumber - 1 == 0 ? 12 : planedMonthNumber - 1, true);
//        final String currentMonthPrev = defineMonthsName(planedMonthNumber - 1 == 0 ? 12 : planedMonthNumber - 1, false);
//        final String firstMonth = defineMonthsName(planedMonthNumber, true);
//        final String firstMonthPrev = defineMonthsName(planedMonthNumber, false);
//    }
//
//    private void populateCurrent(final Integer monthNumber) {
//        final String monthPart = defineMonthPart(monthNumber);
//        final int yearNumberCurrent = defineYearNumber(monthNumber, true);
//        final int yearNumberPrev = defineYearNumber(monthNumber, false);
//        final String monthName = monthPart + " " + yearNumberCurrent;
//
//        final YearMonth yearMonthCurrentObject = YearMonth.of(2000 + yearNumberCurrent, monthNumber);
//        final YearMonth yearMonthPrevObject = YearMonth.of(2000 + yearNumberPrev, monthNumber);
//        int daysInMonthCurrent = yearMonthCurrentObject.lengthOfMonth();
//        int daysInMonthPrev = yearMonthPrevObject.lengthOfMonth();
//
//        months.put(CURRENT, new Pair<>(monthName, daysInMonthCurrent));
//        months.put(CURRENT_PREV, new Pair<>(monthName, daysInMonthPrev));
//
//    }
//
//    private String defineMonthsName(final Integer monthNumber, final boolean isCurrent) {
//        return defineMonthPart(monthNumber) + " " + defineYearNumber(monthNumber, isCurrent);
//    }
//
//    private String defineMonthPart(final Integer monthNumber) {
//        return switch (monthNumber) {
//            case 1 -> JANUARY;
//            case 2 -> FEBRUARY;
//            case 3 -> MARCH;
//            case 4 -> APRIL;
//            case 5 -> MAY;
//            case 6 -> JUNE;
//            case 7 -> JULY;
//            case 8 -> AUGUST;
//            case 9 -> SEPTEMBER;
//            case 10 -> OCTOBER;
//            case 11-> NOVEMBER;
//            case 12 -> DECEMBER;
//        };
//    }
//
//    private Integer defineYearNumber(final Integer monthNumber, final boolean isCurrent) {
//        if (planedMonthNumber <= 2 && monthNumber >= 11 && !isCurrent) {
//            return planedYearNumber - 1;
//        }
//        return planedYearNumber;
//    }
//
//    public static void main(String[] args) {
//
//    }
}
