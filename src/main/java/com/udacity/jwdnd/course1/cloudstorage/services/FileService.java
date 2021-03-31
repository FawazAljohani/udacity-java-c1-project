package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {


    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    private final FileMapper fileMapper;

    public List<File> getFiles(Integer userId) {
        List<File> files = this.fileMapper.getAllFiles(userId);
        return files;
    }

    public File getFile(Integer fileId) {
        return this.fileMapper.getFileById(fileId);
    }

    public int uploadFile(MultipartFile multipartFile, Integer userId) throws IOException {

        File file = new File();
        file.setFileName(multipartFile.getOriginalFilename());
        file.setFileSize(multipartFile.getSize());
        file.setContentType(multipartFile.getContentType());
        file.setFileData(multipartFile.getBytes());
        file.setUserId(userId);

        try {
            return this.fileMapper.insert(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }

    }

    public boolean isFileNameUsed(String fileName, Integer userId) {

        int fileCount = fileMapper.getFile(fileName, userId);
        if (fileCount > 0) {
            return true;
        }
        return false;
    }

    public int deleteFile(Integer fileId) {
       return fileMapper.delete(fileId);
    }

}
