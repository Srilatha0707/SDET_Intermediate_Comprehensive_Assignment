package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class StepDefinition {

    WebDriver driver;

    @Given("the user is on the MakeMyTrip homepage")
    public void the_user_is_on_the_MakeMyTrip_homepage() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/");
    }

    @When("the user selects {string} and {string}")
    public void the_user_selects_Flights_and_Round_Trip(String menu, String tripType) throws InterruptedException {
    	WebElement close_popup = driver.findElement(By.xpath("//span[@class='commonModal__close']"));
        close_popup.click();
        Thread.sleep(2000);
        WebElement flightsTab = driver.findElement(By.xpath("(//span[text()='Flights'])[1]"));
        flightsTab.click();

        WebElement roundTripOption = driver.findElement(By.xpath("//ul[@class='fswTabs latoRegular darkGreyText ']/li[2]"));
        roundTripOption.click();
    }

    @When("the user enters {string} as the From location and {string} as the To location")
    public void the_user_enters_From_location_and_To_location(String fromLocation, String toLocation) {
        
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
    }

    @When("the user selects the departure date and return date")
    public void the_user_selects_the_departure_date_and_return_date() {
    	WebElement departureDateexact = driver.findElement(By.xpath("//div[@aria-label='Sat Aug 17 2024']"));
        departureDateexact.click();

        WebElement returnDate = driver.findElement(By.xpath("//div[@aria-label='Sat Aug 24 2024']")); 
        returnDate.click();
    }

    @When("the user clicks on the Search button")
    public void the_user_clicks_on_the_Search_button() {

        WebElement searchButton = driver.findElement(By.xpath("//a[contains(text(),'Search')]"));
        searchButton.click();
    }

    @Then("the search results page is displayed")
    public void the_search_results_page_is_displayed() {
    	boolean isSearchPageDisplayed = driver.findElement(By.xpath("//span[contains(text(),'Flights from')]")).isDisplayed();
    	if (isSearchPageDisplayed) {
            System.out.println("Search page is displayed as expected.");
        } else {
            System.out.println("Search page is NOT displayed as expected.");
        }
        driver.quit();
    }
}

