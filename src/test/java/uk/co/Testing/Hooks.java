package uk.co.Testing;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    private static WebDriver driver;
    private final sharedDictionary sharedDict;

    public Hooks(sharedDictionary sharedDict){
        this.sharedDict = sharedDict;

    }
    /*static belongs to the Hooks class and can be
    accessed by any other method
    */
    public static WebDriver getDriver(){
        return driver;
    }


    @Before
    public void setUp(){
        //assigns a chromedriver to the driver variable
        driver = new ChromeDriver();
        sharedDict.addDict("mydriver",driver);
    }

    @After
    public void tearDown(){
        System.out.println("driver has quit");
        driver.quit();
    }
}
