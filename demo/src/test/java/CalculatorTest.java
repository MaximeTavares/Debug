import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import debug.Calculator;

public class CalculatorTest {

    private Calculator calculatorUnderTest;

    private static Instant startedAt;

    @BeforeEach
    public void initCalculator() {
        System.out.println("BeforeEach - initCalculator");
        calculatorUnderTest = new Calculator();
    }

    @AfterEach
    public void clearCalculator() {
        System.out.println("AfterEach - clearCalculator");
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
    void testAddTwoPositiveNumbers() {
        // Arrange
        int a = 2;
        int b = 3;

        // Act
        int result = calculatorUnderTest.add(a, b);

        // Assert
        assertEquals(5, result);
    }

    @Test
    void testMultiplyTwoPositiveNumbers() {
        // Arrange
        int a = 4;
        int b = 5;

        // Act
        int result = calculatorUnderTest.multiply(a, b);

        // Assert
        assertEquals(20, result);
    }

}
