package com.fabriciooliveira.projectstone.domain;

/**
 * Created by fabriciooliveira on 12/25/15.
 */
public class CardTransaction {

    private String owner;

    private String cardNumber;

    private String expirationYear;

    private String expirationMonth;

    private String cardFlag;

    private String cVV;

    private float total;

    public CardTransaction() {

    }

    public CardTransaction(String owner, String cardNumber, String expirationYear, String expirationMonth, String cardFlag, String cVV, float total) {
        this.owner = owner;
        this.cardNumber = cardNumber;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
        this.cardFlag = cardFlag;
        this.cVV = cVV;
        this.total = total;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(String cardFlag) {
        this.cardFlag = cardFlag;
    }

    public String getcVV() {
        return cVV;
    }

    public void setcVV(String cVV) {
        this.cVV = cVV;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
