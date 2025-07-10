package com.tiendanube.eduardo.evaluacion.application.dto;

import java.math.BigDecimal;

public class ReceivableSummary {
    private BigDecimal totalReceivable;
    private BigDecimal totalReceived;
    private BigDecimal futureReceivable;
    private BigDecimal totalCommissions;

    public ReceivableSummary(
            BigDecimal totalReceivable,
            BigDecimal futureReceivable,
            BigDecimal totalCommissions,
            BigDecimal totalReceived
    ) {
        this.totalReceivable = totalReceivable;
        this.futureReceivable = futureReceivable;
        this.totalCommissions = totalCommissions;
        this.totalReceived = totalReceived;
    }

    public ReceivableSummary() {
    }

    public BigDecimal getTotalReceivable() {
        return totalReceivable;
    }

    public void setTotalReceivable(BigDecimal totalReceivable) {
        this.totalReceivable = totalReceivable;
    }

    public BigDecimal getFutureReceivable() {
        return futureReceivable;
    }

    public void setFutureReceivable(BigDecimal futureReceivable) {
        this.futureReceivable = futureReceivable;
    }

    public BigDecimal getTotalCommissions() {
        return totalCommissions;
    }

    public void setTotalCommissions(BigDecimal totalCommissions) {
        this.totalCommissions = totalCommissions;
    }

    public BigDecimal getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(BigDecimal totalReceived) {
        this.totalReceived = totalReceived;
    }
}
