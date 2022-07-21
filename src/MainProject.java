import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import oracle.jdbc.proxy.annotation.Methods;




public class MainProject {

	public static void main(String[] args) {
		

	
		
		Scanner sc=new Scanner(System.in);
		String name="A",city="S";
		int accno,amt=0;
		
		ArrayList<Account> list =new ArrayList<>();
		
		System.out.println("select task ");
        System.out.println("1. Open Account");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Balance Enquiry");
		System.out.println("5. List All");
		System.out.println("6. Exit");

		int choice;
		boolean found;
		do
		{
		System.out.print("Enter Choice here");
		 choice =sc.nextInt();
		switch(choice)
		{
		case 1:
			System.out.println("---Account Open---");
			System.out.print("Enter name");
			name =sc.next();
			System.out.print("Enter city");
			city=sc.next();
			System.out.println("Enter amount");
			amt=sc.nextInt();
			Account acc=new Account(name,city,amt);
			list.add(acc);
			System.out.println("Account Opened Succesfuly");
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url="jdbc:oracle:thin:@localhost:1521:orcl";
				String username="scott";
				String Password="tiger";
						
				Connection con=DriverManager.getConnection(url,username,Password);
				
				Statement stmt=con.createStatement();
				
				String sql = "INSERT INTO Bank VALUES("+acc.getAccno()+",'"+acc.getName()+"','"+acc.getCity()+"',"+acc.getBal()+")";
				stmt.executeUpdate(sql);
	     
				
				
			}
			 catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("---Account Deposit---");
			found =false;
			System.out.println("Enter Account number");
			accno=sc.nextInt();
			for(Account ac:list)
			{
				if(accno==ac.getAccno())
				{
					found =true;
					System.out.println("Customer name "+ac.getName());
					System.out.println("Customer City "+ac.getCity());
					System.out.println("Customer Account Balance "+ac.getBal());
					
					System.out.println("\nEnter amount to deposit");
					amt=sc.nextInt();
					ac.setBal(ac.getBal()+amt);
					System.out.println("Deposit Successfully");
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						String url="jdbc:oracle:thin:@localhost:1521:orcl";
						String username="scott";
						String Password="tiger";
								
						Connection con=DriverManager.getConnection(url,username,Password);
						
						Statement stmt=con.createStatement();
						
						String sql = "UPDATE bank SET amt = '"+ac.getBal()+"' where AccountNumber ='"+ac.getAccno()+"'";
						stmt.executeUpdate(sql);
			     
						
						
					}
					 catch (ClassNotFoundException e) {
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					System.out.println("Customer Account Balance "+ac.getBal());
					break;
					
				}
			}
			if(!found)
			{
				System.out.println("Invalid Account Number");
			}
			
			break;
		case 3:
			System.out.println("---Account withdraw---");
			found =false;
			System.out.println("Enter Account number");
			accno=sc.nextInt();
			for(Account ac:list)
			{
				if(accno==ac.getAccno())
				{
					found =true;
					System.out.println("Customer name "+ac.getName());
					System.out.println("Customer City "+ac.getCity());
					System.out.println("Customer Account Balance "+ac.getBal());
					
					System.out.println("\nEnter amount to withdraw");
					amt =sc.nextInt();
					if(amt<=ac.getBal())
					{
						ac.setBal(ac.getBal()-amt);
						System.out.println("withdraw Successfully");
					}
					else {
						System.out.println("Insufficient Balance!");
					}
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						String url="jdbc:oracle:thin:@localhost:1521:orcl";
						String username="scott";
						String Password="tiger";
								
						Connection con=DriverManager.getConnection(url,username,Password);
						
						Statement stmt=con.createStatement();
						
						String sql = "UPDATE bank SET amt = '"+ac.getBal()+"' where AccountNumber='"+ac.getAccno()+"'";
						stmt.executeUpdate(sql);
			     
						
						
					}
					 catch (ClassNotFoundException e) {
						
						e.printStackTrace();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				
					
					break;
				}
			}
			if(!found)
			{
				System.out.println("Invalid Account Number");
			}
			
			break;
		case 4:
			System.out.println("---Account Balance enquiry---");
			found =false;
			System.out.println("Enter Account number");
			accno=sc.nextInt();
			for(Account ac:list)
			{
				if(accno==ac.getAccno())
				{
					found =true;
					System.out.println("Customer name "+ac.getName());
					System.out.println("Customer City "+ac.getCity());
					System.out.println("Customer Account Balance "+ac.getBal());
					
				}
			}
			if(!found)
			{
				System.out.println("Invalid Account Number");
			}
			break;
		case 5:
			System.out.println("---List All Accounts---");
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url="jdbc:oracle:thin:@localhost:1521:orcl";
				String username="scott";
				String Password="tiger";
						
				Connection con=DriverManager.getConnection(url,username,Password);
				
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from bank");
				while(rs.next())
				{
					System.out.println("Accountnumber:"+rs.getString(1)+"ename:"+rs.getString(2)+"City:"+rs.getString(3)+"Amount:"+rs.getString(4));
				}
	     
				
				
			}
			 catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
			break;
		case 6:
			System.out.println("---Thanks---");
			break;
			
		default:	
			System.out.println("---Invalid choice---");
			break;
		}
		
		}while(choice!=6);
		
	}
	
}
