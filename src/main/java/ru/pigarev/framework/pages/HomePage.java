package ru.pigarev.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.pigarev.framework.utils.models.BankProduct;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@class=\"site-header__content-bottom-nav-link nav__link nav__link_parent\"]")
    private List<WebElement> serviceTopMenu;

    @FindBy(xpath = "//a[@class=\"nav__link\" and contains(text(), \"Калькулятор доходности\")]")
    private WebElement profitabilityCalculator;

    @Step("Клик на меню выбора банковсокого продукта. Хранится в name '{product}'")
    public HomePage chooseServiceFromTopMenu(BankProduct product) {
        String serviceName = product.getName();
        for (WebElement element : serviceTopMenu) {
            if (element.findElement(By.xpath("./span")).getText().contains(serviceName)) {
                waitUtilElementToBeClickable(element).click();
                Assertions.assertTrue(element.getAttribute("class").contains("opened"), "Клик на " + serviceName + " не произошел.");
                return this;
            }
        }
        Assertions.fail(serviceName + " в спике отсутствует.");
        return this;
    }

    @Step("Клик на 'Калькулятор доходности'")
    public DepositsPage chooseProfitabilityCalculator() {
        waitUtilElementToBeClickable(profitabilityCalculator).click();
        wait.until(ExpectedConditions.titleContains("Вклады"));
        Assertions.assertTrue(driverManager.getDriver().getTitle().contains("Вклады"), "Клик на 'Калькулятор доходности' не состоялся");
        return pageManager.getDepositsPage();
    }
}
