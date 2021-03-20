package HW4;

import HW4.multilayer.Application;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


public class CartTest extends CommonCartTest{
//    static Application app;
//
//    @BeforeAll
//    public static void startApplication() {
//        app = new Application();
//    }
//
//    @AfterAll
//    public static void stopApplication() {
//        app.closeApp();
//    }

    @Test
    void addRemProductsToFromCart() {
        app.openHomePage();
        app.addProd();
        app.openCartPage();
        app.removeProductFromCart();

       Assertions.assertTrue(app.isElementPresent(By.cssSelector(".btn-default")), "Cart is not found");
    }
}
