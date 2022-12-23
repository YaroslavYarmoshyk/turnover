package com.turnover.util;

import org.apache.poi.ss.util.CellReference;

public final class FormulasUtils {
    public static final String IF = "IF";
    public static final String IFERROR = "IFERROR";

    public static String getRange(final int startRow, final int startColumn, final int endRow, final int endColumn) {
        final String from = new CellReference(startRow + 1, startColumn + 1).formatAsR1C1String();
        final String to = new CellReference(endRow + 1, endColumn + 1).formatAsR1C1String();
        return from + ":" + to;
    }

    public static String getRange(final int startRow, final int startColumn) {
        return new CellReference(startRow + 1, startColumn + 1).formatAsR1C1String();
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
}
