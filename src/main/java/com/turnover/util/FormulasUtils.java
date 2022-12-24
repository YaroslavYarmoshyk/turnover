package com.turnover.util;

import org.apache.poi.ss.util.CellReference;

public final class FormulasUtils {
    public static final String IF = "IF";
    public static final String IFERROR = "IFERROR";

    public static String getRange(final int startRow, final int startColumn, final int endRow, final int endColumn) {
        final String from = new CellReference(startRow, startColumn).formatAsString();
        final String to = new CellReference(endRow, endColumn).formatAsString();
        return from + ":" + to;
    }

    public static String getRange(final int row, final int column) {
        return new CellReference(row, column).formatAsString();
    }

    public static String getRegionSum(final int startRow, final int endRow, final int currentCol) {
        return "SUM(" + getRange(startRow, currentCol, endRow, currentCol) + ")";
    }

    public static String getUnionRegionsSum(final int startRow, final int endRow, final int currentCol) {
        return "SUM(" + getRange(startRow, currentCol) + "," + getRange(endRow, currentCol) + ")";
    }


    public static String getAvgDynamicFormula(final String dynamicCell,
                                              final String avgCell,
                                              final String noDynCell,
                                              final String maxDynCell,
                                              final String negativeDycCell) {
        return IF +
                "(" + dynamicCell + "=" + "\"\"" +
                "," + avgCell + "*(1+" + noDynCell +
                ")," + IF + "(" + dynamicCell + ">" +
                maxDynCell + "," + avgCell + "*(1+" + maxDynCell + ")," +
                IF + "(" + dynamicCell + "<" + negativeDycCell +
                "," + avgCell + "*(1+" + negativeDycCell + ")," +
                avgCell + "*(1+" + dynamicCell + "))))";
    }

    public static String getPlanByDynamicFormula(final String avgDynCell, final String daysCell) {
        return IFERROR + "(" + avgDynCell + "*" + daysCell + ",\"\")";
    }

    public static String getCorrectedPlanFormula(final String planByDynCell, final String totalCell, final String totalAmountCell) {
        return IFERROR + "(" + planByDynCell + "/" + totalCell + "*" + totalAmountCell + ",\"\")";
    }

    public static String getDynFormula(final String firstMonthCell, final String secondMonthCell) {
        return IFERROR + "(" + firstMonthCell + "/" + secondMonthCell + "-1,\"\")";
    }
}
