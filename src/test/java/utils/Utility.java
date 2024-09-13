package utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Utility {

    // Take screenshot
    public static void takeScreenshot(WebDriver driver, String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("./screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            System.out.println("Error while taking screenshot: " + e.getMessage());
        }
    }

    // Write product details into a JSON file
    public static void writeToJSON(Map<String, Integer> productPriceMap, String fileName) {
        JSONObject json = new JSONObject(productPriceMap);
        try (FileWriter file = new FileWriter("./output/" + fileName + ".json")) {
            file.write(json.toString(4));
        } catch (IOException e) {
            System.out.println("Error while writing to JSON: " + e.getMessage());
        }
    }
}



