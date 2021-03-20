package HW4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public String URL_MAIN = "http://158.101.173.161/";

    public Page(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }
}
