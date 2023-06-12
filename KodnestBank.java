package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class KodnestBank {
	private Scanner scan=new Scanner(System.in);
    private	Connection con=null;
    public static KodnestBank ref=null;
    public static KodnestBank getInstance()
    {
    	if(ref==null)
    	{
    		ref=new KodnestBank();
    	}
    	return ref;
    }
        private KodnestBank() {
    	String path="oracle.jdbc.driver.OracleDriver";
    	String url="jdbc:oracle:thin:@//localhost:1521/XE";
    	String user="system";
    	String password="system";
    	try {
    	Class.forName(path);
	    con=DriverManager.getConnection(url,user,password);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
        @codewriter(developer="Thiyagu",task="this task for Registration")
	   void registration() {
        	try{
    			System.out.println("Enter Accno");
    			String accno=scan.next();
    			System.out.println("Enter password");
    			String pwd=scan.next();
    			System.out.println("Enter Confirm Password");
    			String cpwd=scan.next();
    			System.out.println("Enter Name");
    			String name=scan.next();
    			System.out.println("Enter Amount");
    			int amt=scan.nextInt();
    			System.out.println("Enter Age");
    			int age=scan.nextInt();
    			System.out.println("Enter Email");
    			String email=scan.next();
    			System.out.println("Enter Phone Number");
    			String phone=scan.next();
    			if(accno.length()!=10||pwd.equals(cpwd)==false||name.length()<3||amt<=0||age<18||email.length()<10||phone.length()!=10)
    			{
    			System.out.println("Registration Unsuccessful...please Retry...");	
    			}
    			else
    			{
    			String sql="insert into kodnestbank values(?,?,?,?,?,?,?)";
    			PreparedStatement ps=con.prepareStatement(sql);
    			ps.setString(1, accno);
    			ps.setString(2, pwd);
    			ps.setString(3, name);
    			ps.setInt(4, amt);
    			ps.setInt(5, age);
    			ps.setString(6, email);
    			ps.setString(7, phone);
    			int nora=ps.executeUpdate();
    			if(nora==1)
    			{
    				System.out.println("Registration Successful");
    			}
    			else
    			{
    				System.out.println("Some Issue Occured Please Contact Nearest Branch..");
    			}
    			}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}

	}
	@codewriter(developer="Thiyagu",task="this task for login")
	 void login() {
		 try {
		System.out.println("Enter Accno");
		String Accno=scan.next();
		System.out.println("Enter pwd");
		String pwd=scan.next();
		String sql="select * from kodnestbank where Accno=? and password=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, Accno);
		ps.setString(2, pwd);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			System.out.println("Login success...");
			System.out.println("Your Balance is"+rs.getInt("amount"));
		}
		else {
			System.out.println("Invalid user or Password Please Try again..");
		}
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		
	}
	@codewriter(developer="Thiyagu",task="this task for checkbalance")
	 void checkbalance() {
		 try {
				System.out.println("Enter Accno");
				String Accno=scan.next();
				System.out.println("Enter pwd");
				String pwd=scan.next();
				String sql="select * from kodnestbank where Accno=? and password=?";
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, Accno);
				ps.setString(2, pwd);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					System.out.println("Your Balance is"+rs.getInt("amount"));
				}
				else {
					System.out.println("Invalid user or Password Please Try again..");
				}
				 }
				 catch(Exception e) {
					 e.printStackTrace();
				 }
	}
	@codewriter(developer="Thiyagu",task="this task for transferamount")
	 void transferamount() {
		try {
		String sql1="Update from kodnestbank set amount=amount-? where accno=? and password=?";
		String sql2="Update from kodnestbank set amount=amount+? where accno=?";
		PreparedStatement ps1=con.prepareStatement(sql1);
		PreparedStatement ps2=con.prepareStatement(sql2);
		System.out.println("enter fAcccno");
		String faccno=scan.next();
		System.out.println("enter fpwd");
		String fpwd=scan.next();
		System.out.println("enter accno");
		String accno=scan.next();
		System.out.println("enter amoutn to transfer");
		int amt=scan.nextInt();
		ps1.setInt(1, amt);
		ps1.setString(2, faccno);
		ps1.setString(3,fpwd);
		int nora1=ps1.executeUpdate();
		ps2.setInt(1, amt);
		ps2.setString(2, accno);
		int nora2=ps2.executeUpdate();
		if(nora1==1&&nora2==1)
		{
			con.commit();
		}
		else {
			con.rollback();
		}
		}
		catch(Exception e) {
			try {
			con.rollback();
			System.out.println("some problem occured in server...don't worry your money is safe");
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		}
		@codewriter(developer="Thiyagu",task="this task for updatepassword")
	 void updatepassword() {
		 try {
				System.out.println("Enter Accno");
				String Accno=scan.next();
				System.out.println("Enter pwd");
				String pwd=scan.next();
				String sql="select * from kodnestbank where Accno=? and password=?";
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, Accno);
				ps.setString(2, pwd);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					String sql2="update kodnestbank set password=? where accno=? and password=?";
					PreparedStatement ps2=con.prepareStatement(sql2);
					System.out.println("enter new password");
					String newpassword=scan.next();
					System.out.println("enter confirm password");
					String cp1=scan.next();
					if(newpassword.equals(cp1))
					{
					ps2.setString(1, newpassword);
					ps2.setString(2, Accno);
					ps2.setString(3, pwd);
					int nora=ps2.executeUpdate();
					if(nora==1)
					{
						System.out.println(nora);
						System.out.println("password updated");
					}
					else
					{
						System.out.println("some problem occured...please try again");
						
				    }
					}
					else {
						System.out.println("new pASSWORD AND confirmed new password are not matching");
					}
				}
				else {
					System.out.println("Old Accno and Password is Invalid...");
				}
				 }
				 catch(Exception e) {
					 e.printStackTrace();
				 }
				
	}
	@codewriter(developer="Thiyagu",task="this task for updateprofile")
	 void updateprofile() {
		try {
			System.out.println("Enter Accno");
			String Accno=scan.next();
			System.out.println("Enter pwd");
			String pwd=scan.next();
			String sql="select * from kodnestbank where Accno=? and password=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, Accno);
			ps.setString(2, pwd);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				System.out.println("update your profile");
				String sql1="update kodnestbank set email=?,phone=?,age=? where accno=?";
				PreparedStatement ps1=con.prepareStatement(sql1);
				System.out.println("Enter new Age");
    			int Nage=scan.nextInt();
    			System.out.println("Enter new Email");
    			String Nemail=scan.next();
    			System.out.println("Enter new Phone Number");
    			String Nphone=scan.next();
    			ps1.setString(1, Nemail);
    			ps1.setString(2, Nphone);
    			ps1.setInt(3,Nage);
    			ps1.setString(4,Accno);
    			int nora=ps1.executeUpdate();
    			if(nora==1)
    			{
    				System.out.println("profile updated");
    			}
    			else
    			{
    				System.out.println("some problem occured...please try again");
    			}
			}
			else {
				System.out.println("Invalid user or Password Please Try again..");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@codewriter(developer="Thiyagu",task="this task for deleteaccount")
     void deleteaccount() {
		try {
			        con.setAutoCommit(false);
					System.out.println("Enter Accno");
					String Accno=scan.next();
					System.out.println("Enter pwd");
					String pwd=scan.next();
					String sql="select * from kodnestbank where Accno=? and password=?";
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setString(1, Accno);
					ps.setString(2, pwd);
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
                           String sql1="delete from kodnestbank where accno=?";
                           PreparedStatement ps1=con.prepareStatement(sql1);
                           System.out.println("enter accno");
                           String accno=scan.next();
                           ps1.setString(1, accno);
                           int nora=ps1.executeUpdate();
                           if(nora==1) {
                        	   con.commit();
                        	   System.out.println("Your Account is deleted");
                           }
                           else {
                        	   con.rollback();
                           }
					}
					else {
						System.out.println("Invalid user or Password Please Try again..");
					}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	void checkprofile() {
		try {
			System.out.println("Enter Accno");
			String Accno=scan.next();
			System.out.println("Enter pwd");
			String pwd=scan.next();
			String sql="select * from kodnestbank where Accno=? and password=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, Accno);
			ps.setString(2, pwd);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println("FOLLOWING ARE YOUR DETAILS");
				
				System.out.println("accno= "+rs.getString(1));
				System.out.println("Password= "+rs.getString(2));
				System.out.println("Name= "+rs.getString(3));
				System.out.println("Amount= "+rs.getInt(4));
				System.out.println("Age= "+rs.getInt(5));
				System.out.println("Email= "+rs.getString(6));
				System.out.println("Phone= "+rs.getString(7));
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");

			}
			else {
				System.out.println("Invalid user or Password Please Try again..");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
	   }
	}

}
