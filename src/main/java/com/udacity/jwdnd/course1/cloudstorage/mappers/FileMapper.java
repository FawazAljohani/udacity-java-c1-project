package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO files (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    int insert(File file);

    @Select("SELECT fileId, fileName FROM files WHERE userid = #{userId}")
    List<File> getAllFiles(Integer userId);

    @Select("SELECT count(*) FROM files WHERE filename = #{fileName} AND userid = #{userId}")
    int getFile(String fileName, Integer userId);

    @Select("SELECT * FROM files WHERE fileId = #{fileId}")
    File getFileById(Integer fileId);

    @Delete("DELETE FROM files WHERE fileid = #{fileId}")
    int delete(Integer fileId);

}