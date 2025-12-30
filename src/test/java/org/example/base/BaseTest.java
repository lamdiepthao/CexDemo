package org.example.base;

import org.example.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void startDriver(){ driver = new DriverManager().initializeDriver();}

    @AfterMethod
    public void quitDriver() throws InterruptedException {
        Thread.sleep(100);
        driver.quit();}
}
