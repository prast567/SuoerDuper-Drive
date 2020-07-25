package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    
    @Select("SELECT ID AS id, NOTETITLE as noteTitle, NOTEDESCRIPTION as noteDescription, USERID as userId FROM notes WHERE USERID=#{userId}")
    public List<Note> findNoteByUserId(Integer userId);
    
    @Insert("INSERT INTO notes (NOTETITLE, NOTEDESCRIPTION, USERID) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    public void insertNote(Note addNote);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE id = #{id}")
    public int updateNote(Note note);

    @Select("SELECT NOTETITLE as noteTitle, NOTEDESCRIPTION as noteDEscription, "
            + "FROM notes WHERE noteTitle = #{noteTitle}")
    public Note getNoteByNoteTitle(String noteTitle);

    @Select("Select NOTETITLE as noteTitle, NOTEDESCRIPTION as noteDescription from notes")
    public List<Note> findAllNotes();

    @Delete("DELETE FROM notes where NOTETITLE= #{noteTitle}")
    public void deleteNote(String noteTitle);
}