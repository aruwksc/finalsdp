package org.example.finalsdp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalsdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalsdpApplication.class, args);
        System.out.println("\n==============================================");
        System.out.println("🎓 School Management System Started!");
        System.out.println("==============================================");
        System.out.println("📡 API: http://localhost:8080/api");
        System.out.println("🌐 UI:  http://localhost:8080/index.html");
        System.out.println("💚 Health: http://localhost:8080/api/health");
        System.out.println("📋 Endpoints: http://localhost:8080/api/endpoints");
        System.out.println("==============================================\n");
    }
}