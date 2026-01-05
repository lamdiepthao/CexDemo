package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Sweeping extends BasePage {
    // Locators giữ nguyên của bạn
    private final By menuMerchantWallet = By.xpath("//span[normalize-space()='Merchant Wallet']");
    private final By subMenuSweeping = By.xpath("//a[normalize-space()='Sweeping']");
    private final By btnCreateSweeping = By.xpath("//span[normalize-space()='Create Sweeping']");
    private final By btnFindAddress = By.xpath("//span[normalize-space()='Find Address']");

    // Đã đổi locator dropdownNetwork thành ID cho ổn định
    private final By dropdownNetwork = By.xpath("//input[@id='networkCode']");
    private final By selectNetwork = By.xpath("//div[contains(@class,'ant-select-item-option-content')][contains(text(),'ETHSEP (Test)')]");

    private final By dropdownCrypto = By.id("assetCode");
    private final By selectCrypto = By.xpath("//div[@class='ant-select-item-option-content'][normalize-space()='ETHSEP']");
    private final By dropdownWalletType = By.id("walletType");
    private final By selectWallet = By.xpath("//div[contains(text(),'HD')]");
    private final By dropdownColdWallet = By.id("coldWallet");
    private final By selectColdWallet = By.xpath("//div[contains(text(),'TestETH')]");
    private final By inputLimit = By.id("limit");

    private final By btnFindAddress2 = By.xpath("//div[@class='ant-drawer-extra']//span[contains(text(),'Find Address')]");
    private final By btnConfirmSweep = By.xpath("//span[normalize-space()='Confirm to Sweep']");
    private final By btnModalSubmit = By.xpath("//div[@class='ant-modal-footer']//button[1]");
    private final By inputOTP = By.xpath("(//input[@type='text'])[1]");

    public Sweeping(WebDriver driver) {
        super(driver);
    }

    public void navigateToCreateSweeping() throws InterruptedException {
        click(menuMerchantWallet);
        click(subMenuSweeping);
        click(btnCreateSweeping);

        // --- NOTE: Đợi nút Find Address của trang chính hiện lên ---
        waitForVisibility(btnFindAddress);
        click(btnFindAddress);
        Thread.sleep(3000);
    }

    public void clickdropdownNetwork(By locator){

    }

    public void fillSweepingForm(String limitValue) {

        // Click mở dropdown và chọn
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        driver.findElement(dropdownNetwork).click();


        click(selectNetwork);

        click(dropdownCrypto);
        click(selectCrypto);

        // NOTE: walletType thường bị disabled ban đầu, click để kích hoạt
        click(dropdownWalletType);
        click(selectWallet);

        click(dropdownColdWallet);
        click(selectColdWallet);

        sendKeys(inputLimit, limitValue);
    }

    public void submitSweeping(String otpCode) {
        click(btnFindAddress2);
        waitForLoadingToDisappear();
        click(btnConfirmSweep);
        click(btnModalSubmit);
        sendKeys(inputOTP, otpCode);
    }
}