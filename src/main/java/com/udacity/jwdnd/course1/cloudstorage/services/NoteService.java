package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note note){

        return noteMapper.createNote(note);
    }

    public Note getNote(Integer noteId){

        Note note = noteMapper.getNote(noteId);
        if(note != null){
            return note;
        }
        return null;
    }

    public List<Note> getAllNotes(Integer userId){
        return noteMapper.getAllNotes(userId);
    }

    public int deleteNote(Integer noteId){
        return noteMapper.deleteNote(noteId);
    }

    public int updateNote(Note note){
        return noteMapper.updateNote(note);
    }

}
