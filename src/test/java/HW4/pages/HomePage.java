package HW4.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page{

    public HomePage(WebDriver driver){
        super(driver);
    }
    public HomePage open(){
        driver.get(URL_MAIN);
        return this;
    }



}
