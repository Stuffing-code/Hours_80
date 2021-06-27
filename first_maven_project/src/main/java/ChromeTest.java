import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class ChromeTest {

    WebDriver driver;

    @BeforeTest
    public void beforeTest(String text_site) {
        String webDriverKey = "webserver.chrome.driver";
        String webDriverValue = System.getProperty("user.dir") +
                "/target/tmp_webservers/chromedriver-linux-32bit";
        System.setProperty(webDriverKey, webDriverValue);
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
        driver.get(text_site);
    }

//todo: test

    @AfterTest
    public void afterTest() {
        driver.quit();
    }


    public void singInTest(){
        driver.findElement(By.className("gNO89b")).click();
    }

    public void sendKeysTest() {
        driver.findElement(By.className("gLFyf")).sendKeys("dsadsa");
    }

    public void clearTest() {
        driver.findElement(By.className("gLFyf")).clear();
    }
}