package ru.pigarev.framework.utils.models;

public abstract class BankProduct {
    protected String name;
    protected String currency;
    protected String currencyAbb;
    protected String sumOfBankProduct;
    protected Integer termInMonth;

    public BankProduct(String currency, String currencyAbb, String sumOfBankProduct, Integer termInMonth) {
        this.currency = currency;
        this.currencyAbb = currencyAbb;
        this.sumOfBankProduct = sumOfBankProduct;
        this.termInMonth = termInMonth;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencyAbb() {
        return currencyAbb;
    }

    public String getSumOfBankProduct() {
        return sumOfBankProduct;
    }

    public Integer getTermInMonth() {
        return termInMonth;
    }
}
