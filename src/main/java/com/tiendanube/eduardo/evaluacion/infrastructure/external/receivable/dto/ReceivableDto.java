package com.tiendanube.eduardo.evaluacion.infrastructure.external.receivable.dto;

import java.math.BigDecimal;

public class ReceivableDto {
    private String id;
    private String status;
    private String createDate;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal total;

    public ReceivableDto() {}

    public ReceivableDto(
            String id,
            String status,
            String createDate,
            BigDecimal subtotal,
            BigDecimal discount,
            BigDecimal total
    ) {
        this.id = id;
        this.status = status;
        this.createDate = createDate;
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
    }

    public static class Builder {
        private String id;
        private String status;
        private String createDate;
        private BigDecimal subtotal;
        private BigDecimal discount;
        private BigDecimal total;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder createDate(String createDate) {
            this.createDate = createDate;
            return this;
        }

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

        public ReceivableDto build() {
            return new ReceivableDto(id, status, createDate, subtotal, discount, total);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
}
