package com.js.sas.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName PartnerDTO
 * @Description 往来单位
 * @Author zc
 * @Date 2019/6/13 17:57
 **/
public class PartnerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String code;

    private String parentCode;

    private String name;

    private float receivables;

    private char status;

    private int paymentDate;

    private int paymentMonth;

    private char settlementType;

    private int limit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getReceivables() {
        return receivables;
    }

    public void setReceivables(float receivables) {
        this.receivables = receivables;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(int paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(int paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public char getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(char settlementType) {
        this.settlementType = settlementType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartnerDTO)) return false;
        PartnerDTO that = (PartnerDTO) o;
        return getId() == that.getId() &&
                Float.compare(that.getReceivables(), getReceivables()) == 0 &&
                getStatus() == that.getStatus() &&
                getPaymentDate() == that.getPaymentDate() &&
                getPaymentMonth() == that.getPaymentMonth() &&
                getSettlementType() == that.getSettlementType() &&
                getLimit() == that.getLimit() &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getParentCode(), that.getParentCode()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getParentCode(), getName(), getReceivables(), getStatus(), getPaymentDate(), getPaymentMonth(), getSettlementType(), getLimit());
    }

    @Override
    public String toString() {
        return "PartnerDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", name='" + name + '\'' +
                ", receivables=" + receivables +
                ", status=" + status +
                ", paymentDate=" + paymentDate +
                ", paymentMonth=" + paymentMonth +
                ", settlementType=" + settlementType +
                ", limit=" + limit +
                '}';
    }
}
