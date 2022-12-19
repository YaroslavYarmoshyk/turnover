package com.turnover.service;

import com.turnover.model.Month;

public interface MonthService {
    Month createMonth(final Integer monthNumber, final Integer yearNumber);
}
