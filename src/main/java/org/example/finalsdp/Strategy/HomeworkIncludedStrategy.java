package org.example.finalsdp.Strategy;

import org.springframework.stereotype.Component;
import java.util.*;

@Component("homeworkIncludedStrategy")
public class HomeworkIncludedStrategy implements GradeCalculationStrategy {

    @Override
    public double calculateFinalGrade(Map<String, List<Double>> grades) {
        List<Double> examGrades = grades.getOrDefault("exam", new ArrayList<>());
        List<Double> homeworkGrades = grades.getOrDefault("homework", new ArrayList<>());

        double examAvg = examGrades.isEmpty() ? 0.0 :
                examGrades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double hwAvg = homeworkGrades.isEmpty() ? 0.0 :
                homeworkGrades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        return examAvg * 0.7 + hwAvg * 0.3;
    }

    @Override
    public String getStrategyName() {
        return "Exam + Homework (70/30)";
    }
}

