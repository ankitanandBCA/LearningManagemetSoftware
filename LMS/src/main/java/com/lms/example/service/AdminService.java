package com.lms.example.service;

import com.lms.example.entity.Admin;
import com.lms.example.repositry.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

@Autowired
    AdminRepo adminRepo;

public Admin addadmin(Admin admin)
{
    return adminRepo.save(admin);
}

public List<Admin> getadmin()
{
    return adminRepo.findAll();
}

}
