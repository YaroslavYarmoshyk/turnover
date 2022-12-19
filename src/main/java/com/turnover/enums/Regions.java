package com.turnover.enums;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public enum Regions {
    LVIV_1("Львів 1"),
    LVIV_2("Львів 2"),
    UZHHOROD("Ужгород"),
    TERNOPIL("Тернопіль"),
    RIVNE_VOLYN("Рівне, Волинь"),
    RIVNE("Рівне"),
    VOLYN("Волинь");

    private final String regionName;

    Regions(final String regionName) {
        this.regionName = regionName;
    }

    public static boolean isRegion(final String region) {
        if (Strings.isBlank(region)) {
            return false;
        }
        if (region.contains("Разом")) {
            return true;
        }
        return Arrays.stream(Regions.values()).anyMatch(r -> region.equalsIgnoreCase(r.regionName));
    }
}
