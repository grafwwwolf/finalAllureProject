package ru.pigarev.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.pigarev.basetests.BaseTests;
import ru.pigarev.framework.utils.models.Deposit;

@Tag("All")
public class ParametrizedDepositTest extends BaseTests {

    static Deposit[] mpDeposits() {
        return new Deposit[]{rubDeposit, usdDeposit};
    }

    @ParameterizedTest
    @MethodSource("mpDeposits")
    public void parametrizedDepositTest(Deposit deposit) {
        pageManager.getHomePage().chooseServiceFromTopMenu(deposit)
                .chooseProfitabilityCalculator()
                .chooseCurrency(deposit)
                .fillDepositSum(deposit)
                .selectDepositTerm(deposit)
                .fillMonthlyReplenishment(deposit)
                .monthlyCapitalization(deposit)
                .checkCalc(deposit);
    }
}
