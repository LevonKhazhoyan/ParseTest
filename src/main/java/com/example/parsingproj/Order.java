package com.example.parsingproj;

public class Order {

    private int orderId;
    private double amount;
    private String currency;
    private String comment;
    private String fileName;
    private String line;
    private String result;

    public Order(int orderId, double amount, String currency, String comment){
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Order(){ }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "\"orderId\"=" + orderId +
                ",\"amount\"=" + amount +
                ",\"currency\"=\"" + currency + "\"" +
                ",\"comment\"=\"" + comment + "\"" +
                ",\"fileName\"=\"" + fileName + "\"" +
                ",\"line\"=\"" + line + "\"" +
                ",\"result\"=\"" + result + "\"" ;
    }
}
