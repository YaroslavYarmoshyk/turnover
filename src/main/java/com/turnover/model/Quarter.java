package com.turnover.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class Quarter {
    private Map<String, Month> months;

    public String getMonthName(final String month) {
        return months.get(month).getName().toLowerCase();
    }
}
