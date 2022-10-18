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

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class TryBookingTest_S3783867 {
    WebDriver myDriver;

    @Test
    @Order(1)
    @DisplayName("Try Booking check 12 or more unique Events near me")
    void checkEventsNearMe() {
        String url = "https://www.trybooking.com/book/search";
        myDriver.get(url);


        WebElement nearbyEventsContainer = myDriver.findElement(By.xpath("//*[@id=\"nearby-events-container\"]"));
        List<WebElement> nearbyEvents = nearbyEventsContainer.findElements(By.xpath("./child::*"));

        //Add to hashset, that stores unique values which means there can not be any duplicate events.
        HashSet nearbyEventIds = new HashSet();
        for ( WebElement i : nearbyEvents) {
            nearbyEventIds.add(i.getAttribute("id"));
        }
        assertTrue(nearbyEventIds.size() >= 12);
    }

    @Test
    @Order(2)
    @DisplayName("Try Booking an event")
    void checkCreateBooking() {
        String url = "https://www.trybooking.com/CDKGX";
        myDriver.get(url);
        List<WebElement> lweA = myDriver.findElements(By.tagName("button"));
        System.out.println(lweA);

        fail("TODO");
    }
    @Test
    // @Disabled
    @Order(0)
    @DisplayName("Sanity test only")
     void sanityTest(){
        // When this passes I know I have the webdriver and Junit set up correctly
        String url = "https://www.trybooking.com";
        myDriver.get(url);
        assertEquals("Event Ticketing & Booking Platform | TryBooking Australia", myDriver.getTitle());
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