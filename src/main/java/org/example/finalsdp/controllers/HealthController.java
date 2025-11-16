package org.example.finalsdp.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "School Management System API is running");
        response.put("version", "1.0.0");
        response.put("patterns", List.of(
                "Observer", "Decorator", "Strategy",
                "Factory", "Abstract Factory", "Bridge"
        ));
        response.put("timestamp", new Date().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/endpoints")
    public ResponseEntity<Map<String, List<String>>> listEndpoints() {
        Map<String, List<String>> endpoints = new HashMap<>();

        endpoints.put("Observer", List.of(
                "POST /api/grades/subscribe",
                "POST /api/grades/add",
                "GET /api/grades/{studentId}"
        ));

        endpoints.put("Decorator", List.of(
                "POST /api/users/create",
                "GET /api/users/{userId}"
        ));

        endpoints.put("Strategy", List.of(
                "POST /api/grades/calculate?strategy={type}"
        ));

        endpoints.put("Factory", List.of(
                "POST /api/factory/create"
        ));

        endpoints.put("Abstract Factory", List.of(
                "POST /api/abstract-factory/create"
        ));

        endpoints.put("Bridge", List.of(
                "POST /api/bridge/execute"
        ));

        return ResponseEntity.ok(endpoints);
    }
}

