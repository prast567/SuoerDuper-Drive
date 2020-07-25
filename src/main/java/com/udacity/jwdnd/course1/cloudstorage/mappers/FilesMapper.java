package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FilesMapper {

    @Select("SELECT FILEID AS id, FILENAME as fileName, CONTENTTYPE as contentType, FILESIZE as fileSize, FILEDATA as fileData, USERID as userId FROM files WHERE USERID=#{userId}")
    public List<Files> findFilesByUserId(Integer userId);
    
    @Insert("INSERT INTO files (FILENAME, CONTENTTYPE, FILESIZE, FILEDATA, USERID) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    public void insertFile(Files files);

    @Select("SELECT FILENAME as fileName, CONTENTTYPE as contentType, "
            + "FILESIZE as fileSize, FILEDATA as fileData, "
            + "FROM files WHERE fileName = #{fileName}")
    public Files getFileByFileName(String fileName);

    @Select("Select FILENAME as fileName from files")
    public List<String> findAllFiles();

    @Delete("DELETE FROM files where FILENAME= #{fileName}")
    public void deleteFile(String fileName);
    
}