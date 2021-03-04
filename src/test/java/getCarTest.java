import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class getCarTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.gettacar.com/");
    }

    @Test
    public void doSometh() {
        WebElement chooseCarButton = driver.findElement(By.xpath("//*[@id=\"section-hero\"]/div/div[1]/div[2]/div/span/a[1]"));
        chooseCarButton.click();
        WebElement selectBodyType = driver.findElement(By.xpath("//*[@id=\"catalog-container\"]/div[2]/div[1]/div/div[2]/div[1]/span/h5"));
        selectBodyType.click();
        WebElement PickUpButton = driver.findElement(By.xpath("//*[@id=\"catalog-container\"]/div[2]/div[1]/div/div[2]/div[2]/div/div[6]"));
        PickUpButton.click();
        List<WebElement> carsList = driver.findElements(By.className("car-card_car-card__19FY8"));
        carsList.get(0).click();
        WebElement carPrice = driver.findElement(By.className("price-box-details_carPrice__2OTPp"));
        String priceText = carPrice.getText();
        System.out.println(priceText);
        double price = Double.parseDouble(priceText.substring(1).replaceAll(",", ""));
        System.out.println(price);

        if (price >= 25000) {
            System.out.println("High");
        } else {
            System.out.println("Low");
        }
    }

    @AfterMethod(enabled = false)
    public void teardown() {
        driver.quit();
    }
}