package HW4;

import HW4.multilayer.Application;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;

public class CommonCartTest {
    public static Application app;

    @BeforeAll
    public static void startApplication() {
        app = new Application();
    }

    @AfterAll
    public static void stopApplication() {
        app.closeApp();
    }

}
