package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM notes WHERE noteid = #{noteID}")
    Note getNote(Integer noteId);

    @Insert("INSERT INTO notes (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId}")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNote(String noteTitile, String noteDescription, Integer userId);
}
