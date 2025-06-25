package com.Service;

public interface AdminService {
	void adminLogin();
	void allAccountRequestDetsils();
	void acceptRequest(long aadharNumber);
	int rejectRequest(long aadharNumber);
	void allDeletionAccountRequests();
	int approveRequest(long aadharNumber);
	int rejectDeletionRequest(long aadharNumber);
}
