package ru.pigarev.framework.managers;

import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static ru.pigarev.framework.utils.PropConst.*;

public class DriverManager {
    private static DriverManager driverManager = null;
    private TestPropManager propManager = TestPropManager.getInstance();
    private WebDriver driver;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOsFamily();
        } else if (OS.isFamilyMac()) {
            initDriverMacOsFamily();
        } else if (OS.isFamilyUnix()) {
            initDriverUnixOsFamily();
        }
    }

    private void initDriverR() {
//        System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropConst.PATH_CHROME_DRIVER_WINDOWS));
//        ChromeOptions ops = new ChromeOptions();
//        ops.addArguments("--disable-notifications");
//        driver = new ChromeDriver(ops);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("81.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        try {
            driver = new RemoteWebDriver(
                    URI.create("http://164.92.227.174:4444/wd/hub").toURL(),
                    capabilities
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public void quitDriver() {
        driver.manage().deleteAllCookies();
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void initDriverWindowsOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_WINDOWS, PATH_CHROME_DRIVER_WINDOWS);
    }

    private void initDriverMacOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_MAC, PATH_CHROME_DRIVER_MAC);
    }

    private void initDriverUnixOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_UNIX, PATH_CHROME_DRIVER_UNIX);
    }

    private void initDriverAnyOsFamily(String gecko, String chrome) {
        switch (propManager.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", propManager.getProperty(gecko));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", propManager.getProperty(chrome));
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--disable-notifications");
                driver = new ChromeDriver(ops);
//                driver = new ChromeDriver();
                break;
            default:
                Assertions.fail("Типа браузера '" + propManager.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }
}
