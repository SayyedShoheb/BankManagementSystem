package com.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class BankStatement {
	private int transactionId;
	private double transactionAmount;
	private double balanceAmount;
	private LocalDate dateOfTransaction;
	private LocalTime timeOfTransaction;
	private String transactionType;
	private int userId;
	public BankStatement() {
		
	}
	public BankStatement(int transactionId, double transactionAmount, double balanceAmount, LocalDate dateOfTransaction,
			LocalTime timeOfTransaction, String transactionType, int userId) {
		super();
		this.transactionId = transactionId;
		this.transactionAmount = transactionAmount;
		this.balanceAmount = balanceAmount;
		this.dateOfTransaction = dateOfTransaction;
		this.timeOfTransaction = timeOfTransaction;
		this.transactionType = transactionType;
		this.userId = userId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public LocalDate getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(LocalDate dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public LocalTime getTimeOfTransaction() {
		return timeOfTransaction;
	}
	public void setTimeOfTransaction(LocalTime timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
