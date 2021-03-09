package utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.Random;

public class GeneralAction {
    public static WebDriver drv;
    public static WebDriverWait wait;
    public static By panelHeadingLocator = By.className("panel-heading");
    List<WebElement> allProducts, allProductsItems;
    WebElement allProductsInCart;
    JavascriptExecutor jse = (JavascriptExecutor) drv;

    final String ADMIN_URL = "http://158.101.173.161/admin/";
    final String URL_MAIN = "http://158.101.173.161/";
    final String USERNAME = "testadmin";
    final String PASSWORD = "R8MRDAYT_test";
    final String COUNTRIES = ADMIN_URL + "?app=countries&doc=countries";


    @BeforeAll
    public static void start() {
        drv = new ChromeDriver();
        wait = new WebDriverWait(drv, 30);
    }

    @AfterAll
    public static void stop() {
        drv.quit();
    }

    public void logInToAdminPanel(){
        drv.get(ADMIN_URL);
        drv.findElement(By.cssSelector(".form-control[name=username]")).sendKeys(USERNAME);
        drv.findElement(By.cssSelector(".form-control[name=password]")).sendKeys(PASSWORD);
        drv.findElement(By.cssSelector(".btn.btn-default[name=login]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("box-apps-menu")));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openMainPage(){
        drv.get(URL_MAIN);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='box-popular-products']")));
    }

    public void addPopularProductToTheCart(){

        for (int counterOfProduct = 1; counterOfProduct < 4; counterOfProduct++){
            // Find and click on a random product
            allProducts = drv.findElements(By.xpath("//*[@id='box-popular-products']/div/article"));
            Random rand = new Random();
            int randomProduct = rand.nextInt(allProducts.size());

            //click on random popular product
            allProducts.get(randomProduct).click();

            //wait until button "Add to cart" is appeared
            wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@name, 'add_cart_product')]"))));

            //click on "Add to cart" button
            jse.executeScript("arguments[0].click()", drv.findElement(By.xpath("//button[contains(@name, 'add_cart_product')]")));

            //wait until badge of product quantity is displayed
            wait.until(ExpectedConditions.textToBePresentInElement(drv.findElement(By.cssSelector("div.badge.quantity")), String.valueOf(counterOfProduct)));
            openMainPage();
        }
    }

    public void openCart(){
        drv.get("http://158.101.173.161/checkout");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.items")));
    }

    public void openCountries(){
        drv.get(COUNTRIES);
        wait.until(ExpectedConditions.presenceOfElementLocated(panelHeadingLocator));
    }

    public void removeProductFromCart() {
        allProductsInCart = drv.findElement(By.xpath("//*[@id='box-checkout-cart']/ul"));
        allProductsItems = allProductsInCart.findElements(By.cssSelector("li"));
        for (int i = 1; i < allProductsItems.size(); i++) {
            drv.findElements(By.cssSelector("li.item [name='remove_cart_item']")).get(i).click();
            wait.until(ExpectedConditions.stalenessOf(allProductsInCart));
            waitForLoad(drv);
        }
    }

    public boolean isElementPresent(By element) {
        return drv.findElements(element).size() > 0;
    }

    void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
