

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WebSocketChatApplicationTest.class)
class WebSocketChatApplicationTest {

    @Autowired
    private MockMvc mvc;
    private String url = "http://localhost:8080/";
    //WebDriver driver;
    WebDriver driver;


    @Before
    public void launchBrowser() {


    }
    void main() {
    }

    @org.junit.jupiter.api.Test
    void login() throws Exception {
        System.setProperty("webdriver.gecko.driver","/Users/benjaminkelly/Downloads/geckodriver");
        this.driver = new FirefoxDriver();
        this.driver.get("http://localhost:8080/");
        this.driver.findElement(By.id("username")).sendKeys("Jack");
        this.driver.findElement(By.className("act-but")).click();

        this.driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND +"t");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open(\"http://localhost:8080\");");
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        this.driver.findElement(By.id("username")).sendKeys("Sam");
        this.driver.findElement(By.className("act-but")).click();

        /**
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        this.driver.get("http://localhost:8080/");
        this.driver.findElement(By.id("username")).sendKeys("Same");
        driver.findElement(By.className("act-but")).click();
         */

        assertEquals("2",driver.findElement(By.className("chat-num")).getText());
    }

    @org.junit.jupiter.api.Test
    void index() {
    }
}