package com.lms.example.service;

import com.lms.example.entity.Video;
import com.lms.example.repositry.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class VideoService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Autowired
    private VideoRepository videoRepository;

    // apna path set karein

    public Video saveVideo(String description, MultipartFile file) throws IOException {
        // server me file save
        String filePath = uploadDir + File.separator + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        // database me details save
        Video video = new Video();
        video.setDescription(description);
        video.setFilePath(filePath);

        return videoRepository.save(video);
    }
}

