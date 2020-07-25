package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.Serializable;

public class Note  implements Serializable {

    private Integer id;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }


    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", noteTitle=" + noteTitle + ", noteDescription=" + noteDescription + ", userId=" + userId + '}';
    }
}