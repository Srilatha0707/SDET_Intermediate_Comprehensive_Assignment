package testng;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Both_MakeMytrip {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/");
    }
    
    @Test
    public void Makemytrip() throws InterruptedException {

        WebElement close_popup = driver.findElement(By.xpath("//span[@class='commonModal__close']"));
        close_popup.click();
        Thread.sleep(2000);
        WebElement flightsTab = driver.findElement(By.xpath("(//span[text()='Flights'])[1]"));
        flightsTab.click();

        WebElement roundTripOption = driver.findElement(By.xpath("//ul[@class='fswTabs latoRegular darkGreyText ']/li[2]"));
        roundTripOption.click();

        WebElement fromInput = driver.findElement(By.xpath("//div[@class='fsw ']/div/div/label[@for='fromCity']"));
        fromInput.click();
        fromInput.sendKeys("HYD");
        WebElement fromCityOption = driver.findElement(By.xpath("//p[contains(text(),'Hyderabad, India')]"));
        fromCityOption.click();

        WebElement toInput = driver.findElement(By.xpath("//div[@class='fsw ']/div/div/label[@for='toCity']"));
        toInput.click();
        toInput.sendKeys("MAA");
        WebElement toCityOption = driver.findElement(By.xpath("//p[contains(text(),'Chennai, India')]"));
        toCityOption.click();

        WebElement departureDateexact = driver.findElement(By.xpath("//div[@aria-label='Sat Aug 17 2024']"));
        departureDateexact.click();

        WebElement returnDate = driver.findElement(By.xpath("//div[@aria-label='Sat Aug 24 2024']")); 
        returnDate.click();

        WebElement searchButton = driver.findElement(By.xpath("//a[contains(text(),'Search')]"));
        searchButton.click();
        
        boolean isSearchPageDisplayed = driver.findElement(By.xpath("//span[contains(text(),'Flights from')]")).isDisplayed();
        if (isSearchPageDisplayed) {
            System.out.println("Search page is displayed as expected.");
        } else {
            System.out.println("Search page is NOT displayed as expected.");
        }

    }

    @AfterMethod
    public void tearDown() {
    	if(driver != null) {
        driver.quit();
    }
    }
}

