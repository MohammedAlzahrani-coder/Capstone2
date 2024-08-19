package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse("The user has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        userService.updateUser(id, user);
        return ResponseEntity.ok(new ApiResponse("The user has been added successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("The user has been added successfully"));
    }

    @GetMapping("/check/username&password/{username}/{password}")
    public ResponseEntity checkUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        return ResponseEntity.ok(userService.checkUserNameAndPassword(username, password));
    }


    @GetMapping("/get_by_email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

//    @GetMapping("/get_by_role/{role}")
//    public ResponseEntity getUserByRole(@PathVariable String role) {
//        return ResponseEntity.ok(userService.findUsersByRole(role));
//    }

}
