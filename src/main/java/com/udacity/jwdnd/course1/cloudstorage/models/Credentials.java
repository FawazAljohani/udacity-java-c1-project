package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credentials {

    private Integer id;
    private String url;
    private String userName;
    private String password;
    private Integer userId;

    public Credentials(Integer id, String url, String userName, String password, Integer userId) {
        this.id = id;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
