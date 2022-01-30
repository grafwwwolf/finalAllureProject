package ru.pigarev.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.pigarev.basetests.BaseTests;

@Tag("All")
@DisplayName("Набор тестов для долларовых депозитов")
public class UsdDepositTest extends BaseTests {

    @Test
    @DisplayName("Test USD deposit")
    public void usdDepositTest() {
        pageManager.getHomePage().chooseServiceFromTopMenu(usdDeposit)
                .chooseProfitabilityCalculator()
                .chooseCurrency(usdDeposit)
                .fillDepositSum(usdDeposit)
                .selectDepositTerm(usdDeposit)
                .fillMonthlyReplenishment(usdDeposit)
                .monthlyCapitalization(usdDeposit)
                .checkCalc(usdDeposit);
    }
}
