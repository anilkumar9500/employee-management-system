package com.vakinfo.employee.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "https://employee-management-system-frontend-production-c026.up.railway.app"
        }
)
public class AuthController {

    private final AdminService adminService;

    @GetMapping("/hash")
    public String generateHash() {
        return new BCryptPasswordEncoder().encode("admin123");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        boolean valid = adminService.login(username, password);

        if (valid) {
            return ResponseEntity.ok(
                    Map.of(
                            "message", "Login successful",
                            "username", username
                    )
            );
        }

        return ResponseEntity
                .status(401)
                .body(
                        Map.of(
                                "message", "Invalid username or password"
                        )
                );
    }
}