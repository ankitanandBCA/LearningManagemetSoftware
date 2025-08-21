package com.lms.example.repositry;

import com.lms.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmailAndPassword(String email,String password);
}
