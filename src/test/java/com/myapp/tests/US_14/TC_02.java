package com.myapp.tests.US_14;

import com.myapp.pages.PearlyMarketAddProductPage;
import com.myapp.pages.PearlyMarketHomePage;
import com.myapp.utilities.Driver;
import com.myapp.utilities.ReusableMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_02 {

    ReusableMethods reMethods = new ReusableMethods();
    PearlyMarketHomePage pmHomePage = new PearlyMarketHomePage();
    PearlyMarketAddProductPage pmAddProductPage = new PearlyMarketAddProductPage();
    JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();

    @Test
    public void addNewProductMenuTest(){
        reMethods.signIn();
        reMethods.scrollPageEndActions();
        // reMethods.waitForVisibility(pmHomePage.myAccountButton, 11);
        pmHomePage.myAccountButton.click();
        pmHomePage.storeManagerButton.click();
        pmHomePage.myStoreProductsElement.click();
        reMethods.hoverOverOnElementActions(pmHomePage.myStoreProductsElement);
        pmHomePage.productsAddnewElement.click();
        // reMethods.scrollIntoView(pmAddProductPage.featuredImageBox);
        executor.executeScript("arguments[0].click();", pmAddProductPage.galaryImageBox);
        // pmAddProductPage.addToGalarySelectFileButton.click();
        String image1_FilePath= System.getProperty("user.home") + "\\OneDrive\\Desktop\\deneme\\Stool.PNG";
        pmAddProductPage.addToGalarySelectFileButton.sendKeys(image1_FilePath);
        pmAddProductPage.mediaLibrarySection.click();
        pmAddProductPage.addToGalaryStoolImage.click();
        pmAddProductPage.addToGalaryButton.click();

        pmAddProductPage.featuredImageBox.click();
        pmAddProductPage.chooseImageStoolImage.click();
        pmAddProductPage.chooseImageSelectButton.click();

    }

}