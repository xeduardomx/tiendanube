package com.tiendanube.eduardo.evaluacion.application.dto;


public class TransactionRequest {
    private String id;
    private String value;
    private String description;
    private String method;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private String cardCvv;

    public TransactionRequest() {
    }

    public TransactionRequest(Builder builder) {
    }

    public static class Builder {
        public Builder id(String id) {
            return this;
        }

        public Builder value(String value) {
            return this;
        }

        public Builder description(String description) {
            return this;
        }

        public Builder method(String method) {
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

        public TransactionRequest build() {
            return new TransactionRequest(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
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
