package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "logoutButton")
    private WebElement logoutBtn;
    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;
    @FindBy(id = "add-new-note-btn")
    private WebElement addNewNoteBtn;
    @FindBy(id = "edit-note-btn")
    private WebElement editNoteBtn;
    @FindBy(id = "delete-note-btn")
    private WebElement deleteNoteBtn;
    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;
    @FindBy(id = "note-title")
    private WebElement noteTitle;
    @FindBy(id = "note-description")
    private WebElement noteDescription;
    @FindBy(id = "note-title-text")
    private WebElement noteTitleText;
    @FindBy(id = "note-description-text")
    private WebElement noteDescriptionText;

    /////// Credentials Section ////////

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;
    @FindBy(id = "add-new-credential-btn")
    private WebElement addNewCredentialBtn;
    @FindBy(id = "credential-url")
    private WebElement credentialUrl;
    @FindBy(id = "credential-username")
    private WebElement credentialUserName;
    @FindBy(id = "credential-password")
    private WebElement credentialPassword;
    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmit;
    @FindBy(id = "edit-credential-btn")
    private WebElement editCredentialBtn;
    @FindBy(id = "delete-credential-btn")
    private WebElement deleteCredentialBtn;
    @FindBy(id = "credential-url-text")
    private WebElement credentialUrlText;
    @FindBy(id = "credential-username-text")
    private WebElement credentialUserNameText;
    @FindBy(id = "credential-password-text")
    private WebElement credentialPasswordText;

    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver) {

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void logout(){
        logoutBtn.submit();
    }

    ////// Notes Helper Methods //////

    public void openNotesTab(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.notesTab);
    }

    public void clickAddNewNote(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.addNewNoteBtn);
    }

    public void clickEditNote(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.editNoteBtn);
    }

    public void populateNoteForm(String title, String description) {

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + title + "';", this.noteTitle);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + description + "';", this.noteDescription);

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.noteSubmit);
    }

    public void deleteNote(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.deleteNoteBtn);
    }

    public String getNoteTitleText(){
        System.out.println(noteTitle.getAttribute("innerHTML"));
        return noteTitleText.getAttribute("innerHTML");
    }

    public String getNoteDescriptionText(){
        return noteDescriptionText.getAttribute("innerHTML");
    }

    ////// Credentials Helper Methods ///////

    public void openCredentialsTab(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.credentialsTab);
    }

    public void clickAddNewCredential(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.addNewCredentialBtn);
    }

    public void clickEditCredential(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.editCredentialBtn);
    }

    public void populateCredentialForm(String url, String userName, String password){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + url + "';", this.credentialUrl);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + userName + "';", this.credentialUserName);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + password + "';", this.credentialPassword);

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.credentialSubmit);
    }

    public String getCredentialUrlText(){
        return credentialUrlText.getAttribute("innerHTML");
    }

    public String getCredentialUserNameText(){
        return credentialUserNameText.getAttribute("innerHTML");
    }

    public String getCredentialPasswordText(){
        return credentialPasswordText.getAttribute("innerHTML");
    }

    public String getCredentialFormPassword(){
        return credentialPassword.getAttribute("value");
    }

    public void deleteCredential(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.deleteCredentialBtn);
    }


}
