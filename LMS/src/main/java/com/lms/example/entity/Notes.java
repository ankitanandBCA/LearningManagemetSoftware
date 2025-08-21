package com.lms.example.entity;

import jakarta.persistence.*;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] file;

    public Notes(int id, String name, byte[] file) {
        this.id = id;
        this.name = name;
        this.file = file;
    }

    public Notes()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
