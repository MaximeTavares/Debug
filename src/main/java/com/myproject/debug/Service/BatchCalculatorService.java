package com.myproject.debug.Service;

import java.util.List;
import java.util.stream.Stream;

import com.myproject.debug.Domain.Model.CalculationModel;

public interface BatchCalculatorService {
	public List<CalculationModel> batchCalculate(Stream<String> operations);
}
