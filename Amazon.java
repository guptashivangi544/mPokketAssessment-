import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Test
public class Amazon {

    public void TestSelenium(){

    	//Open Browser and Search for a product. Verify the list.
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\Downloads\\chromedriver_win32 (3).exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com");
        WebElement searchBox=driver.findElement(By.cssSelector(("#twotabsearchtextbox")));
        searchBox.sendKeys("Book");
        WebElement searchButton=driver.findElement(By.cssSelector((".nav-search-submit")));
        searchButton.click();
        
        //Add a product to the cart. Verify that the added product is in the cart.
        driver.findElement(By.cssSelector(("span[class='celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1'] span[class='a-size-medium a-color-base a-text-normal']")));
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-button]"));
        driver.findElement(By.xpath("//*[@id=\"nav-cart-count\"]"));
      
        //Increase the number of items in the cart to 10. Verify that the total price changed.		
        driver.findElement(By.xpath("//*[@id=\"a-autoid-0-announce\"]/span[1]"));
        driver.findElement(By.xpath("//*[@id=\"quantity_10\"]"));
        driver.findElement(By.xpath("//*[@id=\"sc-item-C3fe35621-ee56-47b7-b2dc-c612ab190742\"]/div[4]/div/div[1]/div/div/div[2]/div[1]/span[1]/span/input")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id=\"a-autoid-1-announce\"]")); 
        String expectedshippingprice = "4,040.00"; 
        String actualshipingprice = driver.findElement(By.xpath("//*[@id=\"sc-subtotal-amount-activecart\"]/span")).getText(); 
        Alert.assertEquals("Verify Shipping Price", expectedshippingprice, actualshipingprice);
        
        //Remove a product from the cart. Verify the change.
        driver.findElement(By.xpath("//*[@id=\"sc-item-C3fe35621-ee56-47b7-b2dc-c612ab190742\"]/div[4]/div/div[1]/div/div/div[2]/div[1]/span[1]/span/input")).sendKeys(Keys.chord(Keys.CONTROL, "a"),9);
        driver.findElement(By.xpath("//*[@id=\"a-autoid-0-announce\"]"));
        String expectedshippingprice = "3,636.00"; 
        String actualshipingprice = driver.findElement(By.xpath("//*[@id=\"sc-subtotal-label-activecart\"]")).getText(); 
        Alert.assertEquals("Verify Shipping Price", expectedshippingprice, actualshipingprice);
        
        driver.quit();
        
    }
}