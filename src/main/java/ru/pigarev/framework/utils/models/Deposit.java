package ru.pigarev.framework.utils.models;

public class Deposit extends BankProduct {

    private String monthlyReplenishment;
    private boolean monthlyCapitalization;
    private String calcEarned;
    private String calcReplenish;
    private String calcResult;

    public String getMonthlyReplenishment() {
        return monthlyReplenishment;
    }

    public boolean isMonthlyCapitalization() {
        return monthlyCapitalization;
    }

    public String getCalcEarned() {
        return calcEarned;
    }

    public String getCalcReplenish() {
        return calcReplenish;
    }

    public String getCalcResult() {
        return calcResult;
    }


    public Deposit(String currency, String currencyAbb, String sumOfBankProduct, Integer termInMonth,
                   String monthlyReplenishment, boolean monthlyCapitalization, String calcEarned, String calcReplenish, String calcResult) {
        super(currency, currencyAbb, sumOfBankProduct, termInMonth);
        this.name = "Вклады";
        this.monthlyReplenishment = monthlyReplenishment;
        this.monthlyCapitalization = monthlyCapitalization;
        this.calcEarned = calcEarned;
        this.calcReplenish = calcReplenish;
        this.calcResult = calcResult;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "monthlyReplenishment='" + monthlyReplenishment + '\'' +
                ", monthlyCapitalization=" + monthlyCapitalization +
                ", calcEarned='" + calcEarned + '\'' +
                ", calcReplenish='" + calcReplenish + '\'' +
                ", calcResult='" + calcResult + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", currencyAbb='" + currencyAbb + '\'' +
                ", sumOfBankProduct='" + sumOfBankProduct + '\'' +
                ", termInMonth=" + termInMonth +
                '}';
    }
}
