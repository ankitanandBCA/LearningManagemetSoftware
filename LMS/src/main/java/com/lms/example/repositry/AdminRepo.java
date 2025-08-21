package com.lms.example.repositry;

import com.lms.example.entity.Admin;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
@Registered
public interface AdminRepo extends JpaRepository<Admin,Integer> {
}
