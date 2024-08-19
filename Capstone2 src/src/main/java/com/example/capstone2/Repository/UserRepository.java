package com.example.capstone2.Repository;


import com.example.capstone2.Model.Item;
import com.example.capstone2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long id);

    User findByEmail(String email);

    User findByUsername(String username);


    List<User> findByRole(String role);


}
