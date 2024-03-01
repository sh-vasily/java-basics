package ru.msu.vmk;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @ParameterizedTest
    @MethodSource("getQuantityAmount")
    void getAmount(Quantity quantity, double expected) {
        assertEquals(expected, quantity.getAmount().doubleValue());
    }

    @ParameterizedTest
    @MethodSource("getQuantityCurrency")
    void getCurrency(Quantity quantity, String expected) {
        assertEquals(expected, quantity.getCurrency());
    }

    @ParameterizedTest
    @MethodSource("getQuantityAdd")
    void add(Quantity quantity1, Quantity quantity2, Quantity expected) {
        assertEquals(expected, quantity1.add(quantity2));
    }

    @ParameterizedTest
    @MethodSource("getQuantitySubtract")
    void subtract(Quantity quantity1, Quantity quantity2, Quantity expected) {
        assertEquals(expected, quantity1.subtract(quantity2));
    }

    @ParameterizedTest
    @MethodSource("getQuantityMultiply")
    void multiply(Quantity quantity1, Quantity quantity2, Quantity expected) {
        assertEquals(expected, quantity1.multiply(quantity2));
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void divide(Quantity quantity, int n, BigDecimal[] expected) {
        assertArrayEquals(expected, quantity.divide(n));
    }

    @ParameterizedTest
    @MethodSource("getQuantityToString")
    void testToString(Quantity quantity, String expected) {
        assertEquals(expected, quantity.toString());
    }

    @ParameterizedTest
    @MethodSource("getQuantityEquals")
    void testEquals(Quantity quantity1, Quantity quantity2, boolean expected) {
        assertEquals(expected, quantity1.equals(quantity2));
    }

    @ParameterizedTest
    @MethodSource("getQuantityHashCode")
    void testHashCode(Quantity quantity1, Quantity quantity2, boolean expected) {
        assertEquals(expected, quantity1.hashCode() == quantity2.hashCode());
    }

    @ParameterizedTest
    @MethodSource("getQuantityErrorCurrency")
    void testHashCode(Quantity quantity1, Quantity quantity2, String expected) {
        String message = "";
        try {
            quantity1.add(quantity2);
        } catch (Exception ex){
            message = ex.getMessage();
        }
        assertEquals(message, expected);
    }

    @Test
    void testConstructorCurrencyNull() {
        String message = "";
        try {
            Quantity quantity = new Quantity("123", "");
        } catch (Exception ex){
            message = ex.getMessage();
        }
        assertEquals(message, "currency cannot be empty");
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(new Quantity("0.1", "USD"), 2, new BigDecimal[]{new BigDecimal("0.05"), new BigDecimal("0.05")}),
                Arguments.of(new Quantity("1.0", "USD"), 2, new BigDecimal[]{new BigDecimal("0.5"), new BigDecimal("0.5")}),
                Arguments.of(new Quantity("2.0", "USD"), 2, new BigDecimal[]{new BigDecimal("1.0"), new BigDecimal("1.0")}),
                Arguments.of(new Quantity("5.0", "USD"), 2, new BigDecimal[]{new BigDecimal("2.5"), new BigDecimal("2.5")}),
                Arguments.of(new Quantity("10.0", "USD"), 2, new BigDecimal[]{new BigDecimal("5.0"), new BigDecimal("5.0")})
        );
    }

    private static Stream<Arguments> getQuantityAmount() {
        return Stream.of(
                Arguments.of(new Quantity("1000", "USD"), 1000.0),
                Arguments.of(new Quantity("2000", "USD"), 2000.0),
                Arguments.of(new Quantity("3000", "RUB"), 3000.0)
        );
    }

    private static Stream<Arguments> getQuantityCurrency() {
        return Stream.of(
                Arguments.of(new Quantity("1000", "USD"), "USD"),
                Arguments.of(new Quantity("2000", "USD"), "USD"),
                Arguments.of(new Quantity("3000", "RUB"), "RUB")
        );
    }

    private static Stream<Arguments> getQuantityToString() {
        return Stream.of(
                Arguments.of(new Quantity("1000", "USD"), "1000.00 USD"),
                Arguments.of(new Quantity("2000", "USD"), "2000.00 USD"),
                Arguments.of(new Quantity("3000", "RUB"), "3000.00 RUB")
        );
    }

    private static Stream<Arguments> getQuantityAdd() {
        return Stream.of(
                Arguments.of(new Quantity("1000", "USD"), new Quantity("1000", "USD"),  new Quantity("2000", "USD")),
                Arguments.of(new Quantity("2000", "USD"), new Quantity("2000", "USD"),  new Quantity("4000", "USD")),
                Arguments.of(new Quantity("3000", "RUB"), new Quantity("1000", "RUB"),  new Quantity("4000", "RUB"))
        );
    }

    private static Stream<Arguments> getQuantitySubtract() {
        return Stream.of(
                Arguments.of(new Quantity("1000", "USD"), new Quantity("1000", "USD"),  new Quantity("0", "USD")),
                Arguments.of(new Quantity("2000", "USD"), new Quantity("2000", "USD"),  new Quantity("0", "USD")),
                Arguments.of(new Quantity("3000", "RUB"), new Quantity("1000", "RUB"),  new Quantity("2000", "RUB"))
        );
    }

    private static Stream<Arguments> getQuantityMultiply() {
        return Stream.of(
                Arguments.of(new Quantity("1000", "USD"), new Quantity("1000", "USD"),  new Quantity("1000000", "USD")),
                Arguments.of(new Quantity("2000", "USD"), new Quantity("2000", "USD"),  new Quantity("4000000", "USD")),
                Arguments.of(new Quantity("3000", "RUB"), new Quantity("1000", "RUB"),  new Quantity("3000000", "RUB"))
        );
    }

    private static Stream<Arguments> getQuantityEquals() {
        return Stream.of(
                Arguments.of(new Quantity("1000", "USD"), new Quantity("1000", "USD"),  true),
                Arguments.of(new Quantity("2000", "USD"), new Quantity("2000", "USD"),  true),
                Arguments.of(new Quantity("3000", "RUB"), new Quantity("1000", "RUB"),  false)
        );
    }

    private static Stream<Arguments> getQuantityHashCode() {
        return Stream.of(
                Arguments.of(new Quantity("1000", "USD"), new Quantity("1000", "USD"),  true),
                Arguments.of(new Quantity("2000", "USD"), new Quantity("2000", "USD"),  true),
                Arguments.of(new Quantity("3000", "RUB"), new Quantity("1000", "RUB"),  false)
        );
    }

    private static Stream<Arguments> getQuantityErrorCurrency() {
        return Stream.of(
                Arguments.of(new Quantity("1000", "USD"), new Quantity("1000", "RUB"),  "Валюты не совпадают: USD RUB")
        );
    }
}