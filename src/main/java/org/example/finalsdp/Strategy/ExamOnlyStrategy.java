package org.example.finalsdp.Strategy;

import org.springframework.stereotype.Component;
import java.util.*;

@Component("examOnlyStrategy")
public class ExamOnlyStrategy implements GradeCalculationStrategy {

    @Override
    public double calculateFinalGrade(Map<String, List<Double>> grades) {
        List<Double> examGrades = grades.getOrDefault("exam", new ArrayList<>());
        if (examGrades.isEmpty()) {
            return 0.0;
        }
        return examGrades.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    @Override
    public String getStrategyName() {
        return "Exam Only";
    }
}
