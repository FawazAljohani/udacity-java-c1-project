package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	public CredentialService credentialService;
	@Autowired
	private EncryptionService encryptionService;

	private WebDriver driver;


	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	// a test that verifies that an unauthorized user can only access the login and signup pages.
	@Test
	public void confirmUnauthorizedBoundaries() {
		driver.get("http://localhost:" + this.port + "/auth/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/auth/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	//   a test that signs up a new user, logs in, verifies that the home page is accessible,
	//  logs out, and verifies that the home page is no longer accessible.
	@Test
	public void signupAndLogin(){
		String username = "test";
		String password = "uncrackable";
		String firstName = "Fawaz";
		String lastName = "Aljohani";

		driver.get("http://localhost:" + this.port + "/auth/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		driver.get("http://localhost:" + this.port + "/auth/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	public void LogoutAndConfirm(){

		signupAndLogin();

		HomePage homePage = new HomePage(driver);
		homePage.logout();

		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	// test that creates a note, and verifies it is displayed.
	// test that edits an existing note and verifies that the changes are displayed.
	// test that deletes a note and verifies that the note is no longer displayed.
	@Test
	public void createEditAndDeleteNote(){
		String noteTitle = "original title";
		String noteDescription = "original description";
		String editedNoteTitle = "editied title";
		String editedNoteDescription = "editied description";

		signupAndLogin();

		HomePage homePage = new HomePage(driver);

		homePage.openNotesTab();
		homePage.clickAddNewNote();
		homePage.populateNoteForm(noteTitle, noteDescription);

		Assertions.assertEquals(noteTitle, homePage.getNoteTitleText());
		Assertions.assertEquals(noteDescription, homePage.getNoteDescriptionText());

		homePage.openNotesTab();
		homePage.clickEditNote();
		homePage.populateNoteForm(editedNoteTitle, editedNoteDescription);

		Assertions.assertEquals(editedNoteTitle, homePage.getNoteTitleText());
		Assertions.assertEquals(editedNoteDescription, homePage.getNoteDescriptionText());

		homePage.openNotesTab();
		homePage.deleteNote();

		Assertions.assertThrows(NoSuchElementException.class, homePage::getNoteTitleText);
	}


	// a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
	// a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
	// a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed

	@Test
	public void createEditAndDeleteCredential(){
		String url = "https://mail.google.com/";
		String userName = "Fawaz";
		String password = "123456";
		String editedUrl = "https://twitter.com";
		String editedUserName = "Abdullah";
		String editedPassword = "123321";

		signupAndLogin();

		HomePage homePage = new HomePage(driver);

		// Create and Verify

		homePage.openCredentialsTab();
		homePage.clickAddNewCredential();
		homePage.populateCredentialForm(url, userName, password);

		Credential credential = this.credentialService.getCredential(1);

		Assertions.assertEquals(url, homePage.getCredentialUrlText());
		Assertions.assertEquals(userName, homePage.getCredentialUserNameText());
		Assertions.assertEquals(this.encryptionService.encryptValue(password, credential.getKey()), homePage.getCredentialPasswordText());

		// Verify decrypted password and Edit

		homePage.openCredentialsTab();
		homePage.clickEditCredential();

		Assertions.assertEquals(password, homePage.getCredentialFormPassword());

		// Edit and verify changes

		homePage.populateCredentialForm(editedUrl, editedUserName, editedPassword);
		Credential editedCredential = this.credentialService.getCredential(1);
		homePage.openCredentialsTab();

		Assertions.assertEquals(editedUrl, homePage.getCredentialUrlText());
		Assertions.assertEquals(editedUserName, homePage.getCredentialUserNameText());
		Assertions.assertEquals(this.encryptionService.encryptValue(editedPassword, editedCredential.getKey()), homePage.getCredentialPasswordText());

		// Delete and verify

		homePage.deleteCredential();

		Assertions.assertThrows(NoSuchElementException.class, homePage::getCredentialUrlText);
	}
}
