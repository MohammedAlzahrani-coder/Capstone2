package com.example.capstone2.Controller;

import com.example.capstone2.Model.User;
import com.example.capstone2.Service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

//    @GetMapping("/get")
//    public ResponseEntity<List<User>> getAllAdmins() {
//        List<User> admins = adminService.getAllAdmins();
//        return ResponseEntity.ok(admins);
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<User> addAdmin(@Valid @RequestBody User admin, Errors errors) {
//        if (errors.hasErrors()) {
//            String errorMessage = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.badRequest().body(null);
//        }
//        adminService.addAdmin(admin);
//        return ResponseEntity.ok(admin);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<User> updateAdmin(@PathVariable Long id, @Valid @RequestBody User admin, Errors errors) {
//        if (errors.hasErrors()) {
//            String errorMessage = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.badRequest().body(null);
//        }
//        adminService.updateAdmin(id, admin);
//        return ResponseEntity.ok(admin);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
//        adminService.deleteAdmin(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/get_by_id/{id}")
//    public ResponseEntity<User> getAdminById(@PathVariable Long id) {
//        User admin = adminService.getAdminById(id);
//        return ResponseEntity.ok(admin);
//    }
//


}

