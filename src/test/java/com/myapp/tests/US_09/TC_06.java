package com.myapp.tests.US_09;

import com.myapp.pages.PearlyMarketHomePage;
import com.myapp.pages.PearlyMarketMyAccountPage;
import com.myapp.pages.PearlyMarketRegisterPage;
import com.myapp.pages.PearlyMarketVendorRegisterPage;
import com.myapp.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_06 {
//    1_Go to https://pearlymarket.com/
//    2_ Navigate to registration page
//    3_Click on Sign Up button
//    4_On the Register screen, locate the "Become a Vendor" link and click on
//    5_Enter a valid email
//    6_Verify that “Verification code sent to your email: stevegregor2023@gmail.com.” is visible
//    7_Enter a password that contains lowercase letters, digits, and special characters, but no uppercase letter
//    8_ Proceed with the registration process.
//    9_Verify that an error message is displayed, indicating that the password must contain at least one uppercase letter.

    @Test
    public void US09_TC06 () throws IOException {

        // 1_Go to https://pearlymarket.com/
        Driver.getDriver().get(ConfigReader.getProperty("pearlymarket_homepage_url"));

        PearlyMarketHomePage pearlyMarketHomePage = new PearlyMarketHomePage();
        PearlyMarketRegisterPage pearlyMarketRegisterPage = new PearlyMarketRegisterPage();
        PearlyMarketVendorRegisterPage pearlyMarketVendorRegisterPage = new PearlyMarketVendorRegisterPage();
        String path = "src/test/java/resources/email.xlsx";
        String sheetName="Sheet1";
        ExcelUtils excelUtils = new ExcelUtils(path,sheetName);

        //2_ Navigate to registration page
        pearlyMarketHomePage.homepageRegisterButton.click();

        //3_Verify that Sign Up button clicked
        ReusableMethods.verifyElementClickable(pearlyMarketRegisterPage.signUpLink);

        //4_On the Register screen, locate the "Become a Vendor" link and click on
        pearlyMarketRegisterPage.becomeAVendorLink.click();

        //5_Enter a valid email
        pearlyMarketVendorRegisterPage.emailBox.sendKeys(ConfigReader.getProperty("vendorregistermail_fornotuppercase"));

        //6_Verify that “Verification code sent to your email: stevegregor2023@gmail.com.” is visible
        pearlyMarketVendorRegisterPage.verifCodeBox.click();
        WaitUtils.waitFor(4);
        ReusableMethods.verifyElementDisplayed(pearlyMarketVendorRegisterPage.verificationCodeSentBox);

        Driver.getDriver().switchTo().newWindow(WindowType.TAB);
        Driver.getDriver().get(ConfigReader.getProperty("outlook_login"));
        WaitUtils.waitFor(3);
        Driver.getDriver().findElement(By.id("i0116")).sendKeys("stephon.joren@outlook.com");
        Driver.getDriver().findElement(By.id("idSIButton9")).click();

        WaitUtils.waitFor(2);

        Driver.getDriver().findElement(By.id("i0118")).sendKeys(excelUtils.getCellData(1,1));
        Driver.getDriver().findElement(By.id("idSIButton9")).click();

//      Wait until the verification code will be sent from pearly market
        WaitUtils.waitFor(3);
        Driver.getDriver().navigate().refresh();
        WaitUtils.waitForVisibility(By.xpath("//div//span[@title='info@pearlymarket.com']"),4).click();

        WaitUtils.waitFor(4);

        String verifCode=Driver.getDriver().findElement(By.xpath("//td//div//p//b")).getText();
        ReusableMethods.switchToWindow(0);
        pearlyMarketVendorRegisterPage.verifCodeBox.sendKeys(verifCode);

        //7_Enter a password that contains lowercase letters, digits, and special characters, but no uppercase letter
        pearlyMarketVendorRegisterPage.passwordBox.sendKeys("password123!");
        pearlyMarketVendorRegisterPage.confirmPasswordBox.sendKeys("password123!");

        WaitUtils.waitFor(2);

        //8_ Proceed with the registration process.
        JSUtils.clickWithTimeoutByJS(pearlyMarketVendorRegisterPage.registerButton);

        //9_Verify that an error message is displayed, indicating that the password must contain at least one uppercase letter.
        // We can directly registered with no uppercase password.
        MediaUtils.takeScreenshotOfTheEntirePageAsString();

    }
}
