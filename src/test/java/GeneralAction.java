import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class GeneralAction {
    public static WebDriver drv;

    @BeforeAll
    public static void start() {
        drv = new ChromeDriver();
        drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void stop() {
        drv.quit();
    }

    public boolean isElementPresent(WebDriver drv, By locator) {
        return  drv.findElements(locator).size()>0;
    }

    public void logInToAdminPanel(){
        drv.get("http://158.101.173.161/admin/");
        drv.findElement(By.cssSelector(".form-control[name=username]")).sendKeys("testadmin");
        drv.findElement(By.cssSelector(".form-control[name=password]")).sendKeys("R8MRDAYT_test");
        drv.findElement(By.cssSelector(".btn.btn-default[name=login]")).click();
    }
}
