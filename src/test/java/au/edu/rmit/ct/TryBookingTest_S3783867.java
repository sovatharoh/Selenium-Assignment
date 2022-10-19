/**
 *
 * Name: Sovatharo Huy
 * Student ID: s3783867
 *
 * Could not select input for the <select></select> tags directly because the display was set to none. Instead, simulated user clicks on
 * value. Could have simulated scroll down to select the wanted, but chose to select the option that was immediately seen.
 * Had to add sleep thread between each select click, implicit wait was unreliable.
 * The longer implicit waits, was to wait for the page to load which often took a long time.
 */
package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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

        // if size >= then there are more than 11 unique event ids present
        assertTrue(nearbyEventIds.size() >= 12);
    }

    @Test
    @Order(2)
    @DisplayName("Try Booking an event")
    void checkCreateBooking() {
        String url = "https://www.trybooking.com/CDKGX";
        myDriver.get(url);
        myDriver.findElement(By.xpath("//*[@id=\"sticky-bottom-btn\"]/div/button")).click();
        myDriver.findElement(By.xpath("//*[@id=\"event-app\"]/div[2]/div/div/div[2]/div[1]/div/div[5]/a")).click();
        myDriver.findElement(By.xpath("//*[@id=\"ticket-rows-container\"]/div/div[1]/div[4]/div/button[2]")).click();
        myDriver.findElement(By.xpath("//*[@id=\"tickets-next-button\"]")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Fill in student number input
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[1]/div[2]/input")).clear();
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[1]/div[2]/input")).sendKeys("s3783867");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //day select
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[2]/div[2]/div[1]/div[1]/div[1]/span[1]")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[5]")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //month select
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/span[1]")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div[3]")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //year select
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/span[1]")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[2]/div[2]/div[1]/div[3]/div[2]/div[2]/div[7]")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //last semester select
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[3]/div[2]/div[1]/div[1]/span[1]")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[3]/div[2]/div[1]/div[2]/div[2]/div[4]")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myDriver.findElement(By.xpath("//*[@id=\"bookingDataForm\"]/div/div/div[3]/div[2]/div[1]/div[1]/span[1]")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myDriver.findElement(By.xpath("//*[@id=\"data-entry-next-button\"]")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myDriver.findElement(By.id("txtFirstName")).clear();
        myDriver.findElement(By.id("txtFirstName")).sendKeys("Sovatharo");
        myDriver.findElement(By.id("txtLastName")).clear();
        myDriver.findElement(By.id("txtLastName")).sendKeys("Huy");
        myDriver.findElement(By.id("txtEmailAddress")).clear();
        myDriver.findElement(By.id("txtEmailAddress")).sendKeys("s3783867@student.rmit.edu.au");
        myDriver.findElement(By.id("txtConfirmEmailAddress")).clear();
        myDriver.findElement(By.id("txtConfirmEmailAddress")).sendKeys("s3783867@student.rmit.edu.au");
        myDriver.findElement(By.xpath("//*[@id=\"btn-purchase-lg\"]")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("Transaction successful", myDriver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[1]/div[1]/h1/span")).getText());
        assertEquals("s3783867@student.rmit.edu.au", myDriver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[1]/div[2]/div/div[1]/div[2]/div[3]/span")).getText());
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