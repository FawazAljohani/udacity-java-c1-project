package com.udacity.jwdnd.course1.cloudstorage.models;

import java.sql.Blob;

public class Files {

    private Integer id;
    private String name;
    private String contentType;
    private String size;
    private Integer userId;
    private Blob blob;

    public Files(Integer id, String name, String contentType, String size, Integer userId, Blob blob) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.userId = userId;
        this.blob = blob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }
}
