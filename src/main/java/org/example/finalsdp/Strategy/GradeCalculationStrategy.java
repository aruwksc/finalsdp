package org.example.finalsdp.Strategy;

import java.util.List;
import java.util.Map;

public interface GradeCalculationStrategy {
    double calculateFinalGrade(Map<String, List<Double>> grades);
    String getStrategyName();
}
