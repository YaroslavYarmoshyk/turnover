package com.turnover.service.impl;

import com.turnover.model.Month;
import com.turnover.model.Quarter;
import com.turnover.service.MonthService;
import com.turnover.service.QuarterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.turnover.util.Constants.CURRENT;
import static com.turnover.util.Constants.CURRENT_PREV;
import static com.turnover.util.Constants.FIRST;
import static com.turnover.util.Constants.FIRST_PREV;
import static com.turnover.util.Constants.SECOND;
import static com.turnover.util.Constants.SECOND_PREV;
import static com.turnover.util.Constants.THIRD;
import static com.turnover.util.Constants.THIRD_PREV;

@Service
@RequiredArgsConstructor
public class QuarterServiceImpl implements QuarterService {
    private Integer plannedMonthNumber;
    private Integer plannedYearNumber;
    private final MonthService monthService;

    @Override
    public Quarter createQuarter(final Integer monthNumber, final Integer yearNumber) {
        plannedMonthNumber = monthNumber;
        plannedYearNumber = yearNumber;

        return new Quarter().setMonths(getMonthsMap());
    }

    @Override
    public Quarter createQuarter() {
        plannedMonthNumber = LocalDate.now().getMonthValue();
        plannedYearNumber = Integer.valueOf(String.valueOf(LocalDate.now().getYear()).substring(1));

        return new Quarter().setMonths(getMonthsMap());
    }

    private Map<String, Month> getMonthsMap() {
        final Map<String, Month> months = new HashMap<>();
        putMonth(months, getValidMonthNumber(plannedMonthNumber - 1), CURRENT, CURRENT_PREV);
        putMonth(months, plannedMonthNumber, FIRST, FIRST_PREV);
        putMonth(months, getValidMonthNumber(plannedMonthNumber + 1), SECOND, SECOND_PREV);
        putMonth(months, getValidMonthNumber(plannedMonthNumber + 2), THIRD, THIRD_PREV);

        return months;
    }

    private void putMonth(final Map<String, Month> months,
                          final Integer monthNumber,
                          final String current,
                          final String prev) {
        final int curYear = defineYearNumber(monthNumber, true);
        final int prevYear = defineYearNumber(monthNumber, false);
        final Month firstMonthCurYear = monthService.createMonth(monthNumber, curYear);
        final Month firstMonthPrevYear = monthService.createMonth(monthNumber, prevYear);
        months.put(current, firstMonthCurYear);
        months.put(prev, firstMonthPrevYear);
    }

    private int defineYearNumber(final Integer monthNumber, final boolean isCurrent) {
        return isCurrent ? defineCurrentYearNumber(monthNumber) : definePrevYearNumber(monthNumber);
    }

    private int defineCurrentYearNumber(final Integer monthNumber) {
        if (plannedMonthNumber <= 2 && monthNumber >= 11) {
            return plannedYearNumber - 1;
        }
        if (plannedMonthNumber - monthNumber > 2) {
            return plannedYearNumber + 1;
        }
        return plannedYearNumber;
    }

    private int definePrevYearNumber(final Integer monthNumber) {
        if (plannedMonthNumber <= 2 && monthNumber >= 11) {
            return plannedYearNumber - 2;
        }
        if (plannedMonthNumber - monthNumber > 2) {
            return plannedYearNumber;
        }
        return plannedYearNumber - 1;
    }

    private static int getValidMonthNumber(final int monthNumber) {
        if (monthNumber > 12) {
            return monthNumber - 12;
        }
        return monthNumber == 0 ? 12 : monthNumber;
    }
}
