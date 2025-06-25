package com;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Service.BankService;
import com.Service.BankServiceImplimentationClass;
import com.exception.BankUserException;
import com.Service.*;

public class App 
{
	static BankServiceImplimentationClass service = new BankServiceImplimentationClass();
    static AdminService adminService=new AdminServiceImpl();
    public static void main( String[] args )
    {
    	
        String welcome="*****-----WelCome to Shoheb Bank-----*****";
        service.forSleep(welcome);
        Scanner scanner=new Scanner(System.in);
        boolean status=true;
        while(status) {
        try {
        	System.out.println("Enter \n 1 For User Login \n 2 For Admin Login \n 3 For User Registration");
        switch (scanner.nextInt()) {
		case 1:{
			service.forSleep("***---User Login---***");
			service.userLogin();
			break;
		}
			
	    case 2:{
	    	service.forSleep("***--- Admin Login---***");
	    	adminService.adminLogin();
	    }
			break;
	    case 3:{
	    	service.forSleep("***---User Registration---***");
	    	service.userRegistration();
	    }
		break;
		default:System.out.println("Invalid Input");
		
        }
        }
        catch(InputMismatchException e) {
        	e.printStackTrace();
        }
        
        System.out.println("Do you want to Continue (Yes/No)");
		if(scanner.next().equalsIgnoreCase("Yes")) {	
		}
		else {
			status=false;
			service.forSleep("***---Thank you Visit Again---***");
		}
        }
        
        }
    
    }
    

