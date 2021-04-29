import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Issue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Test12 {
    public WebDriver driver;
    public String checking;
    @BeforeEach
    public void initDriver() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("acceptInsecureCerts", true);
        capabilities.setVersion("90.0");

         driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub/").toURL(),
                capabilities
        );
        driver.manage().window().setSize(new Dimension(1920,1024));
        WebDriverRunner.setWebDriver(driver);
    }

    @org.junit.jupiter.api.Test
    @Issue("AE-2")
    @DisplayName("Открываем сщуствующую Issue")
    public void testIssue() {
        try {
            driver.get("http://10.48.0.13/auth?error=noauth");
            Thread.sleep(200);
            driver.findElement(By.xpath("//*[@id=\"username-login\"]")).sendKeys("100");
            Thread.sleep(200);
            driver.findElement(By.xpath("//*[@id=\"passwordfield\"]")).sendKeys("123");
            Thread.sleep(200);
            driver.findElement(By.className("auth__button")).click();
            Thread.sleep(2000);
            checking = driver.findElement(By.className("auth__button")).getText();
            System.out.println(checking);
            if (checking.equals("Выбрать")) {
                System.out.println("Test is Successful");
                driver.quit();
                assertTrue(true);
            } else {
                driver.quit();
                fail("Test is Failing.");
            }

        } catch (Throwable e) {
            e.printStackTrace();
            driver.quit();
        }
    }


}