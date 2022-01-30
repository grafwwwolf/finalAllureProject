package ru.pigarev.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.pigarev.basetests.BaseTests;

@Tag("All")
@DisplayName("Набор тестов для рублевых депозитов")
public class RubDepositTest extends BaseTests {

    @Test
    @DisplayName("Test RUB deposit")
    public void rubDepositTest() {
        pageManager.getHomePage().chooseServiceFromTopMenu(rubDeposit)
        .chooseProfitabilityCalculator()
        .chooseCurrency(rubDeposit)
        .fillDepositSum(rubDeposit)
        .selectDepositTerm(rubDeposit)
        .fillMonthlyReplenishment(rubDeposit)
        .monthlyCapitalization(rubDeposit)
        .checkCalc(rubDeposit);
    }
}
