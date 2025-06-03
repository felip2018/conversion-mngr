package com.miguelfelipedev.conversionmngr.utils;

import com.miguelfelipedev.conversionmngr.exception.UnhandledRateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UtilitiesTest {

    @InjectMocks
    private Utilities utilities;

    private Map<String, Double> rates;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rates = MockedData.getRates();
        ReflectionTestUtils.setField(utilities, "apiKey", "dummyApiKey");
    }

    @Test
    public void testSearchRate() throws UnhandledRateException {
        assertEquals(4717.717392, utilities.searchRate(rates, "COP"));
        assertEquals(1.0, utilities.searchRate(rates, "EUR"));
        assertEquals(1.141989, utilities.searchRate(rates, "USD"));
    }

    @Test
    public void testSearchRateException() throws UnhandledRateException {
        assertThrows(UnhandledRateException.class, () -> {
           utilities.searchRate(rates, "USDT");
        });
    }

    @Test
    public void testValidateApiKey() {
        assertTrue(utilities.validateApiKey("dummyApiKey"));
    }

}
