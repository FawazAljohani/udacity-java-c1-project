package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public String createNote(@ModelAttribute("notes") Note note, Model model, RedirectAttributes redirectAttrs){

        String returnMessage = null;

        if(noteService.getNote(note.getNoteId()) != null){
            int rowsUpdated = noteService.updateNote(note);
            if(rowsUpdated < 0){
                returnMessage = "Something wrong occurred while updating the note, please try again";
            }

            if(returnMessage == null){
                System.out.println("Updated Note successfully");
                redirectAttrs.addFlashAttribute("successMessage", "Updated Note Successfully!");
            } else {
                redirectAttrs.addFlashAttribute("errorMessage", returnMessage);
            }
            return "redirect:home";
        }

        int rowsAdded = noteService.createNote(note);
        if(rowsAdded < 0){
            returnMessage = "Something wrong occurred while creating the note, Please try again";
        }

        if (returnMessage == null) {
            redirectAttrs.addFlashAttribute("successMessage", "Created Note Successfully!");
        } else {
            redirectAttrs.addFlashAttribute("errorMessage", returnMessage);
        }

        return "redirect:home";
    }

    @GetMapping("/delete/{noteid}")
    public String deleteNote(@PathVariable("noteid") Integer noteId, RedirectAttributes redirectAttrs){

        int rowsDeleted = noteService.deleteNote(noteId);

        if(rowsDeleted < 0){
            redirectAttrs.addFlashAttribute("errorMessage", "Failed to Delete the Note, Please Try Again!");
        } else {
            redirectAttrs.addFlashAttribute("successMessage", "Deleted Note Successfully!");
        }

        return "redirect:/home";
    }
}
