import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

public class PresenceTest extends GeneralAction{

    @Test
    void isAppearanceMenuPresent(){
        logInToAdminPanel();
        Assertions.assertTrue(drv.findElement(By.cssSelector(".center-block.img-responsive")).isDisplayed());
        drv.findElement(By.xpath("//*[@id='box-apps-menu']/li[1]/a/span[2]")).click();

        drv.findElement(By.cssSelector("#box-apps-menu > li.app.selected > ul > li.doc.selected > a > span")).click();
        isElementPresent(drv,By.xpath("//div[text()[contains(.,'Template')]]"));

        drv.findElement(By.cssSelector("#box-apps-menu > li.app.selected > ul > li.doc.selected > a > span")).click();
        isElementPresent(drv,By.xpath("//div[text()[contains(.,'Logotype')]]"));



    }

//    @Test
//    void isCatalogMenuPresent(){
//
//    }
//
//    @Test
//    void isCountriesMenuPresent(){
//
//    }
//    @Test
//    void isCurrenciesMenuPresent(){
//
//    }
//
//    @Test
//    void isCustomersMenuPresent(){
//
//    }
//
//    @Test
//    void isGeoZonesMenuPresent(){
//
//    }
//
//    @Test
//    void isLangMenuPresent(){
//    }
//
//    @Test
//    void isModulesMenuPresent(){
//
//    }
//
//    @Test
//    void isOrdersMenuPresent(){
//
//    }
//
//    @Test
//    void isPagesMenuPresent(){
//
//    }
//
//    @Test
//    void isReportsMenuPresent(){
//
//    }
//
//    @Test
//    void isSettingsMenuPresent(){
//
//    }
//
//    @Test
//    void isSlidesMenuPresent(){
//
//    }
//
//    @Test
//    void isTaxMenuPresent(){
//
//    }
//
//    @Test
//    void isTranslationMenuPresent(){
//
//    }
//
//    @Test
//    void isUsersMenuPresent(){
//
//    }
//
//    @Test
//    void isVQMenuPresent(){
//
//    }
}

