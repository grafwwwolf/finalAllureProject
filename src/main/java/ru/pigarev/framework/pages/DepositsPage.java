package ru.pigarev.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.pigarev.framework.utils.models.Deposit;

import java.util.List;

public class DepositsPage extends BasePage {

    @FindBy(xpath = "//label[@class=\"calculator__currency-field\"]")
    private List<WebElement> CurrencyList;

    @FindBy(xpath = "//div[@class=\"calculator__content\"]")
    private WebElement calculatorContent;

    @FindBy(xpath = "//div[@class=\"calculator__content\"]//div[@class=\"jq-selectbox__select-text\"]")
    private WebElement depositTerm;

    @FindBy(xpath = "//label[@class=\"calculator__slide-input-label\" and contains(text(), \"Сумма вклада\")]/..//label[contains(@class, \"currency-input-field\")]")
    private WebElement depositSumBlock;

    @FindBy(xpath = "//div[@class=\"calculator__content\"]//li")
    private List<WebElement> depositTermList;

    @FindBy(xpath = "//div[@class=\"calculator__content\"]//input[@class=\"calculator__slide-input currency-input js-slide-value\" and @name=\"replenish\"]")
    private WebElement monthlyReplenishment;

    @FindBy(xpath = "//div[@class=\"calculator__content\"]//input[@class=\"calculator__check\" and @name=\"capitalization\"]/../..")
    private WebElement monthlyCapitalization;

    @FindBy(xpath = "//div[@class=\"calculator__result-block\"]//span[@class=\"js-calc-earned\"]")
    private WebElement calcEarned;      //Начислено %

    @FindBy(xpath = "//div[@class=\"calculator__result-block\"]//span[@class=\"js-calc-replenish\"]")
    private WebElement calcReplenish;       //Пополнение за срок вклада

    @FindBy(xpath = "//span[@class=\"js-calc-result\"]")
    private WebElement calcResult;


    @Step("Клик на выбор валюты. Хранится в currency '{deposit}'")
    public DepositsPage chooseCurrency(Deposit deposit) {
        String currency = deposit.getCurrency();
        for (WebElement element : CurrencyList) {
            WebElement elem = element.findElement(By.xpath("./span"));
            if (elem.getText().equalsIgnoreCase(currency)) {
                waitUtilElementToBeClickable(elem).click();
                Assertions.assertTrue(depositSumBlock.getAttribute("class").contains("rub"));
                return this;
            }
        }
        Assertions.fail("Валюта '" + currency + "' в списке отсутствует");
        return this;
    }

    @Step("Заполнение поля 'Сумма вклада'. Значением Хранится в sumOfBankProduct '{deposit}'")
    public DepositsPage fillDepositSum(Deposit deposit) {
        String value = deposit.getSumOfBankProduct();
        fillInputField(depositSumBlock.findElement(By.xpath("./input")), value);
        return this;
    }

    @Step("Выбор срока вклада. Значением Хранится в termInMonth '{deposit}'")
    public DepositsPage selectDepositTerm(Deposit deposit) {
        Integer term = deposit.getTermInMonth();
        Select select = new Select(driverManager.getDriver().findElement(By.xpath("//select[@class=\"calculator__slide-input js-slide-value\"]")));
        select.selectByValue(term.toString());
        Assertions.assertEquals(stringToInt(depositTerm.getText()), term, "Выбранный срок вклада не совпадает с искомым сроком.");
        return this;
    }

    @Step("Заполнение поля 'Ежемесячное пополнение'. Значением Хранится в monthlyReplenishment '{deposit}'")
    public DepositsPage fillMonthlyReplenishment(Deposit deposit) {
        String value = deposit.getMonthlyReplenishment();
        fillInputField(monthlyReplenishment, value);
        return this;
    }

    @Step("Выбор 'Ежемесячной капитализации'. Значение 'true' либо  'false' хранится в monthlyCapitalization '{deposit}'")
    public DepositsPage monthlyCapitalization(Deposit deposit) {
        if (deposit.isMonthlyCapitalization()) {
            waitUtilElementToBeClickable(monthlyCapitalization).click();
            Assertions.assertTrue(monthlyCapitalization.findElement(By.xpath(".//input")).isSelected(), "Поле 'Ежемесячная капитализация' не выбрано.");
        }
        return this;
    }

    @Step("Проверка полученных расчетов полей 'Начислено %', 'Пополнение на указанный срок депозита', 'Снятие после указанного срока депозита'." +
            "Значения хранятся соответственно в calcEarned, calcReplenish, calcResult '{deposit}'")
    public DepositsPage checkCalc(Deposit deposit) {
        String valueEarned = deposit.getCalcEarned();
        String valueReplenish = deposit.getCalcReplenish();
        String valueResult = deposit.getCalcResult();
        String text = calcEarned.getText();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(calcEarned, text)));
        Assertions.assertEquals(calcEarned.getText().replaceAll(" ", ""), valueEarned, "Сумма процентов за период вклада не совпадает.");
        Assertions.assertEquals(calcReplenish.getText().replaceAll(" ", ""),  valueReplenish, "Пополнение за период вклада не совпадает");
        Assertions.assertEquals(calcResult.getText().replaceAll(" ", ""),  valueResult, "Сумма к снятию не совпадает");
       return this;
    }


}
