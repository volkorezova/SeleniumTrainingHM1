package utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class GeneralAction {
    public static WebDriver drv;
    final String URL = "http://158.101.173.161/admin/";
    final String URL_MAIN = "http://158.101.173.161/";
    final String USERNAME = "testadmin";
    final String PASSWORD = "R8MRDAYT_test";

    @BeforeAll
    public static void start() {
        drv = new ChromeDriver();
        drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void stop() {
        drv.quit();
    }

    public void logInToAdminPanel(){
        drv.get(URL);
        drv.findElement(By.cssSelector(".form-control[name=username]")).sendKeys(USERNAME);
        drv.findElement(By.cssSelector(".form-control[name=password]")).sendKeys(PASSWORD);
        drv.findElement(By.cssSelector(".btn.btn-default[name=login]")).click();
    }

    public void openMainPage(){
        drv.get(URL_MAIN);
    }

    public void openPopularProduct(){

    }

    public void addPopularProductToTheCart(){

    }

    public void openCart(){

    }

    public void removeProductFromCart(){

    }
}
