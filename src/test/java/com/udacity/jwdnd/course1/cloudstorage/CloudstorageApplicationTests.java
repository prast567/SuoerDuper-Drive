package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudstorageApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    
    /* generate random sign up data */
    private static String randomUserId = null;
    private static String randomFirstName = null;
    private static String randomLastName = null;
    private static String randomPassword = null;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
        
        /* generate random sign up data */
        randomUserId = randomString(6);
        randomFirstName = randomString(4);
        randomLastName = randomString(4);
        randomPassword = randomString(6);
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new FirefoxDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
    

    @Test
    @Order(1)
    public void testUnAuthorizedAccess() {
        /* test if login page accessible without login */
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());

        /* test if signup page accessible without login */
        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());

        /* test to check home page is not accessible without login */
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    @Order(2)
    public void testAuthCycle() {
        

        /* sign up request */
        driver.get("http://localhost:" + this.port + "/signup");
        driver.findElement(By.xpath("//*[@id=\"inputFirstName\"]")).sendKeys(randomFirstName);
        driver.findElement(By.xpath("//*[@id=\"inputLastName\"]")).sendKeys(randomLastName);
        driver.findElement(By.xpath("//*[@id=\"inputUsername\"]")).sendKeys(randomUserId);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("/html/body/div/form/button")).click();

        /*log in request */
        driver.get("http://localhost:" + this.port + "/login");
        driver.findElement(By.xpath("//*[@id=\"inputUsername\"]")).sendKeys(randomUserId);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        
        /* test to check home page is not accessible without login */
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());
        
        /* test to check home page is not accessible without login */
        driver.get("http://localhost:" + this.port + "/logout");
        Assertions.assertEquals("Login", driver.getTitle());
        
        /* test to check home page is not accessible without login */
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
        
    }
    
    @Test
    @Order(3)
    public void addNote() {
        /*log in request */
        driver.get("http://localhost:" + this.port + "/login");
        driver.findElement(By.xpath("//*[@id=\"inputUsername\"]")).sendKeys(randomUserId);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-notes-tab']")).click();
        
        driver.findElement(By.xpath("//*[@id='addNotes']")).click();
        
        String noteTitle = randomString(10);
        String noteDescription = randomString(20);
        driver.findElement(By.xpath("//*[@id=\"note-title\"]")).sendKeys(noteTitle);
        driver.findElement(By.xpath("//*[@id=\"note-description\"]")).sendKeys(noteDescription);
        
        driver.findElement(By.xpath("//*[@id='addNewNote']")).click();
        
        Assertions.assertEquals("Result", driver.getTitle());
        
        String result = driver.findElement(By.xpath("/html/body/div/div/h1")).getText();
        
        Assertions.assertEquals("Success", result);
        
        driver.findElement(By.xpath("/html/body/div/div/span/a")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-notes-tab']")).click();
        
        String currentTitle = driver.findElement(By.xpath("(//*[@id='nav-notes']/div/table[1]/tbody/tr)[last()]/td[3]")).getText();
        
        Assertions.assertEquals(currentTitle, noteTitle);
        
    }
    
    @Test
    @Order(4)
    public void editNote() {
        /*log in request */
        driver.get("http://localhost:" + this.port + "/login");
        driver.findElement(By.xpath("//*[@id=\"inputUsername\"]")).sendKeys(randomUserId);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-notes-tab']")).click();
            
        WebElement editBtn =  driver.findElement(By.xpath("(//*[@id='nav-notes']/div/table[1]/tbody/tr)[last()]/td/button"));
        editBtn.click();
        
        String noteEditDescription = randomString(20);
        
        driver.findElement(By.xpath("//*[@id=\"note-description\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"note-description\"]")).sendKeys(noteEditDescription);
        
        driver.findElement(By.xpath("//*[@id='addNewNote']")).click();
        
        Assertions.assertEquals("Result", driver.getTitle());
        
        String result = driver.findElement(By.xpath("/html/body/div/div/h1")).getText();
        
        Assertions.assertEquals("Success", result);
        
        driver.findElement(By.xpath("/html/body/div/div/span/a")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-notes-tab']")).click();
        
        String currentDescription = driver.findElement(By.xpath("(//*[@id='nav-notes']/div/table[1]/tbody/tr)[last()]/td[4]")).getText();
        
        Assertions.assertEquals(currentDescription, noteEditDescription);
        
    }
    
   
    @Test
    @Order(5)
    public void deleteNote() {
        /*log in request */
        driver.get("http://localhost:" + this.port + "/login");
        driver.findElement(By.xpath("//*[@id=\"inputUsername\"]")).sendKeys(randomUserId);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        /* test add notes */
        driver.findElement(By.xpath("//*[@id='nav-notes-tab']")).click();
            
        WebElement deleteBtn =  driver.findElement(By.xpath("(//*[@id='nav-notes']/div/table[1]/tbody/tr)[last()]/td[2]/form/button"));
        deleteBtn.click();
        
        Assertions.assertEquals("Result", driver.getTitle());
        
        String result = driver.findElement(By.xpath("/html/body/div/div/h1")).getText();
        
        Assertions.assertEquals("Success", result);
        
        driver.findElement(By.xpath("/html/body/div/div/span/a")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-notes-tab']")).click();
        
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='nav-notes']/div/table[1]/tbody/tr"));
        
        Assertions.assertEquals(0, rows.size());

    }
    
    @Test
    @Order(6)
    public void addCredential() {
        /*log in request */
        driver.get("http://localhost:" + this.port + "/login");
        driver.findElement(By.xpath("//*[@id=\"inputUsername\"]")).sendKeys(randomUserId);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-credentials-tab']")).click();
        
        driver.findElement(By.xpath("//*[@id='addCredential']")).click();
        
        String url = "http://www." + randomString(10) + ".com";
        String username = randomString(8);
        String password = randomString(6);
        
        driver.findElement(By.xpath("//*[@id=\"credential-url\"]")).sendKeys(url);
        driver.findElement(By.xpath("//*[@id=\"credential-username\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"credential-password\"]")).sendKeys(password);
        
        driver.findElement(By.xpath("//*[@id='addNewCredential']")).click();
        
        Assertions.assertEquals("Result", driver.getTitle());
        
        String result = driver.findElement(By.xpath("/html/body/div/div/h1")).getText();
        
        Assertions.assertEquals("Success", result);
        
        driver.findElement(By.xpath("/html/body/div/div/span/a")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-credentials-tab']")).click();
        
        String currentUrl = driver.findElement(By.xpath("(//*[@id='nav-credentials']/div/table[1]/tbody/tr)[last()]/td[3]")).getText();
        String currentUsername = driver.findElement(By.xpath("(//*[@id='nav-credentials']/div/table[1]/tbody/tr)[last()]/td[4]")).getText();
        
        Assertions.assertEquals(url, currentUrl);
        Assertions.assertEquals(username, currentUsername);

    }
    
    @Test
    @Order(7)
    public void editCredential() {
        /*log in request */
        driver.get("http://localhost:" + this.port + "/login");
        driver.findElement(By.xpath("//*[@id=\"inputUsername\"]")).sendKeys(randomUserId);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-credentials-tab']")).click();
            
        WebElement editBtn =  driver.findElement(By.xpath("(//*[@id='nav-credentials']/div/table[1]/tbody/tr)[last()]/td/button"));
        editBtn.click();
        
        String url = "https://www." + randomString(10) + ".com";
        String username = randomString(8);
        String password = randomString(6);
        
        driver.findElement(By.xpath("//*[@id=\"credential-url\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"credential-url\"]")).sendKeys(url);
        driver.findElement(By.xpath("//*[@id=\"credential-username\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"credential-username\"]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"credential-password\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"credential-password\"]")).sendKeys(password);
        
        driver.findElement(By.xpath("//*[@id='addNewCredential']")).click();
        
        Assertions.assertEquals("Result", driver.getTitle());
        
        String result = driver.findElement(By.xpath("/html/body/div/div/h1")).getText();
        
        Assertions.assertEquals("Success", result);
        
        driver.findElement(By.xpath("/html/body/div/div/span/a")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-credentials-tab']")).click();
        
        String currentUrl = driver.findElement(By.xpath("(//*[@id='nav-credentials']/div/table[1]/tbody/tr)[last()]/td[3]")).getText();
        String currentUsername = driver.findElement(By.xpath("(//*[@id='nav-credentials']/div/table[1]/tbody/tr)[last()]/td[4]")).getText();
        
        Assertions.assertEquals(url, currentUrl);
        Assertions.assertEquals(username, currentUsername);

    }
    
    
    @Test
    @Order(8)
    public void deleteCredential() {
        /*log in request */
        driver.get("http://localhost:" + this.port + "/login");
        driver.findElement(By.xpath("//*[@id=\"inputUsername\"]")).sendKeys(randomUserId);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(randomPassword);
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-credentials-tab']")).click();
            
        WebElement deleteBtn =  driver.findElement(By.xpath("(//*[@id='nav-credentials']/div/table[1]/tbody/tr)[last()]/td[2]/form/button"));
        deleteBtn.click();
        
        Assertions.assertEquals("Result", driver.getTitle());
        
        String result = driver.findElement(By.xpath("/html/body/div/div/h1")).getText();
        
        Assertions.assertEquals("Success", result);
        
        driver.findElement(By.xpath("/html/body/div/div/span/a")).click();
        
        Assertions.assertEquals("Home", driver.getTitle());
        
        driver.findElement(By.xpath("//*[@id='nav-credentials-tab']")).click();
        
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='nav-credentials']/div/table[1]/tbody/tr"));
        
        Assertions.assertEquals(0, rows.size());
    }
    
    private static String randomString(int n) {
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
