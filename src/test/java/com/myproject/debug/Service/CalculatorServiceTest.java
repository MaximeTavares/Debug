package com.myproject.debug.Service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.myproject.debug.Domain.Calculator;
import com.myproject.debug.Domain.Model.CalculationModel;
import com.myproject.debug.Domain.Model.CalculationType;

public class CalculatorServiceTest {

	Calculator calculator = new Calculator();
	// Calculator IS CALLED BY CalculatorService
	CalculatorService classUnderTest = new CalculatorServiceImpl(calculator);

	@Test
	public void add_returnsTheSum_ofTwoPositiveNumbers() {
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();
		assertThat(result).isEqualTo(3);
	}

	@Test
	public void add_returnsTheSum_ofTwoNegativeNumbers() {
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, -1, 2))
				.getSolution();

		assertThat(result).isEqualTo(1);
	}

	@Test
	public void add_returnsTheSum_ofZeroAndZero() {
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, 0, 0)).getSolution();
		assertThat(result).isEqualTo(0);
	}
}
