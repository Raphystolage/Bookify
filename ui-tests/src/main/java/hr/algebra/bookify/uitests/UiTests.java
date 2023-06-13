package hr.algebra.bookify.uitests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UiTests {

    @Test
    public void testUIEditBook() throws InterruptedException {

        //Driver set up
        System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();

        //Access to UI
        driver.get("http://localhost:8081/book");

        //Fetch first title
        WebElement firstRow = driver.findElement(By.xpath("//table//tr[2]"));
        WebElement titleElement = firstRow.findElement(By.xpath("//td[2]"));
        String title = titleElement.getText();

        //Edit book
        WebElement editButton = firstRow.findElement(By.tagName("a"));
        Thread.sleep(1000L);
        editButton.click();
        Thread.sleep(1000L);
        WebElement titleInput = driver.findElement(By.cssSelector("input[name='title']"));
        titleInput.clear();
        titleInput.sendKeys("New title");
        WebElement saveButton = driver.findElement(By.cssSelector("input[type='submit']"));
        Thread.sleep(1000L);
        saveButton.click();

        //Test
        WebElement editedRow = driver.findElement(By.xpath("//table//tr[2]"));
        WebElement editedTitleElement = editedRow.findElement(By.xpath("//td[2]"));
        String editedTitle = editedTitleElement.getText();

        assertNotEquals(title,editedTitle);

        Thread.sleep(1000L);
        driver.close();
    }

}
