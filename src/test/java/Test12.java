package edosf.iitFirefox.login8077;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import edosf.methods.Cabinet;
import edosf.methods.Check;
import edosf.methods.EnterAndExit;
import edosf.settingsEdo.Iit8077;
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

public class Test12 implements Iit8077 {
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
            driver.get(URL);
            EnterAndExit.LogPass(LOG1LOG, PASS, driver);
            checking = driver.findElement(By.className("auth__button")).getText();
            System.out.println(checking);
            Check.CheckExit("Выбрать", checking, driver);
        } catch (Throwable e) {
            Cabinet.Catch(driver, e);
        }
    }


}