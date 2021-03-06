package HW1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.GeneralAction;

import java.util.List;

public class PresenceTest extends GeneralAction {
    List<WebElement> fullMenuItems;
    List<WebElement> separateSubMenuItem;

    @Test
    void isAppearanceMenuPresent() {
        logInToAdminPanel();

        //verify that we are logged in
        Assertions.assertTrue(drv.findElement(By.cssSelector(".center-block.img-responsive")).isDisplayed());

        //get full menu items from DOM
        fullMenuItems = drv.findElements(By.cssSelector("ul#box-apps-menu li.app"));

        //move through each main item to subitems
        for (int i = 0; i < fullMenuItems.size(); i++) {
            fullMenuItems.get(i).click();

            //get new menu items list after previous click on element
            fullMenuItems = drv.findElements(By.cssSelector("ul#box-apps-menu li.app"));

            //try to get submenus for each menu
            try {
                separateSubMenuItem = fullMenuItems.get(i).findElement(By.cssSelector(".docs")).findElements(By.cssSelector("li.doc"));
                for (int j = 0; j < separateSubMenuItem.size(); j++) {
                    //click on submenu item
                    separateSubMenuItem.get(j).click();

                    //get new menu items list after previous click on element
                    fullMenuItems = drv.findElements(By.cssSelector("ul#box-apps-menu li.app"));

                    //reload submenu items after each click
                    separateSubMenuItem = fullMenuItems.get(i).findElement(By.cssSelector(".docs")).findElements(By.cssSelector("li.doc"));

                    Assertions.assertTrue(drv.findElement(By.cssSelector("div.panel-heading")).isDisplayed());
                }
            } catch (NoSuchElementException e) {
                //if menu item does not have sub menu items
                Assertions.assertTrue(drv.findElement(By.cssSelector("div.panel-heading")).isDisplayed());
            }
        }
    }
}

