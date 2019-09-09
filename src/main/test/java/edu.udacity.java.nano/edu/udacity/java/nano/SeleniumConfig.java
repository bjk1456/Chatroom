package edu.udacity.java.nano;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumConfig {
    private WebDriver driver;

    /**
    public SeleniumConfig() {
        Capabilities capabilities = DesiredCapabilities.firefox();
        driver = new FirefoxDriver();
        driver.setCapability(FirefoxDriver.BINARY, "~/Downloads/geckodriver");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
     */
    public SeleniumConfig() {
        System.setProperty("webdriver.firefox.marionette", "~/Downloads/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }



    static private String findFile(String filename) {
        String paths[] = {"", "bin/", "target/classes"};
        for (String path : paths) {
            if (new File(path + filename).exists())
                return path + filename;
        }
        return "";
    }

    public WebDriver getDriver() {
        return driver;
    }
}

