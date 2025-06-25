package com.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.DAO.BankStatementDAO;
import com.DAO.BankStatementDAOImpl;
import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOImplimentationClass;
import com.exception.BankUserException;
import com.model.BankStatement;
import com.model.BankUserDetails;

public class BankServiceImplimentationClass implements BankService{
	private static final String url="jdbc:mysql://localhost:3306/teca_63?user=root&password=sayyed";
	BankStatement bankStatement;
	Scanner scanner=new Scanner(System.in);
	BankUserDAO bankUserDAO=new BankUserDAOImplimentationClass(); //to call insert method of DAO 
	BankUserDetails loginPersonDetails;
	BankStatementService bankStatementService=new BankStatementServiceImpl(); 
	BankStatementDAO bankStatementDAO=new BankStatementDAOImpl();
//	long aadharNo;
	  @Override
	   public void forSleep(String statement) {
	    	for(int i=0;i<statement.length();i++) {
				System.out.print(statement.charAt(i));
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
	    	System.out.println();
	    }
	  @Override
	  public void userRegistration() {
		  List<BankUserDetails> allBankUserDetails =bankUserDAO.getAllBankUserDetails();
		  loginPersonDetails=new BankUserDetails();
		  System.out.println("Enter your Name");
		  String name=scanner.next();
		  loginPersonDetails.setName(name);
		  //Reading emailId
		  boolean emailIdStatus=true;
		  while(emailIdStatus) {
		  try {
			System.out.println("Enter your EmailId");
			String emailId=scanner.next();
			String subString=emailId.substring( emailId.length()-10, emailId.length());
			if(subString.equals("@gmail.com")) {
				long result=allBankUserDetails.stream().filter((email->email.getEmailId().equalsIgnoreCase(emailId))).count();
				if(result>0) {
					throw new BankUserException("EmailId already exist.. \nplease Enter another emailId");
				}
				else {
				loginPersonDetails.setEmailId(emailId);
				emailIdStatus=false;
				}
			}
			else {
				throw new BankUserException("please include @gmail.com in your EmailId \nEnter valid EmailId ");
			}
			}
		  catch(BankUserException e) {
			  System.out.println(e.getMsg());
		  }
		  }
		  
		  boolean status=true;
		  while(status) {
		    try {	  
		  System.out.println("Enter your Aadhar Number");
		  long aadharNumber=scanner.nextLong();
		  long tempAadharNumber=aadharNumber;
		  int count=0;
		  while(aadharNumber>0) {  
			  count++;
			  aadharNumber/=10;
		  }
		  if(count==12) { 
			  long aadharCount=allBankUserDetails.stream().filter((bankuser -> bankuser.getAadharNumber()==tempAadharNumber)).count();

			  if(aadharCount==0) {
				  loginPersonDetails.setAadharNumber(tempAadharNumber);
				  status=false;
			  }
			  else {
				  throw new BankUserException("Already Aadhar Number Exist");
			  }  	  
		  }
		  else {
			  throw new BankUserException("Invalid Aadhar Number");
		  }
		  }
		  catch(BankUserException e) {
			  System.out.println(e.getMsg());
		  }
		  catch(InputMismatchException e) {
			  System.out.println("Input Mismatch");
		  }
		  }	
		  boolean mstatus=true;
		  while(mstatus) {
		  try {	  
		  System.out.println("Enter your Mobile Number");
		  long mobileNumber =scanner.nextLong();
		  
		  long tempMobileNo=mobileNumber;
		  if(mobileNumber >=6000000000l && mobileNumber <= 9999999999l) {
			  int mobileNumberCount=0;
			  for (BankUserDetails bankUserDetails : allBankUserDetails) {
				if(bankUserDetails.getMobileNumber()==tempMobileNo) {
					mobileNumberCount++;
				}
			  }
				if(mobileNumberCount>0) {
					throw new BankUserException("Already Mobile Number Exist");
				}
				else {
					loginPersonDetails.setMobileNumber(tempMobileNo);
					mstatus=false;
				}
			}		  
		  else {
			  throw new BankUserException("Invalid Mobile Number");
		  }
		  }
		  catch(BankUserException e) {
			  System.out.println(e.getMsg());
		  }
		  }
	//Reading pannumber
		  boolean panNumberStatus=true;
		  while(panNumberStatus) {
			try {
				System.out.println("Enter your pan number");
				String panNumber=scanner.next();
				String validPannumber;
				String subString=panNumber.substring(0, 5);
				String subStringCaps=subString.toUpperCase();
				if(subString.equals(subStringCaps)) {
				
				String secoundSubstring=panNumber.substring(5, panNumber.length()-1);
				System.out.println();
				int secoundPart=Integer.parseInt(secoundSubstring);
				if(secoundPart%2==0) {
					
					String lastChar=panNumber.substring(panNumber.length()-1);
					String lastCharCaps=lastChar.toUpperCase();
					if(lastChar.equals(lastCharCaps)) {
						validPannumber=panNumber.toUpperCase();
						//save it
						loginPersonDetails.setPanNumber(validPannumber);
						panNumberStatus=false;
					}
					else {
						throw new BankUserException("last char must be in upper case");
					}
				}
				else {
					throw new BankUserException(" 6th to four consicutive digits must be numbers");
				}
				}
				else if(subString.equalsIgnoreCase(subStringCaps)) {
				
					throw new BankUserException("first five characters must be in upper case");
				}
				else {
					throw new BankUserException("first five digits must be characters");
				}
				}
				catch ( BankUserException e){
					System.out.println(e.getMsg());
					
				}
			catch (NumberFormatException e) {
				e.getMessage();
				System.out.println("from 6th to four consicutive digits must be numbers");
			}
		  }
		  
		  System.out.println("Enter your Address");
		  String address=scanner.next();
		  loginPersonDetails.setAddress(address);
		  System.out.println("Enter your Gender");
		  String gender=scanner.next();
		  loginPersonDetails.setGender(gender);
		  System.out.println("Enter your Amount");
		  double amount=scanner.nextDouble();
		  loginPersonDetails.setAmount(amount);
		  bankUserDAO.insertBankUserDetails(loginPersonDetails);

	  }
	@Override
	public void userLogin() {
		boolean status=true;
		while(status) {
			
		try {
		System.out.println("Enter your EmailId or Account Number  ");
		String emailIdOrAccountNumber=scanner.next();
		System.out.println("Enter Your Pin");
		int pin=scanner.nextInt();
		loginPersonDetails=bankUserDAO.checkEmailAndPinForLogin(emailIdOrAccountNumber,pin);	
		
		if (loginPersonDetails!=null) {
			boolean userStatus=true;
			while(userStatus) {
			System.out.println("Enter \n 1.For Credit \n 2.For Debit \n 3.For Check Balance \n 4.For Change Pin \n 5.For Check Statement \n 6. Request to Delete Account \n 7.Log out");
			switch (scanner.nextInt()) {
			case 1:{
				loginPersonDetails=bankUserDAO.checkEmailAndPinForLogin(emailIdOrAccountNumber,pin);
				credit(loginPersonDetails.getAmount(),loginPersonDetails.getPin());
			}
				break;
            case 2:{
            	loginPersonDetails=bankUserDAO.checkEmailAndPinForLogin(emailIdOrAccountNumber,pin);
				debit(loginPersonDetails.getAmount(),loginPersonDetails.getPin());				
            }
				break;
            case 3:{
            	loginPersonDetails=bankUserDAO.checkEmailAndPinForLogin(emailIdOrAccountNumber,pin);
				System.out.println("Your Balance is: "+loginPersonDetails.getAmount());
            }
				break;
            case 4:{
            	loginPersonDetails=bankUserDAO.checkEmailAndPinForLogin(emailIdOrAccountNumber,pin);
            	System.out.println("Enter your AccountNumber");
            	double accountNumber=scanner.nextDouble();
            	System.out.println("Enter Your OldPin");
            	int oldPin=scanner.nextInt();
            	if(accountNumber==loginPersonDetails.getAccountNumber() &&oldPin==loginPersonDetails.getPin()) {
            		System.out.println("Enter your new Desired pin");
            		int newPin=scanner.nextInt();
            		int result=updatePin(accountNumber,oldPin,newPin);
            		if(result>0) {
            			System.out.println("Pin Updated Sucesssfully..");
            		}
            		else {
            			throw new BankUserException("Server 404 error");
            		}
            	}
            	else {
            		System.out.println("your AccountNumber Or OldPin invalid");
            	} 
            }
            	break;
            case 5:{
            	loginPersonDetails=bankUserDAO.checkEmailAndPinForLogin(emailIdOrAccountNumber,pin);
            	int userId=loginPersonDetails.getId();
            	bankStatementDAO.getStatementByUsingUserId(userId);
            	
            }
            	break;
            case 6:{
            	loginPersonDetails=bankUserDAO.checkEmailAndPinForLogin(emailIdOrAccountNumber,pin);
            	System.out.println("Enter your account Number");
            	long accountNumber=scanner.nextLong();
            	System.out.println("Let's verify your pin");
            	int userPin=scanner.nextInt();
            	System.out.println("Please can you specify your reason to delete Account");
            	String reason=scanner.next();
            	if(loginPersonDetails.getAccountNumber()==accountNumber && loginPersonDetails.getPin()==userPin)
            	{
            		requestToDeleteAccount(accountNumber,userPin ,reason);
            		System.out.println("Your request is in process you will get message regarding deletion of account soon");
            	}
            	else {
            		System.out.println("Invalid credentials");
            	}
            	
            }
            	break;
            case 7:{
            	userStatus=false;
            }      
			default:
				break;
			}
			if(userStatus) {
			System.out.println("Do you want to Continue(YES/NO)");					
			if(scanner.next().equalsIgnoreCase("yes")) {
				
			}
			else 
			{
				System.out.println("Thank You visit Again....");
				userStatus=false;
				status=false;
			}
			}
			else {
				System.out.println("Thank You visit Again....");
				status=false;
			}
		}
		}
		else {
			throw new BankUserException("Invalid Credential");
		}
		}
		catch(BankUserException e) {
			System.out.println(e.getMsg());
		}
		catch(InputMismatchException e) {
			System.out.println("InputMismatch");
			scanner =new Scanner(System.in);
		}
	}
	}
	
	@Override
	public void debit(double bankAmount ,int pin) {
		boolean status=true;
		while(status) { 
			try {
		System.out.println("Enter your amount");
		double userAmount=scanner.nextDouble();
		
		if (userAmount>=0) 
		{
			if(bankAmount>=userAmount) {
				double balanceAmount=bankAmount-userAmount;
				int result=bankUserDAO.updateAmountByUsingPin(balanceAmount, pin);
				if(result>0) 
				{
					
					bankStatement=new BankStatement();
					bankStatement.setTransactionAmount(userAmount);
					bankStatement.setBalanceAmount(balanceAmount);
					bankStatement.setDateOfTransaction(LocalDate.now());
					bankStatement.setTimeOfTransaction(LocalTime.now());
					bankStatement.setTransactionType("Debit");
					bankStatement.setUserId(loginPersonDetails.getId());
					int statementDetails=bankStatementService.bankStatementDetails(bankStatement);
					if(statementDetails>0) {
						System.out.println("Amount debited Successfully....");
						System.out.println("Do you want to see your remaining balance.(YES/NO)");					
						if(scanner.next().equalsIgnoreCase("yes")) {
							System.out.println("Your remainin balance is:"+balanceAmount);
						}
						else 
						{
							System.out.println("Thank You visit Again....");
						}
					}
					else
					{
						System.out.println("Server 404 error...");
					}
					status=false;
				}
				else {
					System.out.println("Server 404 error...");	
				}
				}
			else {
				throw new BankUserException("Insufficient Amount.....");
			}
		}
		else {			
				throw new BankUserException("Invalid amount");
		}
			}
		catch(BankUserException e) {
			System.out.println(e.getMsg());
		}
	}
	}
	@Override
	public void credit(double bankAmount, int pin) {
		boolean status=true;
		while(status) { 
			try {
	
		System.out.println("Enter your Amount ");
		double userAmount=scanner.nextDouble();
		if(userAmount>=0)
		{
			double updatedAmount=bankAmount+userAmount;
			int result=bankUserDAO.updateAmountByUsingPin(updatedAmount, pin);			
			if(result>0) 
			{
				
			    bankStatement=new BankStatement();
				bankStatement.setTransactionAmount(userAmount);
				bankStatement.setBalanceAmount(updatedAmount);
				bankStatement.setDateOfTransaction(LocalDate.now());
				bankStatement.setTimeOfTransaction(LocalTime.now());
				bankStatement.setTransactionType("Credit");
				bankStatement.setUserId(loginPersonDetails.getId());
				int statementDetails=bankStatementService.bankStatementDetails(bankStatement);
				if(statementDetails>0) {
					System.out.println("Amount Credited Successfully....");
					System.out.println("Do you want to see your updated balance.(YES/NO)");
					String option=scanner.next();
					if(option.equalsIgnoreCase("yes")) {
						System.out.println("Your updated balance is:"+updatedAmount);
					}
					else {
						
					}
				}
				else {
					System.out.println("Server 404 error...");
				}
				status=false;
			}			
			else 
				{
				throw new BankUserException("Server 404 error.....");
				}
		}
		else {
			throw new BankUserException("Invalid amount");
		}
			}
			catch(BankUserException e) {
				System.out.println(e.getMsg());
			}
		}
	}
	@Override
	public int updatePin(double accountNumber, int oldPin,int newPin) {
		
		return bankUserDAO.updatePinByUsingAccountNumberAndOldpin( accountNumber,oldPin,newPin);
	}
	@Override
	public void checkStatement(int userId) {
		bankStatementDAO.getStatementByUsingUserId(userId);
		
	}
	@Override
	public int requestToDeleteAccount(long accountNumber, int pin, String reason) {
		return bankUserDAO.requestToDeleteAccount(accountNumber, pin, reason);
		
	}	
	}
