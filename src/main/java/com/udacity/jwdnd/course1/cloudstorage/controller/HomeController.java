package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserPrincipal;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private NoteService noteService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EncryptionService encryptionService;


    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home(Model model, Authentication authentication) {
        UserPrincipal loggedInUser = (UserPrincipal) authentication.getPrincipal();
            model.addAttribute("uploaded", fileService.findFilesByUserId(loggedInUser.getUser().getUserId()));
            model.addAttribute("addNote", noteService.findNoteByUserId(loggedInUser.getUser().getUserId()));
            model.addAttribute("addCredential", credentialService.findCredentialsByUserId(loggedInUser.getUser().getUserId()));
            model.addAttribute("encriptionService", encryptionService);

        return "home";
    }
    
    @RequestMapping("/result")
    public String result(@ModelAttribute("status") String status, Model model) {
        model.addAttribute("status", status);
        return "result";
    }

}