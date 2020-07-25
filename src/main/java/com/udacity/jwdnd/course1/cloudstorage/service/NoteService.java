package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import java.util.List;


public interface NoteService {
    
    public List<Note> findNoteByUserId(Integer userId);
    
    void insertNote(Note addNote);
    
    boolean getNoteByNoteTitle(String noteTitle);
    
    List<Note> findAllNotes();
    
    void deleteNote(String noteTitle);

     public void updateNote(Note updateNote);
}