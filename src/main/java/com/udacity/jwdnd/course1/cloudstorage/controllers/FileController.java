package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {


    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String saveFile(MultipartFile fileUpload, Model model, RedirectAttributes redirectAttrs) throws IOException {

        String returnMessage = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getUser(auth.getName());

        if (fileUpload.isEmpty()) {
            System.out.println("file empty");
            redirectAttrs.addFlashAttribute("errorMessage", "Please Select a file to upload");
            return "redirect:home";
        }

        if (this.fileService.isFileNameUsed(fileUpload.getOriginalFilename(), user.getUserId())) {
            System.out.println("file name alrady yused");
            redirectAttrs.addFlashAttribute("errorMessage", "File name Already used!");
            return "redirect:home";
        }

        if(fileUpload.getSize() > 10485760){
            redirectAttrs.addFlashAttribute("errorMessage", "File Size Limit (10MB) Exceeded!");
            return "redirect:home";
        }

        int rowsAdded = this.fileService.uploadFile(fileUpload, user.getUserId());
        if(rowsAdded < 0){
            System.out.println("Adding file Operation Result is: " + rowsAdded);
            returnMessage = "Something wrong occurred while creating the note, please try again";
        }

        if (returnMessage == null) {
            redirectAttrs.addFlashAttribute("successMessage", "Uploaded File Successfully!");
        } else {
            redirectAttrs.addFlashAttribute("errorMessage", returnMessage);
        }
        return "redirect:home";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Integer fileId, RedirectAttributes redirectAttrs) {

        int rowsDeleted = fileService.deleteFile(fileId);

        if (rowsDeleted < 0) {
            redirectAttrs.addFlashAttribute("errorMessage", "Failed to Delete the File, Please Try Again!");
        } else {
            redirectAttrs.addFlashAttribute("successMessage", "Deleted File Successfully!");
        }
        return "redirect:/home";
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity downloadFile(@PathVariable("fileId") Integer fileId) {
        File file = this.fileService.getFile(fileId);
        String contentType = file.getContentType();
        String fileName = file.getFileName();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file.getFiledata());
    }

}
