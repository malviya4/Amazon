package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResultPage {

    WebDriver driver;

    By productNames = By.cssSelector("span.a-size-medium.a-color-base.a-text-normal");
    By productPrices = By.className("a-price-whole");

    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public Map<String, Integer> getProductDetails() {
        Map<String, Integer> productPriceMap = new HashMap<>();

        List<WebElement> products = driver.findElements(productNames);
        List<WebElement> prices = driver.findElements(productPrices);

        for (int i = 0; i < products.size(); i++) {
            String productName = products.get(i).getText();
            int price = 0;  // Default price is 0 if no price found
            if (i < prices.size() && !prices.get(i).getText().isEmpty()) {
                price = Integer.parseInt(prices.get(i).getText().replace(",", ""));
            }
            productPriceMap.put(productName, price);
        }
        return productPriceMap;
    }
}


