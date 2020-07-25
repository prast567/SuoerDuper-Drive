package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.DeleteRecord;
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
public class CredentialController {

    @Autowired
    private NoteService noteService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private CredentialService credentialService;
    
    @RequestMapping(value = "/addCredential", method = RequestMethod.POST)
    public String addCredential (Credential addCredential, Authentication authentication, RedirectAttributes rattrs){
        UserPrincipal loggedInUser = (UserPrincipal) authentication.getPrincipal();
        addCredential.setUserId(loggedInUser.getUser().getUserId());
        if(addCredential.getId() != null){
            credentialService.updateCredential(addCredential);
            rattrs.addFlashAttribute("status", "passed");

        }else{
            if (credentialService.getCredentialByuserName(addCredential.getUserName())){
                rattrs.addFlashAttribute("status", "credInsertfailed");
            }
            else {
                credentialService.insertCredential(addCredential);
                rattrs.addFlashAttribute("status", "passed");
            }
        }
        return "redirect:result";
    }
    @RequestMapping(value = "/deletecredential", method = RequestMethod.POST)
    public String deletecredential(@ModelAttribute("deleteRecord") DeleteRecord deleteRecord, RedirectAttributes rattrs){
        if (deleteRecord != null) {
            credentialService.deleteCredential(deleteRecord.getItem());
            rattrs.addFlashAttribute("status", "passed");
            return "redirect:result";
        }
        rattrs.addFlashAttribute("status", "failed");
        return "redirect:result";

    }
}