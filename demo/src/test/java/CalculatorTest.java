import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import debug.Calculator;

public class CalculatorTest {

    @Test
    void testAddTwoPositiveNumbers() {
        //Arrange
        int a = 2;
        int b = 3;
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(a, b);

        //Assert
        assertEquals(5, result);
    }

    @Test
    void testMultiplyTwoPositiveNumbers() {
        //Arrange
        int a = 4;
        int b = 5;
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.multiply(a, b);

        //Assert
        assertEquals(20, result);
    }

}
