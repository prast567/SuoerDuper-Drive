package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;

public class Files implements Serializable {
    
    private Integer id;
    private String fileName;
    private String contentType;
    private String fileSize;
    private byte[] fileData;
    private Integer userId;

    public Files(Integer id, String fileName, String contentType, String fileSize, byte[] fileData, Integer userId) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.userId = userId;
    }

    public Files(String fileName, String contentType, String fileSize, byte[] fileData, Integer userId) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.userId = userId;
    }
    
    public Files(String fileName, String contentType, String fileSize, byte[] fileData) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
    }

    public Files() {
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
}
