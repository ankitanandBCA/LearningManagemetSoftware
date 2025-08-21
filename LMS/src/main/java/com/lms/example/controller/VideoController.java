package com.video.example.controller;

import com.video.example.entity.Video;
import com.video.example.repositry.VideoRepository;
import com.video.example.service.VideoService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    VideoRepository videoRepository;

    @PostMapping("/upload")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public String uploadVideo(@RequestParam("description") String description,
                              @RequestParam("file") MultipartFile file) {
        try {
            Video savedVideo = videoService.saveVideo(description, file);
            return "Video uploaded successfully! ID: " + savedVideo.getId();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }



    @GetMapping("/all")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }


    @GetMapping("/play/{id}")
    @CrossOrigin(origins = "https://lmssoftware-7c720.web.app")
    public void streamVideo(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        File file = new File(video.getFilePath());
        if (!file.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("video/mp4");
        response.setHeader("Accept-Ranges", "bytes");
        Files.copy(file.toPath(), response.getOutputStream());
    }



}


