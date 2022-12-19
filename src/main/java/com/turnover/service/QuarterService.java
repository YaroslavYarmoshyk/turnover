package com.turnover.service;

import com.turnover.model.Quarter;

public interface QuarterService {
    Quarter createQuarter(final Integer monthNumber, final Integer yearNumber);

    Quarter createQuarter();
}
