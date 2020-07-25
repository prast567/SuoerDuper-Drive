package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.DeleteRecord;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private CredentialService credentialService;
    
    @RequestMapping(value = "/addNote", method = RequestMethod.POST)
    public String addNote (Note addNote, Authentication authentication, RedirectAttributes rattrs){
        UserPrincipal loggedInUser = (UserPrincipal) authentication.getPrincipal();
        addNote.setUserId(loggedInUser.getUser().getUserId());
        if(addNote.getId() != null){
            noteService.updateNote(addNote);
            rattrs.addFlashAttribute("status", "passed");

        }else{
            if (noteService.getNoteByNoteTitle(addNote.getNoteTitle())){
                rattrs.addFlashAttribute("status", "noteInsertfailed");
            }
            else {
                noteService.insertNote(addNote);
                rattrs.addFlashAttribute("status", "passed");
            }
        }
        return "redirect:result";
    }

    @RequestMapping(value = "/deletenote", method = RequestMethod.POST)
    public String deletenote(@ModelAttribute("deleteRecord") DeleteRecord deleteRecord, RedirectAttributes rattrs){
        if (deleteRecord != null) {
            noteService.deleteNote(deleteRecord.getItem());
            rattrs.addFlashAttribute("status", "passed");
            return "redirect:result";
        }
        rattrs.addFlashAttribute("status", "deleteFailed");
        return "redirect:result";

    }




    }







