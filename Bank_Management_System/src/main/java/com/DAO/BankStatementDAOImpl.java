package com.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.model.BankStatement;

public class BankStatementDAOImpl implements BankStatementDAO{
	private static final String url="jdbc:mysql://localhost:3306/teca_63?user=root&password=sayyed";
	Connection connection;
	private static final String insert="insert into bankStatement (transactionAmount, balanceAmount, dateOfTransaction, timeOfTransaction, transactionType, userId) values (?,?,?,?,?,?)";
	private static final String getStatement="select * from bankstatement where userId=?";
	@Override
	public int insertBankStatement(BankStatement bankStatement) {
		try {
			connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setDouble(1, bankStatement.getTransactionAmount());
			preparedStatement.setDouble(2, bankStatement.getBalanceAmount());
			preparedStatement.setDate(3, Date.valueOf(bankStatement.getDateOfTransaction()));
			preparedStatement.setTime(4, Time.valueOf(bankStatement.getTimeOfTransaction()));
			preparedStatement.setString(5, bankStatement.getTransactionType());
			preparedStatement.setInt(6, bankStatement.getUserId());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public void getStatementByUsingUserId(int userId) {
try {
	//"select * from bankstatement where userId=?";
	connection=DriverManager.getConnection(url);
	PreparedStatement preparedStatement=connection.prepareStatement(getStatement);
	preparedStatement.setInt(1, userId);
	ResultSet resultSet=preparedStatement.executeQuery();
	if(resultSet.isBeforeFirst()) {
		System.out.println("transactionId transactionAmount balanceAmount dateOfTransaction timeOfTransaction transactionType userId");
		while(resultSet.next()) {
		System.out.print(resultSet.getInt("transactionId")+"		");
		System.out.print(resultSet.getDouble("transactionAmount")+"		");
		System.out.print(resultSet.getDouble("balanceAmount")+"      ");
		System.out.print(resultSet.getDate("dateOfTransaction")+"         ");
		System.out.print(resultSet.getTime("timeOfTransaction")+"          ");
		System.out.print(resultSet.getString("transactionType")+"          ");
		System.out.println(resultSet.getInt("userId")+"	");
		
		}
	}
	else {
		System.out.println("No transactions are done yet......");
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}		
	}

}
