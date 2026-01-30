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

        // --- NOTE: Waiting dashboard loading ---
        sweepingPage.waitForUrlToContain("dashboard");
        sweepingPage.waitForPageLoad();
        System.out.println("Login success and Dashboard loaded");

        sweepingPage.navigateToCreateSweeping();
        sweepingPage.waitForUrlToContain("view");
        sweepingPage.waitForPageLoad();
        System.out.println("Access view create sweeping success");
        //NOTE: call a specific field as error - FillFormSweeping
        sweepingPage.clickdropdownNetwork();
        sweepingPage.clickdropdownCrypto();
        sweepingPage.clickdropdownWallet();
        sweepingPage.clickdropdownColdWallet();
        sweepingPage.clickdropdownSendKey();

        sweepingPage.submitSweeping(get2FACode());
        System.out.println("Sweeping is created successfully");
    }
}

