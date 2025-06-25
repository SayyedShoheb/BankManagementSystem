package com.Service;

public interface BankService {
	  void forSleep(String statement);
	  void userRegistration();
	  void userLogin();
	  void debit(double bankAmount,int pin);
	  void credit(double bankAmount,int pin);
	  int updatePin(double accountNumber ,int oldPin,int newPin);
	  void checkStatement(int userId);
	  int requestToDeleteAccount(long accountNumber,int pin,String reason);
}
