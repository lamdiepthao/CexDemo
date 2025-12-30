package org.example;

import org.example.base.BaseTest;
import org.example.pages.Login;
import org.example.pages.Sweeping;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import java.time.Duration;

public class MyTestCases extends BaseTest {
    private static final String SECRET_KEY = "HZZABH7ASYFPJYGJI454CDVS";
    private static final String USERNAME = "tanniempg";
    private static final String PASSWORD = "Test@123";
    private static final String LOGIN_URL = "https://merchant-bo-sit.mqbc21.com/backofficeV2/login";
    // Calculation function for 2FA
    // =========================================================================
    private String get2FACode() {
        // Create TOTP from secret key
        Totp totp = new Totp(SECRET_KEY);
        // Return OTP 6 number base on time
        return totp.now();
    }

    @Test
    public void testSuccessfulLoginWith2FA() throws InterruptedException {
        Login loginPage = new Login(driver);
        Sweeping sweepingPage = new Sweeping(driver);

        driver.get(LOGIN_URL);
        loginPage.login(USERNAME, PASSWORD);
        loginPage.enter2FA(get2FACode());
        System.out.println("Login success");

        sweepingPage.navigateToCreateSweeping();
        sweepingPage.fillSweepingForm("1");
        sweepingPage.submitSweeping(get2FACode());

        System.out.println("Sweeping is created successfully");
    }

//        // Create sweeping flow:
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//span[normalize-space()='Merchant Wallet']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//a[normalize-space()='Sweeping']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//span[normalize-space()='Create Sweeping']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//span[normalize-space()='Find Address']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//input[@id='networkCode']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//div[contains(text(),'ETHSEP (Test)')]")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//input[@id='assetCode']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//div[@class='ant-select-item-option-content'][normalize-space()='ETHSEP']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//input[@id='walletType']")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//div[contains(text(),'HD')]")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//input[@id='coldWallet']")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//div[contains(text(),'TestETH')]")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//input[@id='limit']")).sendKeys("1");
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//div[@class='ant-drawer-extra']//span[contains(text(),'Find Address')]")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//span[normalize-space()='Confirm to Sweep']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//div[@class='ant-modal-footer']//button[1]")).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[2]")));
//        driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(twoFactorCode);
//        Thread.sleep(3000);
//        System.out.println("Sweeping is created successfully");
    }

