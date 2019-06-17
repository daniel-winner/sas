package com.js.sas.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName SettlementSummaryDTO
 * @Description 结算客户财务统计
 * @Author zhaoc
 * @Date 2019/6/14 12:25
 **/
public class SettlementSummaryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int rowNum;
    // 结算客户编码
    private String code;
    // 结算客户名称
    private String name;
    // 来源
    //@Size(min = 1, max = 4, message = "来源错误")
    //@ApiModelProperty(value = "来源", required = false)
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
    // 开始时间 yyyy-MM-dd
    //@Pattern(regexp = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)", message = "请填写正确的日期YYYY-MM-DD")
    //@ApiModelProperty(value = "开始时间", required = false)
    private String startDate;
    // 结束时间 yyyy-MM-dd
    private String endDate;
    // 分页起始数量（偏移量）
    private int offset;
    // 每页数量
    private int limit;
    // 排序字段
    private String sort;
    // 排序方式
    private String sortOrder;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SettlementSummaryDTO)) return false;
        SettlementSummaryDTO that = (SettlementSummaryDTO) o;
        return getRowNum() == that.getRowNum() &&
                Float.compare(that.getDeliveryAmount(), getDeliveryAmount()) == 0 &&
                Float.compare(that.getReceivedAmount(), getReceivedAmount()) == 0 &&
                Float.compare(that.getReceivables(), getReceivables()) == 0 &&
                Float.compare(that.getInvoiceAmount(), getInvoiceAmount()) == 0 &&
                Float.compare(that.getInvoiceBalance(), getInvoiceBalance()) == 0 &&
                getOffset() == that.getOffset() &&
                getLimit() == that.getLimit() &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getChannel(), that.getChannel()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getSort(), that.getSort()) &&
                Objects.equals(getSortOrder(), that.getSortOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRowNum(), getCode(), getName(), getChannel(), getDeliveryAmount(), getReceivedAmount(), getReceivables(), getInvoiceAmount(), getInvoiceBalance(), getStartDate(), getEndDate(), getOffset(), getLimit(), getSort(), getSortOrder());
    }

    @Override
    public String toString() {
        return "SettlementSummaryDTO{" +
                "rowNum=" + rowNum +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", channel='" + channel + '\'' +
                ", deliveryAmount=" + deliveryAmount +
                ", receivedAmount=" + receivedAmount +
                ", receivables=" + receivables +
                ", invoiceAmount=" + invoiceAmount +
                ", invoiceBalance=" + invoiceBalance +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                ", sort='" + sort + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }

}
