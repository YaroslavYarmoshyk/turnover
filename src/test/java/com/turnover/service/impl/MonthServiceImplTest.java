package com.turnover.service.impl;

import com.turnover.service.MonthService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MonthServiceImplTest {
    private static MonthService monthService;

    @BeforeAll
    static void setUp() {
        monthService = new MonthServiceImpl();
    }

    @Test
    void testMonthCreation_Name() {
        final String expected = "Грудень 22";
        final String actual = monthService.createMonth(12, 22).getName();
        assertEquals(expected, actual);
    }

    @Test
    void testMonthCreation_Days() {
        final int expected = 31;
        final int actual = monthService.createMonth(12, 22).getDays();
        assertEquals(expected, actual);
    }

    @Test
    void testMonthCreation_Saturday() {
        final int expected = 5;
        final int actual = monthService.createMonth(12, 22).getSaturdays();
        assertEquals(expected, actual);
    }
}