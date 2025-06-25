package com.Service;

import com.DAO.BankStatementDAO;
import com.DAO.BankStatementDAOImpl;
import com.model.BankStatement;



public class BankStatementServiceImpl implements BankStatementService{
	BankStatementDAO bankStatementDAO= new BankStatementDAOImpl(); 
	@Override
	public int bankStatementDetails(BankStatement bankStatement) {
		
		return bankStatementDAO.insertBankStatement(bankStatement);
		
	}

}
