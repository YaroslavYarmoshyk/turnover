package com.turnover.service;

import com.turnover.model.Store;

import java.util.List;

public interface CalculationService {
    void calculateDynamics(final List<Store> stores);
}
