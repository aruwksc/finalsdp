package org.example.finalsdp.observer;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class GradeSystemService implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private final Map<String, List<Map<String, Object>>> grades = new HashMap<>();

    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            log.info("Observer attached: {}", observer.getClass().getSimpleName());
        }
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
        log.info("Observer detached: {}", observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers(String message, Map<String, Object> data) {
        for (Observer observer : observers) {
            observer.update(message, data);
        }
    }

    public void addGrade(String studentId, String subject, double grade, String gradeType) {
        grades.computeIfAbsent(studentId, k -> new ArrayList<>());

        Map<String, Object> gradeEntry = new HashMap<>();
        gradeEntry.put("subject", subject);
        gradeEntry.put("grade", grade);
        gradeEntry.put("type", gradeType);
        gradeEntry.put("date", LocalDateTime.now());

        grades.get(studentId).add(gradeEntry);

        String message = String.format("New grade '%s': %.1f (%s)",
                subject, grade, gradeType);
        notifyObservers(message, gradeEntry);

        log.info("Grade added for student {}: {} - {}", studentId, subject, grade);
    }

    public void generateReport(String studentId) {
        if (grades.containsKey(studentId)) {
            String message = "Generated a performance report";
            Map<String, Object> data = new HashMap<>();
            data.put("total_grades", grades.get(studentId).size());
            data.put("studentId", studentId);
            notifyObservers(message, data);

            log.info("Report generated for student: {}", studentId);
        }
    }

    public List<Map<String, Object>> getGrades(String studentId) {
        return grades.getOrDefault(studentId, new ArrayList<>());
    }
}

