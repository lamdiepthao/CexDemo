package org.example.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForUrlToContain(String fraction) {
        wait.until(ExpectedConditions.urlContains(fraction));
    }

    protected void click(By locator)  {
        waitForLoadingToDisappear(); // Đợi hết loading mới click
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void sendKeys(By locator, String text) {
        waitForLoadingToDisappear();
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        el.clear();
        el.sendKeys(text);
    }

    protected void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForLoadingToDisappear() {
        // Tăng thêm các class loading phổ biến của AntD
        By loader = By.cssSelector("div[data-testid='loading'], .ant-spin, .ant-drawer-mask-hidden");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.presenceOfElementLocated(loader));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception e) {
            // No loader found
        }
    }
}