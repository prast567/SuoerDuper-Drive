package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.DeleteRecord;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserPrincipal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FilesController {

    @Autowired
    private NoteService noteService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private CredentialService credentialService;
    
    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public String upload(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes rattrs, Authentication authentication) throws IllegalStateException, IOException {
        UserPrincipal loggedInUser = (UserPrincipal) authentication.getPrincipal();
        Files files = new Files(file.getOriginalFilename(), file.getContentType(), Long.toString(file.getSize()), file.getBytes());
        files.setUserId(loggedInUser.getUser().getUserId());

        if (fileService.getFileByFileName(files.getFileName())) {
            rattrs.addFlashAttribute("status", "failed");
            return "redirect:result";

        } else {
            String path = System.getProperty("user.dir");
            File tmpFile = new File(path + File.separator +"storage"+ File.separator +files.getFileName());
            file.transferTo(tmpFile);
            fileService.insertFile(files);
            rattrs.addFlashAttribute("status", "passed");
            return "redirect:result";

        }
                    }
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public String view (@ModelAttribute("deleteRecord") DeleteRecord deleteRecord, RedirectAttributes rattrs) throws IOException {
            System.out.println(deleteRecord.getItem());
            Files files = fileService.getFileDataByFileName(deleteRecord.getItem());
            byte[] data = files.getFileData();

            String path = System.getProperty("user.dir");

            File file = new File(path + File.separator +"storage"+ File.separator +deleteRecord.getItem());
            try {
                OutputStream os = new FileOutputStream(file);
                os.write(data);
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Runtime.getRuntime().exec("explorer "+ path+ File.separator + "storage");
            rattrs.addFlashAttribute("status", "passed");
            return "redirect:result";
        }

        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public String delete (@ModelAttribute("deleteRecord") DeleteRecord deleteRecord, RedirectAttributes rattrs){
        System.out.println(deleteRecord.getItem());
            if (deleteRecord != null) {
                fileService.deleteFile(deleteRecord.getItem());
                rattrs.addFlashAttribute("status", "passed");
                return "redirect:result";
            }
            rattrs.addFlashAttribute("status", "failed");
            return "redirect:result";

        }
}

