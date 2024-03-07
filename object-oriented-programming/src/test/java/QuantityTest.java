import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.msu.vmk.Quantity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.stream.Stream;

public class QuantityTest {
    private final String defaultUnitOfMeasurement = "кг";

    /**
     * Проверка {@link Quantity#add(Quantity)} при различных единицах измерения
     * @param leftUnitOfMeasurement единицы измерения левого операнда
     * @param rightUnitOfMeasurement единицы измерения правого операнда
     */
    @ParameterizedTest
    @MethodSource("provideDifferentUnitOfMeasurement")
    public void addDifferentUnitOfMeasurementTest(String leftUnitOfMeasurement, String rightUnitOfMeasurement){
        var leftQuantity = createQuantity("1.0", leftUnitOfMeasurement);
        var rightQuantity = createQuantity("1.0", rightUnitOfMeasurement);

        Assertions.assertThrows(Exception.class, () -> leftQuantity.add(rightQuantity));
    }

    /**
     * Проверка {@link Quantity#subtract(Quantity)} при различных единицах измерения
     * @param leftUnitOfMeasurement единицы измерения левого операнда
     * @param rightUnitOfMeasurement единицы измерения правого операнда
     */
    @ParameterizedTest
    @MethodSource("provideDifferentUnitOfMeasurement")
    public void subtractDifferentUnitOfMeasurementTest(String leftUnitOfMeasurement, String rightUnitOfMeasurement){
        var leftQuantity = createQuantity("1.0", leftUnitOfMeasurement);
        var rightQuantity = createQuantity("1.0", rightUnitOfMeasurement);

        Assertions.assertThrows(Exception.class, () -> leftQuantity.subtract(rightQuantity));
    }

    /**
     * Проверка {@link Quantity#multiply(Quantity)} при различных единицах измерения
     * @param leftUnitOfMeasurement единицы измерения левого операнда
     * @param rightUnitOfMeasurement единицы измерения правого операнда
     */
    @ParameterizedTest
    @MethodSource("provideDifferentUnitOfMeasurement")
    public void multiplyDifferentUnitOfMeasurementTest(String leftUnitOfMeasurement, String rightUnitOfMeasurement){
        var leftQuantity = createQuantity("1.0", leftUnitOfMeasurement);
        var rightQuantity = createQuantity("1.0", rightUnitOfMeasurement);

        Assertions.assertThrows(Exception.class, () -> leftQuantity.multiply(rightQuantity));
    }

    /**
     * Проверка {@link Quantity#divide(Quantity)} при различных единицах измерения
     * @param leftUnitOfMeasurement единицы измерения левого операнда
     * @param rightUnitOfMeasurement единицы измерения правого операнда
     */
    @ParameterizedTest
    @MethodSource("provideDifferentUnitOfMeasurement")
    public void divideDifferentUnitOfMeasurementTest(String leftUnitOfMeasurement, String rightUnitOfMeasurement){
        var leftQuantity = createQuantity("1.0", leftUnitOfMeasurement);
        var rightQuantity = createQuantity("1.0", rightUnitOfMeasurement);

        Assertions.assertThrows(Exception.class, () -> leftQuantity.divide(rightQuantity));
    }

    /**
     * Проверка {@link Quantity#add(Quantity)} при различных единицах измерения
     * @param leftNumber левый операнд
     * @param rightNumber правый операнд
     * @param expectedNumber ожидаемая сумма
     */
    @ParameterizedTest
    @MethodSource("provideAddArguments")
    public void addTest(String leftNumber, String rightNumber, String expectedNumber) throws Exception {
        var leftQuantity = createQuantity(leftNumber);
        var rightQuantity = createQuantity(rightNumber);
        var expectedQuantity = createQuantity(expectedNumber);

        var result = leftQuantity.add(rightQuantity);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getAmount(), expectedQuantity.getAmount());
    }

    /**
     * Проверка {@link Quantity#subtract(Quantity)} при различных единицах измерения
     * @param leftNumber левый операнд
     * @param rightNumber правый операнд
     * @param expectedNumber ожидаемая разность
     */
    @ParameterizedTest
    @MethodSource("provideSubtractArguments")
    public void subtractTest(String leftNumber, String rightNumber, String expectedNumber) throws Exception {
        var leftQuantity = createQuantity(leftNumber);
        var rightQuantity = createQuantity(rightNumber);
        var expectedQuantity = createQuantity(expectedNumber);

        var result = leftQuantity.subtract(rightQuantity);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getAmount(), expectedQuantity.getAmount());
    }

    /**
     * Проверка {@link Quantity#multiply(Quantity)} при различных единицах измерения
     * @param leftNumber левый операнд
     * @param rightNumber правый операнд
     * @param expectedNumber ожидаемое произведение
     */
    @ParameterizedTest
    @MethodSource("provideMultiplyArguments")
    public void multiplyTest(String leftNumber, String rightNumber, String expectedNumber) throws Exception {
        var leftQuantity = createQuantity(leftNumber);
        var rightQuantity = createQuantity(rightNumber);
        var expectedQuantity = createQuantity(expectedNumber);

        var result = leftQuantity.multiply(rightQuantity);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getAmount(), expectedQuantity.getAmount());
    }

    /**
     * Проверка {@link Quantity#divide(Quantity)} при различных единицах измерения
     * @param leftNumber левый операнд
     * @param rightNumber правый операнд
     * @param expectedNumber ожидаемое частное
     */
    @ParameterizedTest
    @MethodSource("provideDivideArguments")
    public void divideTest(String leftNumber, String rightNumber, String expectedNumber) throws Exception {
        var leftQuantity = createQuantity(leftNumber);
        var rightQuantity = createQuantity(rightNumber);
        var expectedQuantity = createQuantity(expectedNumber);

        var result = leftQuantity.divide(rightQuantity);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getAmount(), expectedQuantity.getAmount());
    }

    /**
     * Проверка {@link Quantity#divide(int)} при различных единицах измерения
     * @param leftNumber левый операнд
     * @param rightNumber правый операнд
     * @param expectedNumbers ожидаемое частное
     */
    @ParameterizedTest
    @MethodSource("provideDivideToPartsArguments")
    public void divideToPartsTest(String leftNumber, int rightNumber, String[] expectedNumbers) throws Exception {
        var leftQuantity = createQuantity(leftNumber);
        var expected = Arrays.stream(expectedNumbers)
                .map(this::createQuantity)
                .toList();

        var result = leftQuantity.divide(rightNumber);

        Assertions.assertNotNull(result);
        for (int i = 0; i < result.length; i++) {
            Assertions.assertEquals(expected.get(i).getAmount(), result[i].getAmount());
        }
    }

    private static Stream<Arguments> provideDifferentUnitOfMeasurement() {
        return Stream.of(
                Arguments.of("кг","г"),
                Arguments.of("рубль","г"),
                Arguments.of("г","рубль"),
                Arguments.of("г","кг")
        );
    }

    private static Stream<Arguments> provideAddArguments() {
        return Stream.of(
                Arguments.of("1.0","2.0", "3.0"),
                Arguments.of("1.1","2.0", "3.1"),
                Arguments.of("1.99","2.0", "3.99"),
                Arguments.of("1.99","2.01", "4.00"),
                Arguments.of("1.99","-2.01", "-0.02")
        );
    }

    private static Stream<Arguments> provideSubtractArguments() {
        return Stream.of(
                Arguments.of("2.0","1.0", "1.0"),
                Arguments.of("1.1","2.0", "-0.9"),
                Arguments.of("1.99","2.0", "-0.01"),
                Arguments.of("1.99","2.01", "-0.02"),
                Arguments.of("1.99","-2.01", "4.00")
        );
    }

    private static Stream<Arguments> provideMultiplyArguments() {
        return Stream.of(
                Arguments.of("1.0","2.0", "2.0"),
                Arguments.of("1.1","2.0", "2.2")
        );
    }

    private static Stream<Arguments> provideDivideArguments() {
        return Stream.of(
                Arguments.of("1.0","2.0", "0.5"),
                Arguments.of("2.0","2.0", "1"),
                Arguments.of("5.0","2.0", "2.5")
        );
    }

    private static Stream<Arguments> provideDivideToPartsArguments() {
        return Stream.of(
                Arguments.of("0.1", 2, new String[]{"0.05", "0.05"}),
                Arguments.of("1.0", 2, new String[]{ "0.5", "0.5" }),
                Arguments.of("2.0", 2, new String[]{ "1.0", "1.0" }),
                Arguments.of("5.0", 2, new String[]{ "2.5", "2.5"  }),
                Arguments.of("10.0", 2, new String[]{ "5.0", "5.0" }),
                Arguments.of("10.0", 3, new String[]{ "4.0", "3.0", "3.0" }),
                Arguments.of("20.0", 6, new String[]{ "5.0", "3.0", "3.0", "3.0", "3.0", "3.0" })
        );
    }

    private Quantity createQuantity(String amount, String unitOfMeasurement){
        return new Quantity(new BigDecimal(amount, MathContext.DECIMAL32), unitOfMeasurement);
    }

    private Quantity createQuantity(String amount){
        return createQuantity(amount, defaultUnitOfMeasurement);
    }
}
