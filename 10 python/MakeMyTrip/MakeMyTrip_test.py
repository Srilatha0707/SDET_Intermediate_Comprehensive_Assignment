import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service as ChromeService
from webdriver_manager.chrome import ChromeDriverManager


@pytest.fixture
def driver():
    # Set up the WebDriver instance
    driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()))
    yield driver
    driver.quit()


def test_search_flights(driver):
    # Open the website
    driver.get("https://www.makemytrip.com/")

    # Maximize the window
    driver.maximize_window()

    WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//span[@class='commonModal__close']"))
    ).click()

    WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "(//span[text()='Flights'])[1]"))
    ).click()

    # Select Round Trip option
    round_trip_radio = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//ul[@class='fswTabs latoRegular darkGreyText ']/li[2]"))
    )
    round_trip_radio.click()

    # Enter FROM location
    from_input = driver.find_element(By.XPATH, "//div[@class='fsw ']/div/div/label[@for='fromCity']")
    from_input.click()
    from_input.send_keys("HYD")
    from_input_field = (WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//p[contains(text(),'Hyderabad, India')]"))
    ))
    from_input_field.click()


    # Enter TO location
    to_input = driver.find_element(By.XPATH, "//div[@class='fsw ']/div/div/label[@for='toCity']")
    to_input.click()
    to_input.send_keys("MAA")
    to_input_field = (WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//p[contains(text(),'Chennai, India')]"))
    ))
    to_input_field.click()

    # Select Departure Date
    # departure_date = driver.find_element(By.XPATH, "//span[text()='DEPARTURE']")
    # departure_date.click()
    driver.find_element(By.XPATH, "//div[@aria-label='Sat Aug 17 2024']").click()  # Example date

    # Select Return Date
    # return_date = driver.find_element(By.XPATH, "//span[text()='RETURN']")
    # return_date.click()
    driver.find_element(By.XPATH, "//div[@aria-label='Sat Aug 24 2024']").click()  # Example date

    # Click on Search Button
    search_button = driver.find_element(By.XPATH, "//a[contains(text(),'Search')]")
    search_button.click()

    # Verify the search page is displayed
    assert "flights-results" in driver.current_url


if __name__ == "__main__":
    pytest.main()
