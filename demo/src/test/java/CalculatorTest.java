import static org.assertj.core.api.Assertions.assertThat;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import debug.Calculator;

@ExtendWith(LoggingExtension.class)
public class CalculatorTest {

    private Calculator calculatorUnderTest;

    private static Instant startedAt;

    private Logger logger;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @BeforeEach
    public void initCalculator() {
        logger.info("BeforeEach - initCalculator");
        calculatorUnderTest = new Calculator();
    }

    @AfterEach
    public void clearCalculator() {
        logger.info("AfterEach - clearCalculator");
        calculatorUnderTest = null;
    }

    @BeforeAll
    public static void initStartingTime() {
        System.out.println("BeforeAll - initStartingTime");
        startedAt = Instant.now();
    }

    @AfterAll
    public static void showTestDuration() {
        System.out.println("AfterAll - ShowTestDuration");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des test : {0} ms", duration));
    }

    @Test
    @Tag("BasicsOperations")
    void testAddTwoPositiveNumbers() {
        // Arrange
        int a = 2;
        int b = 3;

        // Act
        int result = calculatorUnderTest.add(a, b);

        // Assert
        assertThat(result).isEqualTo(5);
    }

    @Test
    @Tag("BasicsOperations")
    void testMultiplyTwoPositiveNumbers() {
        // Arrange
        int a = 4;
        int b = 5;

        // Act
        int result = calculatorUnderTest.multiply(a, b);

        // Assert
        assertThat(result).isEqualTo(20);
    }

    @Test
    @Tag("BasicsOperations")
    void testSubtractTwoPositiveNumbers() {
        // Arrange
        int a = 10;
        int b = 4;

        // Act
        int result = calculatorUnderTest.subtract(a, b);

        // Assert
        assertThat(result).isEqualTo(6);
    }

    @Test
    @Tag("BasicsOperations")
    void testDivideTwoPositiveNumbers() {
        // Arrange
        int a = 20;
        int b = 4;

        // Act
        int result = calculatorUnderTest.divide(a, b);

        // Assert
        assertThat(result).isEqualTo(5);
    }

    @Test
    @Tag("BasicsOperations")
    void testDivideByZeroShouldThrowException() {
        // Arrange
        int a = 10;
        int b = 0;

        // Act & Assert
        try {
            calculatorUnderTest.divide(a, b);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Division by zero is not allowed.");
        }
    }

    @ParameterizedTest(name = "{0} * 0 must be 0")
    @ValueSource(ints = { -1, 0, 1, 42, 100 })
    public void multiplyByZeroShouldReturnZero(int arg) {
        // Act
        int result = calculatorUnderTest.multiply(arg, 0);

        // Assert
        assertThat(result).isZero();
    }

    @ParameterizedTest(name = "{0} + {1} must be {2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "42, 58, 100",
            "-1, 1, 0",
            "-5, -5, -10"
    })
    public void AddShouldReturnTheSumOfTwoValue(int arg1, int arg2, int expectedResult) {
        // Act
        int result = calculatorUnderTest.add(arg1, arg2);

        // Assert
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldReturnTheListOfDigits_ofPositiveNumber() {
        // Given
        int number = 12345;

        // When
        Set<Integer> digits = calculatorUnderTest.digitsSet(number);

        // Then
        assertThat(digits).containsExactlyInAnyOrder(1, 2, 3, 4, 5);
    }

    @Test
    public void shouldReturnTheListOfDigits_ofNegativeNumber() {
        // Given
        int number = -12345;

        // When
        Set<Integer> digits = calculatorUnderTest.digitsSet(number);

        // Then
        assertThat(digits).containsExactlyInAnyOrder(1, 2, 3, 4, 5);
    }

}
