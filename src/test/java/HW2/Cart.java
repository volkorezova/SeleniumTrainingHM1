package HW2;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utils.GeneralAction;
import org.junit.jupiter.api.Test;

public class Cart extends GeneralAction {

    @Test
    void addRemProductsToFromCart() {
        openMainPage();
        addPopularProductToTheCart();
        openCart();
        removeProductFromCart();

        Assertions.assertTrue(isElementPresent(By.cssSelector(".btn-default")), "Cart is not found");
    }
}