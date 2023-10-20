package com.e_koslowa.jobvacancyseeker.login;

import com.e_koslowa.jobvacancyseeker.config.LinkedinConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class LinkedinLogin extends AbstractLogin {

    @Autowired
    LinkedinConfig config;

    private static final String USERNAME_ID = "username";
    private static final String PASSWORD_ID = "password";
    private static final String CLICK_BUTTON_XPATH = "//*[@id=\"organic-div\"]/form/div[3]/button";

    public WebDriver login() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getLoginPage());

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        driver.findElement(By.id(USERNAME_ID)).sendKeys(config.getUser());
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(config.getPassword());
        driver.findElement(By.xpath(CLICK_BUTTON_XPATH)).click();

        return driver;
    }
}
