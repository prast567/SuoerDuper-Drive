package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import java.util.List;


public interface FileService {
    
    public List<Files> findFilesByUserId(Integer userId);
    
    void insertFile(Files files);
    
    boolean getFileByFileName(String fileName);
    
    List<String> findAllFiles();
    
    void deleteFile(String fileName);
    
    Files getFileDataByFileName(String fileName);
}