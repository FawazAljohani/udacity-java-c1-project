package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(String noteTitle, String noteDescription, Integer userId){

        return noteMapper.createNote(noteTitle, noteDescription, userId);
    }

    public Note getNote(Integer noteId){

        Note note = noteMapper.getNote(noteId);
        if(note != null){
            return note;
        }
        return null;
    }

}
