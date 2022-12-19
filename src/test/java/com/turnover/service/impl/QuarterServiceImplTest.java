package com.turnover.service.impl;

import com.turnover.model.Month;
import com.turnover.service.MonthService;
import com.turnover.service.QuarterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.turnover.util.Constants.CURRENT;
import static com.turnover.util.Constants.CURRENT_PREV;
import static com.turnover.util.Constants.FIRST;
import static com.turnover.util.Constants.FIRST_PREV;
import static com.turnover.util.Constants.SECOND;
import static com.turnover.util.Constants.SECOND_PREV;
import static com.turnover.util.Constants.THIRD;
import static com.turnover.util.Constants.THIRD_PREV;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuarterServiceImplTest {
    private static Map<String, Month> firstMonthQuarter;
    private static Map<String, Month> secondMonthQuarter;
    private static Map<String, Month> twelfthMonthQuarter;

    @BeforeAll
    static void setUp() {
        final MonthService monthService = new MonthServiceImpl();
        final QuarterService quarterService = new QuarterServiceImpl(monthService);
        firstMonthQuarter = quarterService.createQuarter(1, 23).getMonths();
        secondMonthQuarter = quarterService.createQuarter(2, 23).getMonths();
        twelfthMonthQuarter = quarterService.createQuarter(12, 22).getMonths();
    }

    @Test
    @DisplayName(value = "Test 1-st planned month - current")
    @Order(value = 1)
    void testQuarterCreation_firstMonth_Current() {
        final Month actualMonth = firstMonthQuarter.get(CURRENT);
        final String expectedName = "Грудень 22";
        final int expectedDays = 31;
        final int expectedSaturdays = 5;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 1-st planned month - current previous")
    @Order(value = 2)
    void testQuarterCreation_firstMonth_Current_Prev() {
        final Month actualMonth = firstMonthQuarter.get(CURRENT_PREV);
        final String expectedName = "Грудень 21";
        final int expectedDays = 31;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 1-st planned month - first")
    @Order(value = 3)
    void testQuarterCreation_firstMonth_First() {
        final Month actualMonth = firstMonthQuarter.get(FIRST);
        final String expectedName = "Січень 23";
        final int expectedDays = 31;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 1-st planned month - first previous")
    @Order(value = 4)
    void testQuarterCreation_firstMonth_First_Prev() {
        final Month actualMonth = firstMonthQuarter.get(FIRST_PREV);
        final String expectedName = "Січень 22";
        final int expectedDays = 31;
        final int expectedSaturdays = 5;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 1-st planned month - second")
    @Order(value = 5)
    void testQuarterCreation_firstMonth_Second() {
        final Month actualMonth = firstMonthQuarter.get(SECOND);
        final String expectedName = "Лютий 23";
        final int expectedDays = 28;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 1-st planned month - second previous")
    @Order(value = 6)
    void testQuarterCreation_firstMonth_Second_Prev() {
        final Month actualMonth = firstMonthQuarter.get(SECOND_PREV);
        final String expectedName = "Лютий 22";
        final int expectedDays = 28;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 1-st planned month - third")
    @Order(value = 7)
    void testQuarterCreation_firstMonth_Third() {
        final Month actualMonth = firstMonthQuarter.get(THIRD);
        final String expectedName = "Березень 23";
        final int expectedDays = 31;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 1-st planned month - third previous")
    @Order(value = 8)
    void testQuarterCreation_firstMonth_Third_Prev() {
        final Month actualMonth = firstMonthQuarter.get(THIRD_PREV);
        final String expectedName = "Березень 22";
        final int expectedDays = 31;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 2-d planned month - current")
    @Order(value = 9)
    void testQuarterCreation_secondMonth_Current() {
        final Month actualMonth = secondMonthQuarter.get(CURRENT);
        final String expectedName = "Січень 23";
        final int expectedDays = 31;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 2-d planned month - current previous")
    @Order(value = 10)
    void testQuarterCreation_secondMonth_Current_Prev() {
        final Month actualMonth = secondMonthQuarter.get(CURRENT_PREV);
        final String expectedName = "Січень 22";
        final int expectedDays = 31;
        final int expectedSaturdays = 5;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 2-d planned month - first")
    @Order(value = 11)
    void testQuarterCreation_secondMonth_First() {
        final Month actualMonth = secondMonthQuarter.get(FIRST);
        final String expectedName = "Лютий 23";
        final int expectedDays = 28;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 2-d planned month - first previous")
    @Order(value = 12)
    void testQuarterCreation_secondMonth_First_Prev() {
        final Month actualMonth = secondMonthQuarter.get(FIRST_PREV);
        final String expectedName = "Лютий 22";
        final int expectedDays = 28;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 2-d planned month - second")
    @Order(value = 13)
    void testQuarterCreation_secondMonth_Second() {
        final Month actualMonth = secondMonthQuarter.get(SECOND);
        final String expectedName = "Березень 23";
        final int expectedDays = 31;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 2-d planned month - second previous")
    @Order(value = 14)
    void testQuarterCreation_secondMonth_Second_Prev() {
        final Month actualMonth = secondMonthQuarter.get(SECOND_PREV);
        final String expectedName = "Березень 22";
        final int expectedDays = 31;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 2-d planned month - third")
    @Order(value = 15)
    void testQuarterCreation_secondMonth_Third() {
        final Month actualMonth = secondMonthQuarter.get(THIRD);
        final String expectedName = "Квітень 23";
        final int expectedDays = 30;
        final int expectedSaturdays = 5;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 2-d planned month - third previous")
    @Order(value = 16)
    void testQuarterCreation_secondMonth_Third_Prev() {
        final Month actualMonth = secondMonthQuarter.get(THIRD_PREV);
        final String expectedName = "Квітень 22";
        final int expectedDays = 30;
        final int expectedSaturdays = 5;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 12-th planned month - current")
    @Order(value = 17)
    void testQuarterCreation_twelfthMonth_Current() {
        final Month actualMonth = twelfthMonthQuarter.get(CURRENT);
        final String expectedName = "Листопад 22";
        final int expectedDays = 30;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 12-th planned month - current previous")
    @Order(value = 18)
    void testQuarterCreation_twelfthMonth_Current_Prev() {
        final Month actualMonth = twelfthMonthQuarter.get(CURRENT_PREV);
        final String expectedName = "Листопад 21";
        final int expectedDays = 30;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 12-th planned month - first")
    @Order(value = 19)
    void testQuarterCreation_twelfthMonth_First() {
        final Month actualMonth = twelfthMonthQuarter.get(FIRST);
        final String expectedName = "Грудень 22";
        final int expectedDays = 31;
        final int expectedSaturdays = 5;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 12-th planned month - first previous")
    @Order(value = 20)
    void testQuarterCreation_twelfthMonth_First_Prev() {
        final Month actualMonth = twelfthMonthQuarter.get(FIRST_PREV);
        final String expectedName = "Грудень 21";
        final int expectedDays = 31;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 12-th planned month - second")
    @Order(value = 21)
    void testQuarterCreation_twelfthMonth_Second() {
        final Month actualMonth = twelfthMonthQuarter.get(SECOND);
        final String expectedName = "Січень 23";
        final int expectedDays = 31;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 12-th planned month - second previous")
    @Order(value = 22)
    void testQuarterCreation_twelfthMonth_Second_Prev() {
        final Month actualMonth = twelfthMonthQuarter.get(SECOND_PREV);
        final String expectedName = "Січень 22";
        final int expectedDays = 31;
        final int expectedSaturdays = 5;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 12-th planned month - third")
    @Order(value = 23)
    void testQuarterCreation_twelfthMonth_Third() {
        final Month actualMonth = twelfthMonthQuarter.get(THIRD);
        final String expectedName = "Лютий 23";
        final int expectedDays = 28;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

    @Test
    @DisplayName(value = "Test 12-th planned month - third previous")
    @Order(value = 24)
    void testQuarterCreation_twelfthMonth_Third_Prev() {
        final Month actualMonth = twelfthMonthQuarter.get(THIRD_PREV);
        final String expectedName = "Лютий 22";
        final int expectedDays = 28;
        final int expectedSaturdays = 4;

        assertEquals(expectedName, actualMonth.getName());
        assertEquals(expectedDays, actualMonth.getDays());
        assertEquals(expectedSaturdays, actualMonth.getSundays());
    }

}