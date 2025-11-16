package org.example.finalsdp.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/abstract-factory")
@CrossOrigin(origins = "*")
public class AbstractFactoryController {

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createDepartmentFamily(@RequestBody DepartmentRequest request) {
        Map<String, Object> response = new HashMap<>();

        String deptType = request.getDepartmentType();
        Map<String, Object> department = new HashMap<>();

        if ("academic".equals(deptType)) {
            department.put("type", "Academic Department");
            department.put("head", createStaffMember("Head of Department", "Academic"));
            department.put("teachers", List.of(
                    createStaffMember("Senior Teacher", "Academic"),
                    createStaffMember("Junior Teacher", "Academic")
            ));
            department.put("resources", List.of("Library", "Lab", "Classroom"));
        } else if ("administrative".equals(deptType)) {
            department.put("type", "Administrative Department");
            department.put("head", createStaffMember("Administrator", "Administrative"));
            department.put("staff", List.of(
                    createStaffMember("Secretary", "Administrative"),
                    createStaffMember("Clerk", "Administrative")
            ));
            department.put("resources", List.of("Office", "Filing Room", "Meeting Room"));
        }

        department.put("name", request.getName());
        department.put("id", request.getId());

        response.put("success", true);
        response.put("message", "Department family created via Abstract Factory Pattern");
        response.put("department", department);
        response.put("timestamp", new Date().toString());

        return ResponseEntity.ok(response);
    }

    private Map<String, String> createStaffMember(String role, String type) {
        Map<String, String> staff = new HashMap<>();
        staff.put("role", role);
        staff.put("type", type);
        staff.put("id", "SM-" + UUID.randomUUID().toString().substring(0, 8));
        return staff;
    }

    public static class DepartmentRequest {
        private String departmentType;
        private String name;
        private String id;

        public String getDepartmentType() { return departmentType; }
        public void setDepartmentType(String departmentType) { this.departmentType = departmentType; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }
}

