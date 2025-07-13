package es.mbl_cu.mpa.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TimeFormatterServiceTest {

    private TimeFormatterService timeFormatterService;

    @BeforeEach
    void setUp() {
        timeFormatterService = new TimeFormatterService();
    }

    @Test
    void shouldReturnNullWhenInputIsNull() {
        var actual = timeFormatterService.formatDuration(null);
        assertNull(actual);
    }

    @Test
    void shouldFormatSecondsUnderOneMinute() {
        assertEquals("05", timeFormatterService.formatDuration(5));
        assertEquals("45", timeFormatterService.formatDuration(45));
        assertEquals("59", timeFormatterService.formatDuration(59));
    }

    @Test
    void shouldFormatMinutesAndSecondsUnderOneHour() {
        assertEquals("01:05", timeFormatterService.formatDuration(65));
        assertEquals("10:00", timeFormatterService.formatDuration(600));
        assertEquals("59:59", timeFormatterService.formatDuration(3599));
    }

    @Test
    void shouldFormatHoursMinutesAndSeconds() {
        assertEquals("01:00:00", timeFormatterService.formatDuration(3600));
        assertEquals("01:01:05", timeFormatterService.formatDuration(3665));
        assertEquals("12:34:56", timeFormatterService.formatDuration(45296));
    }

}
