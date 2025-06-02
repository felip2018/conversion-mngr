package com.miguelfelipedev.conversionmngr.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstantsTests {

    @Test
    public void testConstantsValues() {
        assertEquals("error", Constants.ERROR);
        assertEquals("message", Constants.MESSAGE);
        assertEquals("currentDate", Constants.CURRENT_DATE);
        assertEquals("x-api-key", Constants.X_API_KEY);
    }

    @Test
    public void testConstantsClassConstructor() {
        assertThrows(UnsupportedOperationException.class, Constants::new);
    }

}
