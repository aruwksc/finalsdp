package org.example.finalsdp.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/bridge")
@CrossOrigin(origins = "*")
public class BridgeController {

    @PostMapping("/execute")
    public ResponseEntity<Map<String, Object>> executeAction(@RequestBody BridgeRequest request) {
        Map<String, Object> response = new HashMap<>();

        String userType = request.getUserType();
        String action = request.getAction();
        String targetId = request.getTargetId();

        Map<String, Object> executionResult = new HashMap<>();
        boolean hasPermission = checkPermission(userType, action);

        if (hasPermission) {
            executionResult.put("status", "Success");
            executionResult.put("message", executeActionLogic(userType, action, targetId));
            executionResult.put("performer", userType);
            executionResult.put("action", action);
            executionResult.put("target", targetId);
        } else {
            executionResult.put("status", "Denied");
            executionResult.put("message", userType + " does not have permission to " + action);
        }

        response.put("success", hasPermission);
        response.put("result", executionResult);
        response.put("timestamp", new Date().toString());

        return ResponseEntity.ok(response);
    }

    private boolean checkPermission(String userType, String action) {
        Map<String, List<String>> permissions = new HashMap<>();
        permissions.put("student", List.of("viewGrades"));
        permissions.put("teacher", List.of("viewGrades", "editGrades", "generateReport"));
        permissions.put("admin", List.of("viewGrades", "editGrades", "generateReport", "manageUsers"));

        return permissions.getOrDefault(userType, List.of()).contains(action);
    }

    private String executeActionLogic(String userType, String action, String targetId) {
        switch (action) {
            case "viewGrades":
                return userType + " viewed grades for " + targetId;
            case "editGrades":
                return userType + " edited grades for " + targetId;
            case "generateReport":
                return userType + " generated report for " + targetId;
            case "manageUsers":
                return userType + " managed user " + targetId;
            default:
                return "Action executed";
        }
    }

    public static class BridgeRequest {
        private String userType;
        private String action;
        private String targetId;

        public String getUserType() { return userType; }
        public void setUserType(String userType) { this.userType = userType; }
        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }
        public String getTargetId() { return targetId; }
        public void setTargetId(String targetId) { this.targetId = targetId; }
    }
}

