package jdbc;

import java.util.Scanner;

public class KodnestUser {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		KodnestBank kb= KodnestBank.getInstance();
		while(true)
		{
			System.out.println("Welcome to KodnestBank");
			System.out.println("press 1------>Registration");
			System.out.println("press 2------>Login");
			System.out.println("press 3------>Check Balance");
			System.out.println("press 4------>Transfer Amount");
			System.out.println("press 5------>Update Password");
			System.out.println("press 6------>Update Profile");
			System.out.println("press 7------>Delete Account");
			System.out.println("press 8------>check Balance");
			System.out.println("press 9------>stop");
			System.out.println("Enter your choice");
			int choice=scan.nextInt();
			switch(choice) {
			case 1:kb.registration();
			       break;
			case 2:kb.login();
		       break;
			case 3:kb.checkbalance();
		       break;
			case 4:kb.transferamount();
		       break;
			case 5:kb.updatepassword();
		       break;
			case 6:kb.updateprofile();
		       break;
			case 7:kb.deleteaccount();
		       break;
			case 8:kb.checkbalance();
		    default :
		    	System.out.println("Thank for using the kodnestbank");
		    	System.exit(0);
			}
		}

	}

}
