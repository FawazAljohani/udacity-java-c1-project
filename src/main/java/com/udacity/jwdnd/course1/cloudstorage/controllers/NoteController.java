package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public String createNote(@ModelAttribute("notes") Note note, Model model){

        String returnMessage = null;

        if(noteService.getNote(note.getNoteId()) != null){
            noteService.updateNote(note);
            return "redirect:home";
        }

        int rowsAdded = noteService.createNote(note);
        if(rowsAdded < 0){
            returnMessage = "Something wrong occured while creating the note, please try again";
        }

        if (returnMessage == null) {
            System.out.printf("created note successfully");
            model.addAttribute("successMessage", true);
        } else {
            model.addAttribute("errorMessage", returnMessage);
        }

        return "redirect:home";
    }

    @GetMapping("/delete/{noteid}")
    public String deleteNote(@PathVariable("noteid") Integer noteId, Model model){

        noteService.deleteNote(noteId);

        return "redirect:/home";
    }
}
