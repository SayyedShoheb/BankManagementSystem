package com.Service;
import java.util.List;
import java.util.Random;
import java.util.Scanner;import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.DAO.AdminDAO;
import com.DAO.AdminDAOImpl;
import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOImplimentationClass;
import com.exception.BankUserException;
import com.model.BankUserDetails;
public class AdminServiceImpl implements AdminService{
	Scanner scanner=new Scanner(System.in);
	AdminDAO adminDAO=new AdminDAOImpl();
	BankUserDAO bankUserDAO=new BankUserDAOImplimentationClass();
	int count=0;
	List<BankUserDetails> allUserDetails;
	@Override
	public void adminLogin() {
		List<BankUserDetails> allUserDetails=bankUserDAO.getAllBankUserDetails();
		System.out.println("Enter your Emailid");
		String emailId =scanner.next();
		System.out.println("Enter your Password");
		String password=scanner.next();
		boolean adminStatus=true;
		while(adminStatus) {
		try {
		if(adminDAO.getAdminDetailsUsingAdminEmailidAndPassword(emailId, password)) {
			System.out.println("Enter \n 1.To Get All user Details \n 2.To Get All Account Requests \n 3.To get All requests for deletion of account \n 4.exit");
			
			switch(scanner.nextInt())
			{
			case 1: {
			allUserDetails.forEach((user)->{
					
					System.out.println("S.no:"+user.getId());
					System.out.println("UserName:"+user.getName());
					System.out.println("Mobile No:"+user.getMobileNumber());
					System.out.println("Aadhar Number"+user.getAadharNumber());
					System.out.println("******-------------******");
					
				});
				
			}	
			break;
		  case 2:{
			allAccountRequestDetsils();
		  }
		  break;
		  case 3: {
			  allDeletionAccountRequests();
		  }
		  break;
		 case 4:{
			  adminStatus=false;
			  return;
		  }
		 }
			
		}
		else {
			throw new BankUserException("Admin Login failed");
		}
		}catch(BankUserException e) {
			System.out.println(e.getMsg());
		}
		catch(NullPointerException e) {
			System.out.println("There are no pending requests");
		}
		System.out.println("Do you want to continue Operations (YES/NO)");
		if(scanner.next().equalsIgnoreCase("yes")) {
			
		}
		else {
			adminStatus=false;
		}
		}
	}
	@Override
	public void allAccountRequestDetsils() {
		boolean adminStatus=true;
		while(adminStatus) {
		allUserDetails=bankUserDAO.getAllBankUserDetails();
		
		List<BankUserDetails> list=allUserDetails.stream().filter((user->user.getStatus().equalsIgnoreCase("pending"))).collect(Collectors.toList());
	    if(list.isEmpty()) {
	    	System.out.println("There are no pending requests....");
	    	adminStatus=false;
	    	return;
	    }
	    else {
		list.forEach((user)->{
			
			System.out.println("S.no:"+count++);
			System.out.println("UserName:"+user.getName());
			System.out.println("Mobile No:"+user.getMobileNumber());
			System.out.println("Aadhar Number"+user.getAadharNumber());
			
		});
		System.out.println("Enter S.No to Select User");
		BankUserDetails bankUserDetails=list.get(scanner.nextInt()-1);//index start from 0 ,get(Index)
		System.out.println(bankUserDetails);
		System.out.println("Enter \n 1.Accept \n 2.Reject");
		switch (scanner.nextInt()) {
		case 1:
			acceptRequest(bankUserDetails.getAadharNumber());
			break;
		case 2:{
			int result=rejectRequest(bankUserDetails.getAadharNumber());
			if(result>0) {
				System.out.println("Data Removed Successsfully...");
			}
			else {
				System.out.println("Server errror");
			}
		}
			break;
		default:
			break;
		}
		}
		}
		System.out.println("Do you want to continue (YES/NO)");
		if(scanner.next().equalsIgnoreCase("yes")) {
			
		}
		else {
			adminStatus=false;
		}
		}
	@Override
	public void acceptRequest(long aadharNumber) {
		Random random=new Random();
		int pin=random.nextInt(10000);
		if(pin<1000) {
			pin=pin+10000000;
		}
		int accountNumber=random.nextInt(1000000);
		if(accountNumber<1000000) {
			accountNumber=accountNumber+10000000;
		}
		int result=adminDAO.updatePinAndAccountNumberAndStatusByUsingAadharNumber(pin, accountNumber, aadharNumber);
		System.out.println(result);
		if(result!=0) {
			System.out.println("Request Accepted");
			System.out.println("Account Number:"+accountNumber);
			System.out.println("Pin:"+pin);
			System.out.println("******-------------******");
		}
		else {
			System.out.println("Server error  404");
		}
	}
	@Override
	public int rejectRequest(long aadharNumber) {
		return adminDAO.deleteUserDetailsByUsingAadharNumber(aadharNumber);
		
	}
	@Override
	public void allDeletionAccountRequests() {
		boolean status=true;
		while(status) {
		allUserDetails=bankUserDAO.getAllBankUserDetails();
		
	List<BankUserDetails> list=allUserDetails.stream().filter((user->user.getStatus().equalsIgnoreCase("DeleteRequest"))).collect(Collectors.toList());
		if(list.isEmpty()) {
			System.out.println("There are no pending requests for deletion of account");
			status=false;
			return;
		}
		else {
			list.forEach((user)->{
				System.out.println("S.no: "+count++);
				System.out.println("Username: "+user.getName());
				System.out.println("mobileNumber: "+user.getMobileNumber());
				System.out.println("Aadhar number: "+user.getAadharNumber());
			});
		}		
		
		System.out.println("Enter S.No to Select User");
		BankUserDetails bankUserDetails=list.get(scanner.nextInt()-1);//index start from 0 ,get(Index)
		System.out.println(bankUserDetails);
		System.out.println("Enter \n 1.Accept \n 2.Reject");
		switch (scanner.nextInt()) {
		case 1:{
			int result=approveRequest(bankUserDetails.getAadharNumber());
			if(result>0) {
				System.out.println("Data Removed Successsfully...");
			}
			else {
				System.out.println("Server errror");
			}
		}
			break;
		case 2:{
			int result=rejectDeletionRequest(bankUserDetails.getAadharNumber());
			if(result>0) {
				System.out.println("data updated successfully");
			}
			else {
				System.out.println("Server error");
			}
		}
			break;
		default:
			break;
		}
	}
	}
	@Override
	public int approveRequest(long aadharNumber) {
		return adminDAO.deleteUserDetailsByUsingAadharNumber(aadharNumber);
		
	}
	@Override
	public int rejectDeletionRequest(long aadharNumber) {
		return adminDAO.rejectDeletionRequest(aadharNumber);
		
	}

}
