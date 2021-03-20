package HW4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends Page{
    @FindBy(css = "ul.items")
    public WebElement tableProducts;

    @FindBy(xpath = "//*[@id='box-checkout-cart']/ul")
    public WebElement boxCheckedOutCartUl;

    @FindBy(css = "li")
    public WebElement boxCheckedOutLI;

    @FindBy(css = "li.item [name='remove_cart_item']")
    public WebElement removeCartItem;

    public CartPage (WebDriver driver) {
        super(driver);
    }

    public CartPage openCart(){
        driver.get(URL_MAIN + "checkout");
        wait.until(ExpectedConditions.presenceOfElementLocated((By) tableProducts));
        return this;
    }
}
