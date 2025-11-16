package org.example.finalsdp.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/factory")
@CrossOrigin(origins = "*")
public class FactoryController {

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createFromFactory(@RequestBody FactoryRequest request) {
        Map<String, Object> response = new HashMap<>();

        String objectType = request.getType();
        Map<String, Object> createdObject = new HashMap<>();

        switch (objectType) {
            case "student":
                createdObject.put("type", "Student");
                createdObject.put("role", "Learner");
                createdObject.put("permissions", List.of("view_grades", "submit_homework"));
                break;
            case "teacher":
                createdObject.put("type", "Teacher");
                createdObject.put("role", "Educator");
                createdObject.put("permissions", List.of("create_grades", "view_all_students"));
                break;
            case "staff":
                createdObject.put("type", "Staff");
                createdObject.put("role", "Administrative");
                createdObject.put("permissions", List.of("manage_records", "view_reports"));
                break;
            default:
                createdObject.put("type", "Unknown");
        }

        createdObject.put("name", request.getName());
        createdObject.put("id", request.getId());

        response.put("success", true);
        response.put("message", "Object created successfully via Factory Pattern");
        response.put("object", createdObject);
        response.put("timestamp", new Date().toString());

        return ResponseEntity.ok(response);
    }

    public static class FactoryRequest {
        private String type;
        private String name;
        private String id;

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }
}

