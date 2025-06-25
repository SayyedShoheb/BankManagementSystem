package com.model;

public class BankUserDetails {
  /*
  id, Name, EmailId, AadharNumber, MobileNumber, PanNumber, Address, Gender, 
  Amount, Pin, AccountNumber, Status
  */
	private int id;
	private String Name;
	private String EmailId;
	private long AadharNumber;
	private long MobileNumber;
	private String PanNumber;
	private String Address;
	private String Gender;
	private double Amount;
	private int Pin;
	private long AccountNumber;
	private String Status;
	
	public BankUserDetails() {
		
	}

	public BankUserDetails(int id, String name, long aadharNumber, long mobileNumber, String panNumber, String address,
			String gender, double amount, int pin, int accountNumber, String status) {
		super();
		this.id = id;
		Name = name;
		AadharNumber = aadharNumber;
		MobileNumber = mobileNumber;
		PanNumber = panNumber;
		Address = address;
		Gender = gender;
		Amount = amount;
		Pin = pin;
		AccountNumber = accountNumber;
		Status = status;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	public long getAadharNumber() {
		return AadharNumber;
	}

	public void setAadharNumber(long aadharNumber) {
		AadharNumber = aadharNumber;
	}

	public long getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getPanNumber() {
		return PanNumber;
	}

	public void setPanNumber(String panNumber) {
		PanNumber = panNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public int getPin() {
		return Pin;
	}

	public void setPin(int pin) {
		Pin = pin;
	}

	public long getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "BankUserDetails [id=" + id + ", Name=" + Name + ", AadharNumber=" + AadharNumber + ", MobileNumber="
				+ MobileNumber + ", PanNumber=" + PanNumber + ", Address=" + Address + ", Gender=" + Gender
				+ ", Amount=" + Amount + ", Pin=" + Pin + ", AccountNumber=" + AccountNumber + ", Status=" + Status
				+ "]";
	}
	
	
}
