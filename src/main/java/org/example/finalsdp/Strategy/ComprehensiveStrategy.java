package org.example.finalsdp.Strategy;

import org.springframework.stereotype.Component;
import java.util.*;

@Component("comprehensiveStrategy")
public class ComprehensiveStrategy implements GradeCalculationStrategy {

    @Override
    public double calculateFinalGrade(Map<String, List<Double>> grades) {
        List<Double> examGrades = grades.getOrDefault("exam", new ArrayList<>());
        List<Double> homeworkGrades = grades.getOrDefault("homework", new ArrayList<>());
        List<Double> projectGrades = grades.getOrDefault("project", new ArrayList<>());

        double examAvg = examGrades.isEmpty() ? 0.0 :
                examGrades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double hwAvg = homeworkGrades.isEmpty() ? 0.0 :
                homeworkGrades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double projectAvg = projectGrades.isEmpty() ? 0.0 :
                projectGrades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        return examAvg * 0.5 + hwAvg * 0.25 + projectAvg * 0.25;
    }

    @Override
    public String getStrategyName() {
        return "Complex Grade (Exam 50%, Homework 25%, project 25%)";
    }
}

