package com.lms.example.controller;

import com.lms.example.entity.Admin;
import com.lms.example.repositry.AdminRepo;
import com.lms.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminRepo adminRepo;

    @PostMapping("/AddAdmin")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public Admin addadmins(@RequestBody Admin admin)
    {
        return adminService.addadmin(admin);
    }

    @GetMapping("/getAdmin")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public List<Admin> getadmindata()
    {
        return adminService.getadmin();
    }


    @DeleteMapping("deleteAdmin/{id}")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public Boolean deletedata(@PathVariable int id)
    {
        adminRepo.deleteAllById(Collections.singleton(id));
        return true;
    }



    @GetMapping("/totaladmin")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
        public long totaladmin()
    {
        return adminRepo.count();
    }

}
