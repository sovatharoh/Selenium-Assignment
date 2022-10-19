/**
 *
 * Name: Sovatharo Huy
 * Student ID: s3783867  (( Update with your ID))
 *
 *
 */
package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class zJPetstoreTest_S3783867 {
    WebDriver myDriver;

    @Test
    @Order(1)
    @DisplayName("Check the pet name, price and check if there is stock for one pet of your choice.")
    void checkFemaleGoldFish() throws Exception {
        String femaleGoldFishURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-21";
        myDriver.get(femaleGoldFishURL);

        List<WebElement> lweTD = myDriver.findElements(By.tagName("td"));

        //Check that the product name is correct.
        assertEquals("Adult Female Goldfish", lweTD.get(2).getText());

        //Check that the pet is in stock ( >0 in stock). Throws an exception if product is back ordered.
        final String stockString = lweTD.get(4).getText();
        if(stockString.equals("Back ordered.")) {
            throw new Exception("Not in stock");
        } else {
            String stockNumber = "";
            for(int i = 0; i < stockString.length(); i++){
                if(Character.isDigit(stockString.charAt(i))){
                    stockNumber = stockNumber + stockString.charAt(i);
                } else {
                    i = stockString.length();
                }
            }
            assertTrue(Integer.parseInt(stockNumber) > 0);
        }

        // Checks goldfish price
        String goldFishPrice = lweTD.get(5).getText();
        assertEquals(goldFishPrice, "$5.29");
        /**
         * You will be asked to submit this for your Assignment 3 .
         */
    }

    @Test
    @Order(2)
    @DisplayName("Start a menagerie! Select a specific fish, specific cat, and a third pet (they will have a unique item ID)." +
            " Add 3 multiples of the fish, 2 multiples of the cat, and one of the third pet to the cart. Check the subtotal matches the expected price")
    void startAMenagerie() throws Exception {

        HashMap<String, String> petsURL = new HashMap<String, String>();
        //Fish URL
        petsURL.put("Fish", "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-21");
        //Cat URL
        petsURL.put("Cat", "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-17");
        //Iguana URL
        petsURL.put("Iguana", "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-13");

        //Fish View Item Page
        for(int i = 0; i < 3; i++){
            myDriver.get(petsURL.get("Fish"));
            List<WebElement> lweA = myDriver.findElements(By.tagName("a"));
            //Click add to cart
            List<WebElement> lweTD = myDriver.findElements(By.tagName("td"));
            final String stockString = lweTD.get(4).getText();
            if(stockString.equals("Back ordered.")) {
                throw new Exception("Not in stock");
            } else {
                lweA.get(10).click();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i = 0; i < 2; i++){
            myDriver.get(petsURL.get("Cat"));
            List<WebElement> lweA = myDriver.findElements(By.tagName("a"));
            //Click add to cart
            List<WebElement> lweTD = myDriver.findElements(By.tagName("td"));
            final String stockString = lweTD.get(4).getText();
            if(stockString.equals("Back ordered.")) {
                throw new Exception("Not in stock");
            } else {
                lweA.get(10).click();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        myDriver.get(petsURL.get("Iguana"));
        List<WebElement> lweA = myDriver.findElements(By.tagName("a"));
        //Click add to cart
        List<WebElement> lweTD = myDriver.findElements(By.tagName("td"));
        final String stockString = lweTD.get(4).getText();
        if(stockString.equals("Back ordered.")) {
            throw new Exception("Not in stock");
        } else {
            lweA.get(10).click();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<WebElement> tdCheckout = myDriver.findElements(By.tagName("td"));
        if(tdCheckout.get(3).getText().equals("false") || tdCheckout.get(11).equals("false") || tdCheckout.get(19).equals("false")) {
            throw new Exception("No stock in product");
        } else {
            //CHECK IF Quanity is correct for each pet
            assertEquals("3", myDriver.findElement(By.name("EST-21")).getAttribute("value"));
            assertEquals("2", myDriver.findElement(By.name("EST-17")).getAttribute("value"));
            assertEquals("1", myDriver.findElement(By.name("EST-13")).getAttribute("value"));
            assertEquals("Sub Total: $221.37", tdCheckout.get(24).getText());
        }
    }

    @Test
    // @Disabled
    @Order(0)
    @DisplayName("Sanity test only")
     void sanityTest1(){
        // When this passes I know I have the webdriver and Junit set up correctly
        String petStoreURL = "https://petstore.octoperf.com";
        myDriver.get(petStoreURL);
        // do any sanity check here.
        assertEquals("JPetStore Demo", myDriver.getTitle());
    }

    @BeforeEach
    void setUp() {
        SeleniumDriverFactory sdf =new SeleniumDriverFactory ();
        this.myDriver = sdf.getDriver();
    }

    @AfterEach
    void tearDown() {
        //myDriver.close();
        myDriver.quit();
    }
}