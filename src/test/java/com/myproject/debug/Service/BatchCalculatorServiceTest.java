package com.myproject.debug.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myproject.debug.Domain.Model.CalculationModel;
import com.myproject.debug.Domain.Model.CalculationType;

@ExtendWith(MockitoExtension.class)
public class BatchCalculatorServiceTest {

    @Mock
    private CalculatorService calculatorService;

    private BatchCalculatorService batchCalculatorService;

    @BeforeEach
    public void setUp() {
        batchCalculatorService = new BatchCalculatorServiceImpl(calculatorService);
    }

    // ArgumentCaptor usage example
    @Test
    public void givenOperationsList_whenbatchCalculate_thenCallsServiceWithCorrectArguments()
            throws IOException, URISyntaxException {
        // GIVEN
        final Stream<String> operations =
            Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();
        final ArgumentCaptor<CalculationModel> calculationModelCaptor =
            ArgumentCaptor.forClass(CalculationModel.class);

        // WHEN
        batchCalculatorService.batchCalculate(operations);

        // THEN
        verify(calculatorService, times(4)).calculate(calculationModelCaptor.capture());
        final List<CalculationModel> calculationModels =
            calculationModelCaptor.getAllValues();
        assertThat(calculationModels)
                .extracting(CalculationModel::getLeftArgument, 
                            CalculationModel::getType,
                            CalculationModel::getRightArgument)
                .containsExactly(
                        tuple(2, CalculationType.ADDITION, 2),
                        tuple(5, CalculationType.SUBTRACTION, 4),
                        tuple(6, CalculationType.MULTIPLICATION, 8),
                        tuple(9, CalculationType.DIVISION, 3));
    }

}
