package edu.udacity.java.nano;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WebSocketChatApplicationTest.class)
class WebSocketChatApplicationTest {

    @Autowired
    private MockMvc mvc;
    private SeleniumConfig config;
    private String url = "http://localhost:8080/";
    @org.junit.jupiter.api.Test

    @Before
    public void LaunchBrowser() {

        System.setProperty("webdriver.firefox.marionette","~/Downloads/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    void main() {
    }

    @org.junit.jupiter.api.Test
    void login() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/")
                .accept(MediaType. APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());
    }

    @org.junit.jupiter.api.Test
    void index() {
    }
}