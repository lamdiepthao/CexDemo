package org.example.pages;

import org.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Sweeping extends BasePage{
    // --- Locators ---
    private final By menuMerchantWallet = By.xpath("//span[normalize-space()='Merchant Wallet']");
    private final By subMenuSweeping = By.xpath("//a[normalize-space()='Sweeping']");
    private final By btnCreateSweeping = By.xpath("//span[normalize-space()='Create Sweeping']");
    private final By btnFindAddress = By.xpath("//span[normalize-space()='Find Address']");
    // Form fields
    private final By dropdownNetwork = By.xpath("//input[@id='networkCode']");
    private final By selectNetwork = By.xpath("//div[contains(text(),'ETHSEP (Test)')]");
    private final By dropdownCrypto = By.id("assetCode");
    private final By selectCrypto = By.xpath("//div[@class='ant-select-item-option-content'][normalize-space()='ETHSEP']");
    private final By dropdownWalletType = By.id("walletType");
    private final By selectWallet = By.xpath("//div[contains(text(),'HD')]");
    private final By dropdownColdWallet = By.id("coldWallet");
    private final By selectColdWallet = By.xpath("//div[contains(text(),'TestETH')]");
    private final By inputLimit = By.id("limit");
    // Buttons & Final steps
    private final By btnFindAddress2 = By.xpath("//div[@class='ant-drawer-extra']//span[contains(text(),'Find Address')]");
    private final By btnConfirmSweep = By.xpath("//span[normalize-space()='Confirm to Sweep']");
    private final By btnModalSubmit = By.xpath("//div[@class='ant-modal-footer']//button[1]");
    private final By inputOTP = By.xpath("(//input[@type='text'])[1]");
    // Constructor
    public Sweeping(WebDriver driver) {
        super(driver);
    }
    //---Action---
    public void navigateToCreateSweeping() {
        click(menuMerchantWallet);
        click(subMenuSweeping);
        click(btnCreateSweeping);
        click(btnFindAddress);
    }
    public void fillSweepingForm(String limitValue) {
        click(dropdownNetwork);
        click(selectNetwork);
        click(dropdownCrypto);
        click(selectCrypto);
        click(dropdownWalletType);
        click(selectWallet);
        click(dropdownColdWallet);
        click(selectColdWallet);
        sendKeys(inputLimit, limitValue);
    }

    public void submitSweeping(String otpCode) {
        click(btnFindAddress2);
        click(btnConfirmSweep);
        click(btnModalSubmit);

        // Nhập OTP cuối cùng để hoàn tất
        sendKeys(inputOTP, otpCode);
    }
}
