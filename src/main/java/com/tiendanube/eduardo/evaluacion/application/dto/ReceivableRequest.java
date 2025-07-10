package com.tiendanube.eduardo.evaluacion.application.dto;

import java.time.LocalDateTime;

public class ReceivableRequest {

    private String id;
    private String transactionId;
    private Double value;
    private LocalDateTime dueDate;
    private String status;
    private LocalDateTime createDate;
    private double subtotal;
    private double discount;
    private double total;

    public ReceivableRequest() {
    }

    public ReceivableRequest(
            String id,
            String transactionId,
            Double value,
            LocalDateTime dueDate,
            String status,
            LocalDateTime createDate,
            double subtotal,
            double discount,
            double total
    ) {
        this.id = id;
        this.transactionId = transactionId;
        this.value = value;
        this.dueDate = dueDate;
        this.status = status;
        this.createDate = createDate;
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String transactionId;
        private Double value;
        private LocalDateTime dueDate;
        private String status;
        private LocalDateTime createDate;
        private double subtotal;
        private double discount;
        private double total;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder value(Double value) {
            this.value = value;
            return this;
        }

        public Builder dueDate(LocalDateTime dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder subtotal(double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public Builder total(double total) {
            this.total = total;
            return this;
        }

        public ReceivableRequest build() {
            return new ReceivableRequest(id, transactionId, value, dueDate, status, createDate, subtotal, discount, total);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
