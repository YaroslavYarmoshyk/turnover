package com.turnover.service;

import com.turnover.model.Quarter;
import com.turnover.model.Store;

import java.util.List;

public interface StoreService {
    List<Store> getStores(final Quarter quarter) throws Exception;
}
