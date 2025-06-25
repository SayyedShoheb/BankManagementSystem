package com.DAO;

public interface AdminDAO {
	boolean getAdminDetailsUsingAdminEmailidAndPassword(String emailId,String password);
	int updatePinAndAccountNumberAndStatusByUsingAadharNumber(int pin,int aacountNumber,long aadharNumber);
	int deleteUserDetailsByUsingAadharNumber(long aadharNumber);
	int rejectDeletionRequest(long aadharNumber);
}
