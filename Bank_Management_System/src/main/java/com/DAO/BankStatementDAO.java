package com.DAO;

import com.model.BankStatement;

public interface BankStatementDAO {
	int insertBankStatement(BankStatement bankStatement);
	 void getStatementByUsingUserId(int userId);
}
