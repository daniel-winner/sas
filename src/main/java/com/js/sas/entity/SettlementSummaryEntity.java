package com.js.sas.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName SettlementSummaryEntity
 * @Description 结算客户财务统计
 * @Author zc
 * @Date 2019/6/10 08:00
 **/
@Entity
public class SettlementSummaryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int rowNum;
    // 结算客户编码
    private String code;
    // 结算客户名称
    private String name;
    // 来源
    private String channel;
    // 发货金额
    private float deliveryAmount;
    // 收款金额
    private float receivedAmount;
    // 应收款
    private float receivables;
    // 已开票金额
    private float invoiceAmount;
    // 发票结余
    private float invoiceBalance;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public float getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(float deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public float getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(float receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public float getReceivables() {
        return receivables;
    }

    public void setReceivables(float receivables) {
        this.receivables = receivables;
    }

    public float getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(float invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public float getInvoiceBalance() {
        return invoiceBalance;
    }

    public void setInvoiceBalance(float invoiceBalance) {
        this.invoiceBalance = invoiceBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SettlementSummaryEntity)) return false;
        SettlementSummaryEntity that = (SettlementSummaryEntity) o;
        return getRowNum() == that.getRowNum() &&
                Float.compare(that.getDeliveryAmount(), getDeliveryAmount()) == 0 &&
                Float.compare(that.getReceivedAmount(), getReceivedAmount()) == 0 &&
                Float.compare(that.getReceivables(), getReceivables()) == 0 &&
                Float.compare(that.getInvoiceAmount(), getInvoiceAmount()) == 0 &&
                Float.compare(that.getInvoiceBalance(), getInvoiceBalance()) == 0 &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getChannel(), that.getChannel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRowNum(), getCode(), getName(), getChannel(), getDeliveryAmount(), getReceivedAmount(), getReceivables(), getInvoiceAmount(), getInvoiceBalance());
    }

    @Override
    public String toString() {
        return "SettlementSummaryEntity{" +
                "rowNum=" + rowNum +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", channel='" + channel + '\'' +
                ", deliveryAmount=" + deliveryAmount +
                ", receivedAmount=" + receivedAmount +
                ", receivables=" + receivables +
                ", invoiceAmount=" + invoiceAmount +
                ", invoiceBalance=" + invoiceBalance +
                '}';
    }
}
