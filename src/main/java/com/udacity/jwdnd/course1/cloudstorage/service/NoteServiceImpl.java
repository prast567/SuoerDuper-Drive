package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;
    
    @Override
    public List<Note> findNoteByUserId(Integer userId) {
        return noteMapper.findNoteByUserId(userId);
    }

    @Transactional
    @Override
    public void insertNote(Note addNote) {
        noteMapper.insertNote(addNote);
    }

    public void updateNote(Note updateNote) { noteMapper.updateNote(updateNote);}

    @Override
    public boolean getNoteByNoteTitle(String noteTitle) {
        Note addNote = noteMapper.getNoteByNoteTitle(noteTitle);

        if(addNote != null) {
            return true;
        }

        return false;
    }

    @Override
    public List<Note> findAllNotes(){
        return noteMapper.findAllNotes();
    }

    @Override
    public void deleteNote(String noteTitle) {
        noteMapper.deleteNote(noteTitle);
    }


}