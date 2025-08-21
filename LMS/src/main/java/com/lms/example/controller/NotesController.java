package com.lms.example.controller;

import com.lms.example.entity.Notes;
import com.lms.example.repositry.NotesRepo;
import com.lms.example.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class NotesController {

    @Autowired
    NotesService notesService;

    @Autowired
    NotesRepo notesRepo;


    @PostMapping("/uploadFile")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public Notes uploadnotes(@RequestParam("name")String name, @RequestParam("file")MultipartFile file) throws IOException {

        byte[] filebyte= file.getBytes();

        Notes n=new Notes();
        n.setName(name);
        n.setFile(filebyte);

       return notesService.addnotes(n);
    }



    @GetMapping("/view/{id}")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public ResponseEntity<byte[]> viewPdf(@PathVariable int id) {
        Notes notes = notesService.getbyId(id);

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "inline; filename=document.pdf")
                .body(notes.getFile());
    }



    @GetMapping("/notes")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public List<Notes> getUsers() {
        return notesRepo.findAll();
    }

    @DeleteMapping("/deletepdfss/{id}")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public Boolean deleletepdfs(@PathVariable int id)
    {
        notesRepo.deleteById(id);
        return true;
    }

    @GetMapping("/totalnotes")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public long totalnotes() {
        return notesRepo.count();
    }








}
