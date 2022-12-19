package com.turnover.service.impl;

import com.turnover.model.Store;
import com.turnover.service.CalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Objects;

@Service
public class CalculationServiceImpl implements CalculationService {

    @Override
    public void calculateDynamics(final List<Store> stores) {
        for (Store store: stores) {
            calculateDynamicsByStore(store);
        }
    }

    private void calculateDynamicsByStore(final Store store) {
        store.setFirstDynamic(calculateDynamic(store.getAvgSalPrevMonth(), store.getAvgSalFirstMonth()));
        store.setSecondDynamic(calculateDynamic(store.getAvgSalFirstMonth(), store.getAvgSalSecondMonth()));
        store.setThirdDynamic(calculateDynamic(store.getAvgSalSecondMonth(), store.getAvgSalThirdMonth()));
    }

    private BigDecimal calculateDynamic(final BigDecimal firstSales, final BigDecimal secondSales) {
        if (isZero(firstSales) || isZero(secondSales)) {
            return BigDecimal.ZERO;
        }
        return secondSales.divide(firstSales, new MathContext(4)).subtract(BigDecimal.ONE);
    }

    private boolean isZero(final BigDecimal sales) {
        if (Objects.isNull(sales)) {
            return true;
        }
        return Objects.equals(BigDecimal.ZERO, sales);
    }
}
