package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.BankUserDetails;

public class BankUserDAOImplimentationClass implements BankUserDAO{

	private static final String url="jdbc:mysql://localhost:3306/teca_63?user=root&password=sayyed";
	private static final String insert="insert into bankuserdetails (Name, EmailId, AadharNumber, MobileNumber,PanNumber, Address, Gender, Amount, Status)"+
   "values(?,?,?,?,?,?,?,?,?)";
	Connection connection;
	private static final String select_all="select * from bankuserdetails";
	private static final String userLogin="select * from bankuserdetails where((EmailId=? or AccountNumber=?) and Pin=?)";
	private static final String updateAmount="update bankuserdetails set Amount=? where Pin=?";
	private static final String updatePin="update bankuserdetails set pin=? where Pin=? and AccountNumber=?";
	private static final String deleteAccount="update bankuserdetails set Status=? where accountNumber=? and pin=?";
	@Override
	public void insertBankUserDetails(BankUserDetails bankUserDetails) {
		try {
			 connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			
			preparedStatement.setString(1,bankUserDetails.getName());
			preparedStatement.setString(2, bankUserDetails.getEmailId());
			preparedStatement.setLong(3, bankUserDetails.getAadharNumber());
			preparedStatement.setLong(4, bankUserDetails.getMobileNumber());
			preparedStatement.setString(5, bankUserDetails.getPanNumber());
			preparedStatement.setString(6, bankUserDetails.getAddress());
			preparedStatement.setString(7, bankUserDetails.getGender());
			preparedStatement.setDouble(8, bankUserDetails.getAmount());
			preparedStatement.setString(9, "Pending");
			int result=preparedStatement.executeUpdate();
			if(result>0) {
				System.out.println("Registred Successfully.......");
			}
			else {
				System.out.println("Server 404 error.......");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Override
	public void getUserDetailsByUsingEmailAndPin() {
		     String select ="select * from bankuserdetails where EmailId=? and Pin=?";
		try {
			
			 connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(select);
			preparedStatement.setString(1,"" );
			preparedStatement.setInt(2,0 );
		    ResultSet resultSet=preparedStatement.executeQuery();
		    if(resultSet.next()) {
		    	
		    	System.out.println(resultSet.getInt(1));
		    	System.out.println(resultSet.getString(2));
		    	System.out.println(resultSet.getString(3));
		    	System.out.println();
		    }
		    else {
		    	System.out.println("No Data found");
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public List<BankUserDetails> getAllBankUserDetails() {
		try {
			 connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(select_all);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<BankUserDetails> list=  new ArrayList<BankUserDetails>();
			if(resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					BankUserDetails bankUserDetails=new BankUserDetails();
					bankUserDetails.setId(resultSet.getInt("id"));
					bankUserDetails.setName(resultSet.getString("Name"));
					bankUserDetails.setEmailId(resultSet.getString("EmailId"));
					bankUserDetails.setAadharNumber(resultSet.getLong("AadharNumber"));
					bankUserDetails.setMobileNumber(resultSet.getLong("MobileNumber"));
					bankUserDetails.setPanNumber(resultSet.getString("PanNumber"));
					bankUserDetails.setAddress(resultSet.getString("Address"));
					bankUserDetails.setGender(resultSet.getString("Gender"));
					bankUserDetails.setAmount(resultSet.getDouble("Amount"));
					bankUserDetails.setStatus(resultSet.getString("Status"));
					list.add(bankUserDetails); 
				}
			return list;
		}
			else {
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public BankUserDetails checkEmailAndPinForLogin(String emailIdOrAccountNumber, int pin) {
		List<BankUserDetails> allUserDetails=getAllBankUserDetails();

		try {
			 connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(userLogin);
			preparedStatement.setString(1, emailIdOrAccountNumber);
			preparedStatement.setString(2, emailIdOrAccountNumber);
			preparedStatement.setInt(3, pin);
			
			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				BankUserDetails bankUserDetails=new BankUserDetails();
				bankUserDetails.setAadharNumber(resultSet.getLong("AadharNumber"));
				bankUserDetails.setAccountNumber(resultSet.getLong("AccountNumber"));
				bankUserDetails.setId(resultSet.getInt("id"));
				bankUserDetails.setEmailId(resultSet.getString("EmailId"));
				bankUserDetails.setPin(resultSet.getInt("Pin"));
				bankUserDetails.setAmount(resultSet.getDouble("Amount"));
				return bankUserDetails;		
		    	}
			else {
				return null;
			}
		}		  
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	@Override
	public int updateAmountByUsingPin(double balanceAmount, int pin) {
		try {
			//update bankuserdetails set Amount=? where Pin=?";
			 connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(updateAmount);
			preparedStatement.setDouble(1, balanceAmount);
			preparedStatement.setInt(2, pin);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int updatePinByUsingAccountNumberAndOldpin(double accountNumber, int oldPin,int newPin) {
		try {
			//update bankuserdetails set pin=? where Pin=? and AccountNumber=?";
			connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(updatePin);
			preparedStatement.setInt(1, newPin);
			preparedStatement.setInt(2,  oldPin);
			preparedStatement.setDouble(3,accountNumber );
			return preparedStatement.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int requestToDeleteAccount(long accountNumber, int pin, String reason) {
		try {
			//update bankuserdetails set Status=? where accountNumber=? and pin=?";
			connection =DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(deleteAccount);
			preparedStatement.setString(1, "DeleteRequest");
			preparedStatement.setLong(2, accountNumber);
			preparedStatement.setInt(3, pin);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
		
	}
	
	

}
