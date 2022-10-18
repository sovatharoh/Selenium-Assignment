/**
 *
 * Name: John Smith (( Update with your name here ))
 * Student ID: s45045012  (( Update with your ID))
 *
 * [OPTIONAL: add any notes or comments here about the code]
 */
package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class JPetstoreTest_S3783867 {
    WebDriver myDriver;

    @Test
    @Order(2)
    @DisplayName("Check Price for Adult Male Chihuahua at product page")
    void checkChihuahua1() {

        String chihuahuaURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-26";
        myDriver.get(chihuahuaURL);

        // WebElement class represents an html tag such as <p> , <a> , <img> <td> for tables etc. even forms
        // ... and this class has methods for you to check content and attributes
        // see the getText() method below to read the text between the opening/closing tags.
        // see the getAttribute("href") method which is used by toString() to read the url

        List<WebElement> lweA = myDriver.findElements(By.tagName("a"));
        System.out.println("Printing text from <a> elements:");
        for (WebElement wea : lweA){
            System.out.print(wea.getText() + ",");
        }
        System.out.println("Printing href attribute values from <a> elements:");
        for (WebElement wea : lweA){
            System.out.println(wea.getAttribute("href") + ",");
        }

        // for this web page (very 1990s) a lot of content are in tables so <td> is a standard cell in the table

        List<WebElement> lweTD = myDriver.findElements(By.tagName("td"));
        System.out.println("Printing text from <td> elements:");
        for (WebElement wea : lweTD){ // each of this are td WebElements
            System.out.print(wea.getText() + ", ");
        }

        // Thread.sleep() is not normally encouraged, but is a quick way to pause browser
        // When you are processing a webpage with Selenium, if the webpage doesn't load fast enough
        // an exception can be thrown unless you put in some wait time.
        // There are more official ways to handle wait time - we will look at that next week.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Write the tests for these below using JUnit assertEquals and the findElements method
        fail("Task 11.1 : Check that the price is $125.50");
        fail("Task 11.2 : Check that the product name is correct (Adult Male Chihuahua) for this product page");
        fail("Task 11.3 : Check that the adult male chihuahua is in stock. ( > 0 ) - or perhaps this should be your first check before 11.1!?");

    }

    @Test
    @Order(3)
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
    @Order(4)
    @DisplayName("More examples with Xpath")
    void checkChihuahua2(){

        String chihuahuaURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-26";
        myDriver.get(chihuahuaURL);

        /**
         * If you have time you can look further with this below.
         * Xpath has powerful functionality to find elements and values
         * Xpath is a bit like regular expressions for html elements (DOM tree)
         */

        WebElement we; // to store the result of our search. Of course you can also use a List of WebElements if you expect a return of many results

        // In this example we are searching only for the first td element, which has exact text value - '$125.50'
        we = myDriver.findElement(By.xpath("//td[text()='$125.50']"));

        // In this example we are searching only for any element, which contains a particular string value
        // (a partial match, like a substring match)
        // Here we are using * to check all elements for their enclosed value
        // <p>Like the value here</p>
        we = myDriver.findElement(By.xpath("//*[contains(text(),'$125')]"));

        System.out.println("we.toString(): " + we.toString()); // see what it looks like toString()
        System.out.println("we.getText(): " + we.getText()); // see what the text is

        /**
         * we.toString(): [[FirefoxDriver: firefox on WINDOWS (955939a2-3c3d-4cf3-b531-05dc9df88c99)] -> xpath: //*[contains(text(),'$125')]]
         * we.getText(): $125.50
         *
         */

    }

    @Test
    @Order(5)
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
        List<WebElement> lweTD2 = myDriver.findElements(By.tagName("td"));
        System.out.println("Printing text from <td> elements:");
        int i = 1;
        for (WebElement wea : lweTD2){ // each of this are td WebElements
            System.out.println(wea.getText());
            System.out.println(i++);
        }
        System.out.println("VALUE");
        System.out.println(myDriver.findElement(By.name("EST-21")).getAttribute("value"));
        System.out.println(myDriver.findElement(By.name("EST-17")).getAttribute("value"));
        System.out.println(myDriver.findElement(By.name("EST-13")).getAttribute("value"));
        fail("TODO");

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