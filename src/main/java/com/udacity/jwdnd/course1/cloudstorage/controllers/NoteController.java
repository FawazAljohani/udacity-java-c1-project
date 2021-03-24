package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public String postNote(@ModelAttribute("newNoteForm") Note note, Model model){

        String returnMessage = null;

        String noteTitle = note.getNoteTitle();
        String noteDescription = note.getNoteDescription();
        Integer userId = note.getUserId();

        int rowsAdded = noteService.createNote(noteTitle, noteDescription, userId);
        if(rowsAdded < 0){
            returnMessage = "Something wrong occured while creating the note, please try again";
        }

        if (returnMessage == null) {
            model.addAttribute("SuccessMessage", true);
        } else {
            model.addAttribute("ErrorMessage", returnMessage);
        }

        return "home";
    }
}
