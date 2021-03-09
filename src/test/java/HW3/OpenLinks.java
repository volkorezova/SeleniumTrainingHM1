package HW3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.GeneralAction;

import java.util.List;

public class OpenLinks extends GeneralAction {
    String originalWindow;
    List<WebElement> externalLinksList;

    @Test
    void openWindowsAndClose(){
        By externalLinks = By.cssSelector("i.fa.fa-external-link");

        logInToAdminPanel();
        Assertions.assertTrue(drv.findElement(By.cssSelector(".center-block.img-responsive")).isDisplayed());

        openCountries();
        Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");

        drv.findElement(By.xpath("//a[text()=' Add New Country']")).click();
        Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");

        originalWindow = drv.getWindowHandle();
        externalLinksList = drv.findElements(externalLinks);

        //open windows
        for (int i = 0; i < externalLinksList.size(); i++){
            externalLinksList.get(i).click();
        }

        //close all tabs except first window (Countries)
        for (String handl : drv.getWindowHandles()){
            if (!handl.equals(originalWindow)) {
                drv.switchTo().window(handl);
                drv.close();
            }
        }

        //switch to the original window
        drv.switchTo().window(originalWindow);
        Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");
    }

}
