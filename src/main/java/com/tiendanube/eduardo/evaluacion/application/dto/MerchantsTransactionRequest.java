package com.tiendanube.eduardo.evaluacion.application.dto;


import java.math.BigDecimal;

public class MerchantsTransactionRequest {
    private BigDecimal value;
    private String description;
    private PaymentMethod method;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private String cardCvv;

    public MerchantsTransactionRequest() {
    }

    public MerchantsTransactionRequest(Builder builder) {
    }

    public static class Builder {
        public Builder id(BigDecimal id) {
            return this;
        }

        public Builder value(BigDecimal value) {
            return this;
        }

        public Builder description(String description) {
            return this;
        }

        public Builder method(PaymentMethod method) {
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            return this;
        }

        public Builder cardHolderName(String cardHolderName) {
            return this;
        }

        public Builder cardExpirationDate(String cardExpirationDate) {
            return this;
        }

        public Builder cardCvv(String cardCvv) {
            return this;
        }

        public MerchantsTransactionRequest build() {
            return new MerchantsTransactionRequest(this);
        }
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }
}
