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

    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver) {

        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void logout(){
        logoutBtn.submit();
    }

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

}
