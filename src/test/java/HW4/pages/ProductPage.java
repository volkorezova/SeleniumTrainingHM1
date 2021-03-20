package HW4.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends Page{
    @FindBy(xpath = "//*[@id='box-popular-products']/div/article")
    public WebElement popularProdOnHomePage;

    @FindBy(xpath = "//button[contains(@name, 'add_cart_product')]")
    public WebElement addCartProduct;

    @FindBy(css = "div.badge.quantity")
    public WebElement badgeCount;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

}
