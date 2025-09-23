package com.myproject.debug.Service;

import com.myproject.debug.Domain.Calculator;
import com.myproject.debug.Domain.Model.CalculationModel;
import com.myproject.debug.Domain.Model.CalculationType;

public class CalculatorServiceImpl implements CalculatorService {

	private final Calculator calculator;

	public CalculatorServiceImpl(Calculator calculator) {
		this.calculator = calculator;
	}

	@Override
	public CalculationModel calculate(CalculationModel calculationModel) {
		final CalculationType type = calculationModel.getType();

		Integer response = null;
		switch (type) {
		case ADDITION:
			response = calculator.add(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case SUBTRACTION:
			response = calculator.sub(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case MULTIPLICATION:
			response = calculator.multiply(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case DIVISION:
			response = calculator.divide(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		default:
			throw new UnsupportedOperationException("Unsupported calculations");
		}

		calculationModel.setSolution(response);
		return calculationModel;
	}

}
