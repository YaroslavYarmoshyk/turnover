package com.turnover.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Store {
    private String name;
    private BigDecimal avgSalPrevMonthActualYear;
    private BigDecimal avgSalPrevMonth;
    private BigDecimal avgSalFirstMonth;
    private BigDecimal avgSalSecondMonth;
    private BigDecimal avgSalThirdMonth;

    private BigDecimal firstDynamic;
    private BigDecimal secondDynamic;
    private BigDecimal thirdDynamic;

    private BigDecimal avgSalFirstMonthPlan;
    private BigDecimal avgSalSecondMonthPlan;
    private BigDecimal avgSalThirdMonthPlan;

    private Integer firstMonthDays;
    private Integer secondMonthDays;
    private Integer thirdMonthDays;

    private BigDecimal firstMonthPlan;
    private BigDecimal secondMonthPlan;
    private BigDecimal thirdMonthPlan;
}
