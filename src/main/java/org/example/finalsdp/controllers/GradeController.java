package org.example.finalsdp.controllers;

import org.example.finalsdp.observer.Observer;
import org.example.finalsdp.observer.Student;
import org.example.finalsdp.observer.Parent;
import org.example.finalsdp.observer.GradeSystemService;
import org.example.finalsdp.Strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeSystemService gradeSystemService;
    private final GradeCalculatorService gradeCalculatorService;

    @Autowired
    public GradeController(GradeSystemService gradeSystemService,
                           GradeCalculatorService gradeCalculatorService) {
        this.gradeSystemService = gradeSystemService;
        this.gradeCalculatorService = gradeCalculatorService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addGrade(@RequestBody GradeRequest request) {
        gradeSystemService.addGrade(
                request.getStudentId(),
                request.getSubject(),
                request.getGrade(),
                request.getGradeType()
        );

        Map<String, String> response = new HashMap<>();
        response.put("message", "Grade added successfully");
        response.put("studentId", request.getStudentId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Map<String, String>> subscribeObserver(@RequestBody SubscribeRequest request) {
        Observer observer;

        if ("student".equalsIgnoreCase(request.getType())) {
            observer = new Student(request.getName(), request.getId());
        } else if ("parent".equalsIgnoreCase(request.getType())) {
            observer = new Parent(request.getName(), request.getChildName());
        } else {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Invalid observer type")
            );
        }

        gradeSystemService.attach(observer);

        return ResponseEntity.ok(Map.of(
                "message", "Observer subscribed successfully",
                "type", request.getType()
        ));
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculateGrade(
            @RequestBody CalculateRequest request,
            @RequestParam(required = false, defaultValue = "comprehensive") String strategy) {

        GradeCalculationStrategy selectedStrategy;
        switch (strategy.toLowerCase()) {
            case "exam":
                selectedStrategy = new ExamOnlyStrategy();
                break;
            case "homework":
                selectedStrategy = new HomeworkIncludedStrategy();
                break;
            default:
                selectedStrategy = new ComprehensiveStrategy();
        }

        gradeCalculatorService.setStrategy(selectedStrategy);
        double finalGrade = gradeCalculatorService.calculate(request.getGrades());

        Map<String, Object> response = new HashMap<>();
        response.put("finalGrade", finalGrade);
        response.put("strategy", gradeCalculatorService.getCurrentStrategy());
        response.put("grades", request.getGrades());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Map<String, Object>>> getGrades(@PathVariable String studentId) {
        return ResponseEntity.ok(gradeSystemService.getGrades(studentId));
    }
}