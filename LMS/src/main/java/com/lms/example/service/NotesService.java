package com.lms.example.service;

import com.lms.example.entity.Notes;
import com.lms.example.repositry.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotesService {

    @Autowired
    NotesRepo notesRepo;


    public Notes addnotes(Notes notes)
    {
        return notesRepo.save(notes);
    }

    public Notes getbyId(int id)
    {
        return notesRepo.findById(id).orElseThrow(() -> new RuntimeException("File Not Found"));
    }
}
