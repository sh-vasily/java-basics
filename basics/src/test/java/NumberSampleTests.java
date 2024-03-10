import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.msu.vmk.NumberSample;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NumberSampleTests {
    @ParameterizedTest
    @MethodSource("provideParameters")
    public void splitTest(BigDecimal amount, int n, BigDecimal[] expected){
        var splitted = NumberSample.split(amount, n);
        assertArrayEquals(expected, splitted);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(new BigDecimal("0.1"), 2, new BigDecimal[]{new BigDecimal("0.05"), new BigDecimal("0.05")}),
                Arguments.of(new BigDecimal("1.0"), 2, new BigDecimal[]{new BigDecimal("0.5"), new BigDecimal("0.5")}),
                Arguments.of(new BigDecimal("2.0"), 2, new BigDecimal[]{new BigDecimal("1.0"), new BigDecimal("1.0")}),
                Arguments.of(new BigDecimal("5.0"), 2, new BigDecimal[]{new BigDecimal("2.5"), new BigDecimal("2.5")}),
                Arguments.of(new BigDecimal("10.0"), 2, new BigDecimal[]{new BigDecimal("5.0"), new BigDecimal("5.0")}),
                Arguments.of(new BigDecimal("20.0"), 3, new BigDecimal[]{new BigDecimal("8.0"), new BigDecimal("6.0"), new BigDecimal("6.0")})
        );
    }
}

