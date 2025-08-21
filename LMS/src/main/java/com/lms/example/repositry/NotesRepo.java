package com.lms.example.repositry;

import com.lms.example.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepo extends JpaRepository<Notes,Integer> {
}
