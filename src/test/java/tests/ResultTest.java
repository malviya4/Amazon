package tests;

import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultPage;
import utils.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultTest {
    WebDriver driver;
    HomePage amazonHomePage;
    ResultPage amazonSearchResultsPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");
        amazonHomePage = new HomePage(driver);
        amazonSearchResultsPage = new ResultPage(driver);
    }

    @Test
    @Story("Search for LG Soundbar and sort the results by price")
    public void searchAndSortProducts() {
        // Search for "LG Soundbar"
        amazonHomePage.searchFor("LG Soundbar");

        // Read product names and prices
        Map<String, Integer> productPriceMap = amazonSearchResultsPage.getProductDetails();

        // Take screenshot of search results page
        Utility.takeScreenshot(driver, "search_results");

        // Sort products by price
        ArrayList<Map.Entry<String, Integer>> sortedProducts = new ArrayList<>(productPriceMap.entrySet());
        sortedProducts.sort(Map.Entry.comparingByValue());

        // Print sorted products
        for (Map.Entry<String, Integer> entry : sortedProducts) {
            System.out.println( "Price: " + entry.getValue() + " Product: " + entry.getKey());
        }

        // Store the result in a JSON file
        Utility.writeToJSON(productPriceMap, "product_prices");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


