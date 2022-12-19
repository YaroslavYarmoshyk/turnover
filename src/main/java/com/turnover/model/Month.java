package com.turnover.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Month {
    private String name;
    private Integer days;
    private Integer sundays;
}
