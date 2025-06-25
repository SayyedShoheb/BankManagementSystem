package com.DAO;

import java.util.List;

import com.model.BankUserDetails;

public interface BankUserDAO {
	void insertBankUserDetails(BankUserDetails bankUserDetails);
	void getUserDetailsByUsingEmailAndPin();
	List<BankUserDetails>  getAllBankUserDetails();
	BankUserDetails checkEmailAndPinForLogin(String emailId,int pin);
	int updateAmountByUsingPin(double balanceAmount,int pin);
	int updatePinByUsingAccountNumberAndOldpin(double accountNumber,int oldPin,int newPin);
	int requestToDeleteAccount(long accountNumber,int pin,String reason);
}
