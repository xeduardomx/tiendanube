package com.tiendanube.eduardo.evaluacion.application.dto;

import java.math.BigDecimal;

public class ReceivableCalculationResult {
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal total;
    private String status;

    public ReceivableCalculationResult() {
    }

    public ReceivableCalculationResult(BigDecimal subtotal, BigDecimal discount, BigDecimal total, String status) {
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
        this.status = status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BigDecimal subtotal;
        private BigDecimal discount;
        private BigDecimal total;
        private String status;

        public Builder subtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder discount(BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public Builder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public ReceivableCalculationResult build() {
            return new ReceivableCalculationResult(subtotal, discount, total, status);
        }
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
