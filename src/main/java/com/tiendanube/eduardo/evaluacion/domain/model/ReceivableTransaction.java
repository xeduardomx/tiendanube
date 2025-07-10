package com.tiendanube.eduardo.evaluacion.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "receivable_transactions")
public class ReceivableTransaction {

    @Id
    private String id;

    private String status;

    @Column(name = "create_date")
    private LocalDate createDate;

    private BigDecimal value;

    private String description;

    private String method;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "card_expiration_date")
    private String cardExpirationDate;

    @Column(name = "card_cvv")
    private String cardCvv;

    private BigDecimal subtotal;

    private BigDecimal discount;

    private BigDecimal total;

    @Column(name = "payment_day")
    private LocalDate paymentDay;

    @Column(name = "merchant_id")
    private String merchantId;


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getCreateDate() { return createDate; }
    public void setCreateDate(LocalDate createDate) { this.createDate = createDate; }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }

    public String getCardExpirationDate() { return cardExpirationDate; }
    public void setCardExpirationDate(String cardExpirationDate) { this.cardExpirationDate = cardExpirationDate; }

    public String getCardCvv() { return cardCvv; }
    public void setCardCvv(String cardCvv) { this.cardCvv = cardCvv; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public LocalDate getPaymentDay() { return paymentDay; }

    public void setPaymentDay(LocalDate paymentDay) { this.paymentDay = paymentDay; }

    public String getMerchantId() { return merchantId; }

    public void setMerchantId(String merchantId) { this.merchantId = merchantId; }
}
