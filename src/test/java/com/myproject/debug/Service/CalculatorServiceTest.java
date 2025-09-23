package com.myproject.debug.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myproject.debug.Domain.Calculator;
import com.myproject.debug.Domain.Model.CalculationModel;
import com.myproject.debug.Domain.Model.CalculationType;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

	@Mock
	private Calculator calculator;

	@Mock
	private SolutionFormatter solutionFormatter;

	private CalculatorService classUnderTest;

	@BeforeEach
	public void setUp() {
		classUnderTest = new CalculatorServiceImpl(calculator, solutionFormatter);
	}

	@Test
	public void calculate_shouldUseCalculator_forAddition() {
		// Given
		when(calculator.add(1, 2)).thenReturn(3);

		// When
		final int result = classUnderTest.calculate(new CalculationModel(CalculationType.ADDITION, 1, 2))
				.getSolution();

		// Then
		verify(calculator).add(1, 2);
		assertThat(result).isEqualTo(3);
	}

	@Test
	public void calculate_shouldUseCalculator_forSubtraction() {
		// Given
		when(calculator.sub(5, 3)).thenReturn(2);

		// When
		final int result = classUnderTest.calculate(new CalculationModel(CalculationType.SUBTRACTION, 5, 3))
				.getSolution();

		// Then
		verify(calculator).sub(5, 3);
		assertThat(result).isEqualTo(2);
	}

	@Test
	public void calculate_shouldUseCalculator_forMultiplication() {
		// Given
		when(calculator.multiply(4, 6)).thenReturn(24);

		// When
		final int result = classUnderTest.calculate(new CalculationModel(CalculationType.MULTIPLICATION, 4, 6))
				.getSolution();

		// Then
		verify(calculator).multiply(4, 6);
		assertThat(result).isEqualTo(24);
	}

	@Test
	public void calculate_shouldUseCalculator_forDivision() {
		// Given
		when(calculator.divide(8, 2)).thenReturn(4);

		// When
		final int result = classUnderTest.calculate(new CalculationModel(CalculationType.DIVISION, 8, 2))
				.getSolution();

		// Then
		verify(calculator).divide(8, 2);
		assertThat(result).isEqualTo(4);
	}

	@Test
	public void calculate_shouldThrowIllegalArgumentException_forDivisionByZero() {
		// Given
		when(calculator.divide(8, 0)).thenThrow(new ArithmeticException());

		// When
		assertThrows(IllegalArgumentException.class, () -> {
			classUnderTest.calculate(new CalculationModel(CalculationType.DIVISION, 8, 0));
		});

		// Then
		verify(calculator, times(1)).divide(8, 0);
	}

	@Test
	public void calculate_shouldFormatSolution_ForAnAddition() {
		// Given
		when(calculator.add(10000, 3000)).thenReturn(13000);
		when(solutionFormatter.format(13000)).thenReturn("13 000");

		// When
		final String formattedResult = classUnderTest
				.calculate(new CalculationModel(CalculationType.ADDITION, 10000, 3000)).getFormattedSolution();

		// Then
		assertThat(formattedResult).isEqualTo("13 000");
	}
}
