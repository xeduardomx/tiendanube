package com.tiendanube.eduardo.evaluacion.infrastructure.external.transaction.dto;

public class TransactionDto {

    private String id;
    private String value;
    private String description;
    private String method;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private String cardCvv;

    public TransactionDto() {}

    public TransactionDto(String id, String value, String description, String method, String cardNumber,
                          String cardHolderName, String cardExpirationDate, String cardCvv) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.method = method;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpirationDate = cardExpirationDate;
        this.cardCvv = cardCvv;
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
