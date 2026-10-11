package com.example.calculator;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Pure calculation logic, kept free of Android classes so it can be
 * unit-tested on the JVM without an emulator.
 */
public class CalculatorLogic {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * @throws ArithmeticException if the divisor is zero
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    /**
     * Formats a result for display: no trailing ".0", and no floating point
     * noise such as 0.30000000000000004 (rounded to 10 significant digits).
     */
    public String format(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "Error";
        }
        BigDecimal rounded = new BigDecimal(value).round(new MathContext(10));
        if (rounded.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }
        return rounded.stripTrailingZeros().toPlainString();
    }
}
