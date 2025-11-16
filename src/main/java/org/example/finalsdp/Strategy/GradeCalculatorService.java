package org.example.finalsdp.Strategy;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GradeCalculatorService {
    private GradeCalculationStrategy strategy;

    public GradeCalculatorService() {
        this.strategy = new ExamOnlyStrategy(); // Default strategy
    }

    public void setStrategy(GradeCalculationStrategy strategy) {
        this.strategy = strategy;
        log.info("Strategy changed to: {}", strategy.getStrategyName());
    }

    public double calculate(Map<String, List<Double>> grades) {
        double result = strategy.calculateFinalGrade(grades);
        log.info("Grade calculated using {}: {}", strategy.getStrategyName(), result);
        return result;
    }

    public String getCurrentStrategy() {
        return strategy.getStrategyName();
    }
}

