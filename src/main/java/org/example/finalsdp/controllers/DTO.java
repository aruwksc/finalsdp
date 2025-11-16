package org.example.finalsdp.controllers;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
class GradeRequest {
    private String studentId;
    private String subject;
    private double grade;
    private String gradeType;
}

@Data
class SubscribeRequest {
    private String type;
    private String name;
    private String id;
    private String childName;
}

@Data
class CalculateRequest {
    private Map<String, List<Double>> grades;
}

@Data
class UserRequest {
    private String name;
    private String userId;
    private List<String> roles;
    private String groupName;
}

@Data
@lombok.AllArgsConstructor
class UserResponse {
    private String userId;
    private String name;
    private String description;
    private List<String> permissions;
}

