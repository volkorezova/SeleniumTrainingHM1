package HW3;

import HW3.listener.Listener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneralActionsWithListener {
    final String ADMIN_URL = "http://158.101.173.161/admin/";
    final String URL_MAIN = "http://158.101.173.161/";
    final String USERNAME = "testadmin";
    final String PASSWORD = "R8MRDAYT_test";
    final String COUNTRIES = ADMIN_URL + "?app=countries&doc=countries";

    By panelHeadingLocator = By.className("panel-heading");

    EventFiringWebDriver edr;
    WebDriverWait wait;

    @BeforeEach
    public void start() {
        edr = new EventFiringWebDriver(new ChromeDriver());
        edr.register(new Listener());
        wait = new WebDriverWait(edr, 5);
    }

    @AfterEach
    public void stop() {
        edr.quit();
    }

    void logInToAdminPanel(){
        edr.get(ADMIN_URL);
        edr.findElement(By.cssSelector(".form-control[name=username]")).sendKeys(USERNAME);
        edr.findElement(By.cssSelector(".form-control[name=password]")).sendKeys(PASSWORD);
        edr.findElement(By.cssSelector(".btn.btn-default[name=login]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("box-apps-menu")));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void openCountries(){
        edr.get(COUNTRIES);
        wait.until(ExpectedConditions.presenceOfElementLocated(panelHeadingLocator));
    }

    boolean isElementPresent(By element) {
        return edr.findElements(element).size() > 0;
    }
}
