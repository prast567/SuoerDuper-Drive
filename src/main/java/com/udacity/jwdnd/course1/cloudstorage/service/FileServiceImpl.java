package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fileService")
public class FileServiceImpl implements FileService {
    
    @Autowired
    private FilesMapper filesMapper;
    
    @Override
    public List<Files> findFilesByUserId(Integer userId) {
        return filesMapper.findFilesByUserId(userId);
    }

    @Transactional
    @Override
    public void insertFile(Files files) {
        filesMapper.insertFile(files);
    }

    @Override
    public boolean getFileByFileName(String fileName) {
        Files files = filesMapper.getFileByFileName(fileName);

        if(files != null) {
            return true;
        }

        return false;
    }

    @Override
    public List<String> findAllFiles(){
        return filesMapper.findAllFiles();
    }

    @Override
    public void deleteFile(String fileName) {
        filesMapper.deleteFile(fileName);
    }

    @Override
    public Files getFileDataByFileName(String fileName) {
        Files files = filesMapper.getFileByFileName(fileName);
        return files;
    }
}