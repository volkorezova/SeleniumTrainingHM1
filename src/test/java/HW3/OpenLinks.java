package HW3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OpenLinks extends GeneralActionsWithListener {
    final int INCREMENT_OF_OPENED_WINDOWS = 1;
    String originalWindow;
    List<WebElement> externalLinksList;
    Set<String> windowsBeforeNewOpened;
    ArrayList<String> windowsAfterNewOpened;

    @Test
    void openWindowsAndClose(){
        By externalLinks = By.cssSelector("i.fa.fa-external-link");

        logInToAdminPanel();
        Assertions.assertTrue(edr.findElement(By.cssSelector(".center-block.img-responsive")).isDisplayed());

        openCountries();
        Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");

        edr.findElement(By.xpath("//a[text()=' Add New Country']")).click();
        Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");

        originalWindow = edr.getWindowHandle();
        externalLinksList = edr.findElements(externalLinks);
        windowsBeforeNewOpened = edr.getWindowHandles();

        //open windows
        for (int i = 0; i < externalLinksList.size(); i++){
            externalLinksList.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(windowsBeforeNewOpened.size() + INCREMENT_OF_OPENED_WINDOWS));
            Assertions.assertEquals(windowsBeforeNewOpened.size() + INCREMENT_OF_OPENED_WINDOWS, edr.getWindowHandles().size());
            windowsAfterNewOpened = new ArrayList<String>(edr.getWindowHandles());
            edr.switchTo().window(windowsAfterNewOpened.get(INCREMENT_OF_OPENED_WINDOWS));
            edr.close();

            //switch to the original window
            edr.switchTo().window(originalWindow);
        }
        Assertions.assertTrue(isElementPresent(panelHeadingLocator), "Heading not found");
    }

}
