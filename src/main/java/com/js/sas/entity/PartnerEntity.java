package com.js.sas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName Partner
 * @Description 往来单位
 * @Author zc
 * @Date 2019/6/13 09:44
 **/
@Entity
@Table(name = "YY_AA_Partner")
public class PartnerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String parentCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float receivables;

    @Column(nullable = false)
    private char status;

    @Column(nullable = false)
    private int paymentDate;

    @Column(nullable = false)
    private int paymentMonth;

    @Column(nullable = false)
    private char settlementType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartnerEntity)) return false;
        PartnerEntity partner = (PartnerEntity) o;
        return getId() == partner.getId() &&
                Float.compare(partner.getReceivables(), getReceivables()) == 0 &&
                getStatus() == partner.getStatus() &&
                getPaymentDate() == partner.getPaymentDate() &&
                getPaymentMonth() == partner.getPaymentMonth() &&
                getSettlementType() == partner.getSettlementType() &&
                Objects.equals(getCode(), partner.getCode()) &&
                Objects.equals(getParentCode(), partner.getParentCode()) &&
                Objects.equals(getName(), partner.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getParentCode(), getName(), getReceivables(), getStatus(), getPaymentDate(), getPaymentMonth(), getSettlementType());
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", name='" + name + '\'' +
                ", receivables=" + receivables +
                ", status=" + status +
                ", paymentDate=" + paymentDate +
                ", paymentMonth=" + paymentMonth +
                ", settlementType=" + settlementType +
                '}';
    }
}
