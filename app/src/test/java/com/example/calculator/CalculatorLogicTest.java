package com.example.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class CalculatorLogicTest {

    private final CalculatorLogic logic = new CalculatorLogic();

    @Test
    public void add_positiveNumbers() {
        assertEquals(7.0, logic.add(3, 4), 0.0);
    }

    @Test
    public void add_negativeNumbers() {
        assertEquals(-9.0, logic.add(-4, -5), 0.0);
    }

    @Test
    public void subtract_givesNegativeWhenSecondIsLarger() {
        assertEquals(-5.0, logic.subtract(5, 10), 0.0);
    }

    @Test
    public void multiply_byZeroIsZero() {
        assertEquals(0.0, logic.multiply(123.45, 0), 0.0);
    }

    @Test
    public void multiply_decimals() {
        assertEquals(6.25, logic.multiply(2.5, 2.5), 0.0);
    }

    @Test
    public void divide_normal() {
        assertEquals(2.5, logic.divide(5, 2), 0.0);
    }

    @Test
    public void divide_byZeroThrows() {
        assertThrows(ArithmeticException.class, () -> logic.divide(10, 0));
    }

    @Test
    public void format_removesTrailingZero() {
        assertEquals("7", logic.format(7.0));
    }

    @Test
    public void format_removesFloatingPointNoise() {
        assertEquals("0.3", logic.format(0.1 + 0.2));
    }

    @Test
    public void format_keepsDecimals() {
        assertEquals("2.5", logic.format(2.5));
    }

    @Test
    public void format_negativeResult() {
        assertEquals("-5", logic.format(-5.0));
    }

    @Test
    public void format_zero() {
        assertEquals("0", logic.format(0.0));
    }

    @Test
    public void format_largeNumberIsNotScientificNotation() {
        assertEquals("1000000000000", logic.format(1_000_000_000_000.0));
    }

    @Test
    public void format_infinityShowsError() {
        assertEquals("Error", logic.format(Double.POSITIVE_INFINITY));
    }
}
