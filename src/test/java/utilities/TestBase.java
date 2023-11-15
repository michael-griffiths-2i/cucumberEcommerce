package utilities;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

public class TestBase {

    protected WebDriver driver;
    @After
    void teardown(){
       System.out.println("driver has quit");
       driver.quit();
    }

}
