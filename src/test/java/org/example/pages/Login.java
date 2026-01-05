package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends BasePage {
    // 1. Khai báo các Locators (ID, Xpath, ...)
    private final By usernameInput = By.id("loginId");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By otpInput = By.xpath("(//input[@type='text'])[2]");

    public Login(WebDriver driver) {
        super(driver);
    }

    // 2. Các hành động trên trang
    public void login(String user, String pass) {
        sendKeys(usernameInput, user);
        sendKeys(passwordInput, pass);
        click(loginButton);
    }

    public void enter2FA(String code) throws InterruptedException {
        sendKeys(otpInput, code);
        Thread.sleep(4000);
    }

}