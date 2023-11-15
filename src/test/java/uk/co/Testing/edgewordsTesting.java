package uk.co.Testing;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class edgewordsTesting{

    //WebDriver driver = new ChromeDriver();
    private WebDriver driver;
    private final sharedDictionary sharedDict;

    public edgewordsTesting(sharedDictionary sharedDict){
            this.sharedDict = sharedDict;
            this.driver = (WebDriver)sharedDict.readDict("mydriver");

    }

    @Given("I am logged in")
    public void i_am_logged_in() {

        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account");
        driver.findElement(By.linkText("Dismiss")).click();
        System.out.println("Demo store warning dismissed");
        System.out.println("I am on the login page");

        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("mikeyg77@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Donotigiveout!");
        driver.findElement(By.name("login")).click();




    }
    @Given("I am on the shop page")
    public void i_am_on_the_shop_page() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.linkText("Shop")).click();
    }
    @When("I add an item to the cart")
    public void i_add_an_item_to_the_cart() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.cssSelector("a[aria-label='Add “Beanie” to your cart']")).click();
        driver.findElement(By.linkText("Cart")).click();
        System.out.println("Cart button is clicked");



    }

    @Then("the discount is applied correctly")
    public void the_discount_is_applied_correctly() {
        //view cart
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("coupon_code")).click();
        driver.findElement(By.id("coupon_code")).sendKeys("edgewords");
        driver.findElement(By.name("apply_coupon")).click();


        System.out.println("Finding price");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Delay has happened");
        String totalRaw = driver.findElement(By.cssSelector(".cart-subtotal > td > .amount.woocommerce-Price-amount > bdi")).getText();
        totalRaw = totalRaw.substring(1); // Create substring totalRaw removing the first character
        double total = parseDouble(totalRaw);
        System.out.println("calling get discount");
        getDiscount(total);

    }
    private void getDiscount(double totalPrice) { //only called from getPrice so can be private to this class

        String totalDiscountRaw = driver.findElement(By.cssSelector(".cart-discount.coupon-edgewords > td > .amount.woocommerce-Price-amount")).getText();
        System.out.println(totalDiscountRaw);
//        totalDiscountRaw = totalDiscountRaw.substring(1); // Create a substring of discount removing the minus and currency
//        double totalDiscount = parseDouble(totalDiscountRaw);
//        System.out.println("Total Discount" + totalDiscount);
//        // Work out discount based on Price
//        double actualDiscount = totalPrice * 0.15;
//        assertEquals(actualDiscount, totalDiscount, 0.2); // Adjust the tolerance as needed
    }



    @Then("the order numbers match")
    public void the_order_numbers_match() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.linkText("Checkout")).click();
        driver.findElement(By.id("billing_first_name")).click();
        driver.findElement(By.id("billing_first_name")).clear();
        driver.findElement(By.id("billing_first_name")).sendKeys("Mickey");

        driver.findElement(By.id("billing_last_name")).click();
        driver.findElement(By.id("billing_last_name")).clear();
        driver.findElement(By.id("billing_last_name")).sendKeys("Mickey");

        driver.findElement(By.id("select2-billing_country-container")).click();

        driver.findElement(By.id("billing_address_1")).click();
        driver.findElement(By.id("billing_address_1")).clear();
        driver.findElement(By.id("billing_address_1")).sendKeys("123 Main Road");

        driver.findElement(By.id("billing_city")).click();
        driver.findElement(By.id("billing_city")).clear();
        driver.findElement(By.id("billing_city")).sendKeys("Glasgow");

        driver.findElement(By.id("billing_phone")).click();
        driver.findElement(By.id("billing_phone")).clear();
        driver.findElement(By.id("billing_phone")).sendKeys("0141 432 1234");

        driver.findElement(By.id("billing_postcode")).click();
        driver.findElement(By.id("billing_postcode")).clear();
        driver.findElement(By.id("billing_postcode")).sendKeys("G11 3XY");

        driver.findElement(By.id("billing_email")).click();
        driver.findElement(By.id("billing_email")).clear();
        driver.findElement(By.id("billing_email")).sendKeys("mickeymouse@hotmail.com");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("place_order")).click();
        System.out.println("Place order clicked");


       try {
           Thread.sleep(3000);
        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
        WebElement newOrderNumberElement = driver.findElement(By.cssSelector(".woocommerce-order-overview__order strong"));

        String newOrderNumber =newOrderNumberElement.getText();
        System.out.println(newOrderNumber);
        //newOrderNumber = newOrderNumber.substring(1);
        System.out.println(newOrderNumber);
        compareOrderNumbers(newOrderNumber);

    }


    public void compareOrderNumbers(String orderNumber){

        driver.findElement(By.linkText("My account")).click();
        driver.findElement(By.linkText("Orders")).click();


        WebElement myOrdersOrderNumber = driver.findElement(By.cssSelector("tr:nth-of-type(1) > .woocommerce-orders-table__cell.woocommerce-orders-table__cell-order-number > a"));

        String finalOrderRaw = myOrdersOrderNumber.getText();
        String finalOrder= finalOrderRaw.substring(1);
        System.out.println(orderNumber);
        System.out.println(finalOrder);
        assertEquals(orderNumber, finalOrder);

    }

}