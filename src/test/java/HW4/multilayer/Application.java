package HW4.multilayer;

import HW4.pages.CartPage;
import HW4.pages.HomePage;
import HW4.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class Application {
    private static WebDriver driver;
    protected WebDriverWait wait;
    private final HomePage homePage;
    private final CartPage cartPage;
    private final ProductPage productPage;

    List<WebElement> allProducts, allProductsItems;
    WebElement allProductsInCart;
    JavascriptExecutor jse = (JavascriptExecutor) driver;

    public Application() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void openHomePage(){
        homePage.open();
    }

    public void closeApp() {
        driver.quit();
    }

    public void addProd(){
        for (int counterOfProduct = 1; counterOfProduct < 4; counterOfProduct++){
            // Find and click on a random product
            allProducts = driver.findElements(By.xpath("//*[@id='box-popular-products']/div/article"));
            Random rand = new Random();
            int randomProduct = rand.nextInt(allProducts.size());

            //click on random popular product
            allProducts.get(randomProduct).click();

            //wait until button "Add to cart" is appeared
            wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@name, 'add_cart_product')]"))));

            //click on "Add to cart" button
            jse.executeScript("arguments[0].click()", driver.findElement((By.xpath(String.valueOf(productPage.addCartProduct)))));

            //wait until badge of product quantity is displayed
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector(String.valueOf(productPage.badgeCount))), String.valueOf(counterOfProduct)));
            homePage.open();
        }
    }

    public void openCartPage(){
        cartPage.openCart();
    }

    public void removeProductFromCart() {
        allProductsInCart = driver.findElement((By) cartPage.boxCheckedOutCartUl);
        allProductsItems = allProductsInCart.findElements((By) cartPage.boxCheckedOutLI);
        for (int i = 1; i < allProductsItems.size(); i++) {
            driver.findElements((By) cartPage.removeCartItem).get(i).click();
            wait.until(ExpectedConditions.stalenessOf(allProductsInCart));
            waitForLoad(driver);
        }
    }

    void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public boolean isElementPresent(By element) {
        return driver.findElements(element).size() > 0;
    }
}
