package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO{
	private static final String url="jdbc:mysql://localhost:3306/teca_63?user=root&password=sayyed";
	private static final String adminLogin="select * from admin where AdminEmailid=? and AdminPassword=?";
	private static final String update="update bankuserdetails set Pin=?,AccountNumber=?,Status=? where AadharNumber=?";
	private static final String deleteUserData="Delete from bankuserdetails where AadharNumber=?";
	private static final String deletionRequest="update bankuserdetails set Status=? where AadharNumber=?";
	Connection connection;
	@Override
	public boolean getAdminDetailsUsingAdminEmailidAndPassword(String emailId, String password) {
		  try {
			connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(adminLogin);
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public int updatePinAndAccountNumberAndStatusByUsingAadharNumber(int pin, int aacountNumber, long aadharNumber) {
		try {
			connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(update);
			preparedStatement.setInt(1,pin );
			preparedStatement.setInt(2, aacountNumber);
			preparedStatement.setString(3, "Accepted");
			preparedStatement.setLong(4, aadharNumber);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int deleteUserDetailsByUsingAadharNumber(long  aadharNumber) {
		try {
			connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(deleteUserData);
			preparedStatement.setLong(1,aadharNumber );
			return preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int rejectDeletionRequest(long aadharNumber) {
		try {
			connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(deletionRequest);
			preparedStatement.setString(1, "DeletionRequestRejected");
			preparedStatement.setLong(2, aadharNumber);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}

}
