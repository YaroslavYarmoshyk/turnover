package com.turnover.service.impl;

import com.turnover.model.Month;
import com.turnover.model.Quarter;
import com.turnover.service.MonthService;
import com.turnover.service.QuarterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuarterServiceImpl implements QuarterService {
    private Integer planedMonthNumber;
    private Integer planedYearNumber;
    private final MonthService monthService;

    @Override
    public Quarter createQuarter(final Integer monthNumber, final Integer yearNumber) {
        planedMonthNumber = monthNumber;
        planedYearNumber = yearNumber;

        final Quarter quarter = new Quarter();
        return null;
    }

//    private Map<String, Month> getMonthsMap() {
//        final int currentMonthNumber = planedMonthNumber - 1 == 0 ? 12 : planedMonthNumber - 1
//        monthService.createMonth(planedMonthNumber - 1 == 0 ? 12 : planedMonthNumber - 1, defineYearNumber())
//    }

    private int defineYearNumber(final Integer monthNumber, final boolean isCurrent) {
        if (planedMonthNumber <= 2 && monthNumber >= 11 && !isCurrent) {
            return planedYearNumber - 1;
        }
        return planedYearNumber;
    }
}
