package cinema.model;

import java.util.Currency;

/**
 * Created by Vitali_Sheuka on 9/7/2016.
 */
public class Amount {

    private Double amount;
    private Currency currency;

    public Amount() {
    }

    public Amount(Double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
