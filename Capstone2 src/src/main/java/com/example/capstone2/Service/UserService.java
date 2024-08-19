package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    public User findUserById(Long id){
        User user = userRepository.findByUserId(id);
        if (user == null) {
            throw new ApiException("The user is not found: " + id);
        }
        return user;
    }
    private final UserRepository userRepository;


    public List<User> findAllUsers() {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {

            throw new ApiException("No users found");

        }

        return userRepository.findAll();

    }

    public void addUser(User user) {

        userRepository.save(user);

    }

    public void updateUser(Long id, User user) {

        User u = userRepository.findById(id).orElse(null);

        if (u == null) {

            throw new ApiException("The user is not found");

        }

        u.setName(user.getName());

        u.setUsername(user.getUsername());

        u.setPassword(user.getPassword());

        u.setEmail(user.getEmail());

        u.setRole(user.getRole());

        u.setAge(user.getAge());

        userRepository.save(u);

    }

    public void deleteUser(Long id) {
        User u = userRepository.findById(id).orElse(null);
        if (u == null) {
            throw new ApiException("The user is not found");
        }
        userRepository.delete(u);
    }


    public String checkUserNameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ApiException("The user is not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new ApiException("The password is incorrect");
        }
        return "The User credentials are correct";
    }


    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ApiException("The user is not found");
        }
        return user;
    }


    public List<User> findUsersByRole(String role) {
        List<User> users = userRepository.findByRole(role);
        if (users.isEmpty()) {
            throw new ApiException("No users found");
        }
        return users;
    }


}