package com.turnover.enums;

import com.turnover.model.Store;
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

    public static boolean isRegion(final String regionName) {
        if (Strings.isBlank(regionName)) {
            return false;
        }
        if (regionName.contains("Разом")) {
            return true;
        }
        return Arrays.stream(Regions.values()).anyMatch(r -> regionName.equalsIgnoreCase(r.regionName));
    }

    public static boolean isRegion(final Store region) {
        return isRegion(region.getName());
    }

    public static boolean isUnionRegions(final String regionName) {
        if (Strings.isBlank(regionName)) {
            return false;
        }
        return regionName.equalsIgnoreCase(RIVNE_VOLYN.regionName);
    }
}
