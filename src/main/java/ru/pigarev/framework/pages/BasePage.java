package ru.pigarev.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.pigarev.framework.managers.DriverManager;
import ru.pigarev.framework.managers.PageManager;

public class BasePage{

    protected final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();

    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }


    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

//    protected WebElement waitUtilElementToBeVisible(WebElement element) {
//        return wait.until(ExpectedConditions.visibilityOf(element));
//    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
        waitUtilElementToBeClickable(element);
        element.clear();
        element.sendKeys(value);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
//        System.out.println(element.getAttribute("value") + "getClassValue");
        boolean checkFlag = element.getAttribute("value").replaceAll(" ", "").equals(value);
//        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");
    }

    public int stringToInt(String text) {
        return Integer.parseInt(text.replaceAll("\\D+", ""));
    }

//    public void checkCalc(WebElement element, String value) {
//        String text = element.getText();
//        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text)));
//        Assertions.assertEquals(element.getText().replaceAll(" ", ""), value, "Сумма не совпадает.");
//    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
