package com.fabiano.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    private String accountNumber;
    private Date transactionDate;
    private Double amount;

    public Transaction(){
    }

    public Transaction(String accountNumber,Date transactionDate,Double amount){
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    private DateFormat formatter =
            new SimpleDateFormat("MM/dd/yyyy");


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public DateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DateFormat formatter) {
        this.formatter = formatter;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber='" + accountNumber + '\'' +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                '}';
    }
}
