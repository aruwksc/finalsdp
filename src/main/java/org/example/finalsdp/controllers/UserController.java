package org.example.finalsdp.controllers;

import org.example.finalsdp.Decorator.User;
import org.example.finalsdp.Decorator.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Data;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User user = userService.createUser(request.getName(), request.getUserId());
        if (request.getRoles() != null) {
            for (String role : request.getRoles()) {
                switch (role.toLowerCase()) {
                    case "teacher":
                        user = userService.addTeacherRole(user);
                        break;
                    case "admin":
                        user = userService.addAdminRole(user);
                        break;
                    case "curator":
                        user = userService.addGroupCuratorRole(user, request.getGroupName());
                        break;
                }
            }
        }

        return ResponseEntity.ok(new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getDescription(),
                user.getPermissions()
        ));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getDescription(),
                user.getPermissions()
        ));
    }
}

