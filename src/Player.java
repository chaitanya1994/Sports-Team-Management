import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;


public class Player {
	
	Scanner a=new Scanner(System.in);
	int opt;
	
	private String name;
	private String position;
	private int jersey;
	private int age;
	
	public void player_in(int tid)
	
	{
		a.nextLine();
		System.out.print("Enter Name of the Player : ");
		name = a.nextLine();
		
		System.out.print("Jersey Number of the Player : ");
		jersey = a.nextInt();
		
		a.nextLine();
		System.out.print("Preferred Position of Player : ");
		position = a.nextLine();
		
		System.out.print("Age of the player : ");
		age = a.nextInt();
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
			PreparedStatement ps = connect.prepareStatement("insert into players values (?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,position);
			ps.setInt(3,jersey);
			ps.setInt(4,age);
			ps.setInt(5, tid);
			
			
			ps.executeUpdate();
			
			System.out.println("\nPlayer Added Successfully\n");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
	}
	
	public void player_out(int opt)
	{
		int flag = 0;
		
		int n;
		Scanner s = new Scanner(System.in);
		
		System.out.print("\n\nEnter Jersey Number of the Player to be Searched : ");
		n=s.nextInt();
	try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
			PreparedStatement ps = connect.prepareStatement("select * from players where jersey = ? and teamid = ?");
			ps.setInt(1,n );
			ps.setInt(2, opt);
			
			ResultSet rs = ps.executeQuery();
			flag =0;
			while(rs.next())
			{
				flag++;
				name = rs.getString(1);
				position = rs.getString(2);
				jersey = rs.getInt(3);
				age = rs.getInt(4);
				break;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	if(flag==1)
	{
		
		System.out.print("\n\n\n\n");
		System.out.println("Enter Name of the Player : " + name);
		System.out.println("Preferred Position of Player : " + position);
		System.out.println("Jersey Number of the Player : " + jersey);
		System.out.println("Age of the player : " + age);
	}
	else
		System.out.print("\nNo Record Found\n\n");
	
	}
	
	public void player_menu(int tid)
	{
		do{

			
		System.out.println("\n\n1. Display All \n2. Search player \n3. Add a Player \n4. Delete A Player \n5. Previous Menu\n\n");
		opt = a.nextInt();
		switch(opt)
		{
		
		case 1 : this.display_player(tid);
					break;
		case 2 : this.player_out(tid);
					break;
		case 3 : this.player_in(tid);
				break;
		case 4 : this.deleteplayer(tid);
				break;
		case 5 : System.out.print("\n\n\n\n\n\n\n\n");return;
		
		default : System.out.print("\nWrong Choice....choose again\n");
		}
		}while(opt!=5);
	}
	
	public void display_player(int opt)
	{
		int flag=0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
			PreparedStatement ps = connect.prepareStatement("select * from players where teamid = ?");
			ps.setInt(1, opt);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				flag++;
				name = rs.getString(1);
				position = rs.getString(2);
				jersey = rs.getInt(3);
				age = rs.getInt(4);
				
				System.out.print("\n\n");
				System.out.println("Enter Name of the Player : " + name);
				System.out.println("Preferred Position of Player : " + position);
				System.out.println("Jersey Number of the Player : " + jersey);
				System.out.println("Age of the player : " + age);
			}
			if(flag==0)
			
				System.out.println("\nNo Players enrolled in the team yet, add players first.\n");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();}
	}
		
		
		public void deleteplayer(int opt)
		{
			
			int n;
			Scanner s = new Scanner(System.in);
			
			System.out.print("\n\nEnter Jersey Number of the Player to be Deleted : ");
			n=s.nextInt();
		
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
				PreparedStatement ps = connect.prepareStatement("delete from players where teamid = ? and jersey = ?");
				ps.setInt(1,opt );
				ps.setInt(2, n);
				
				if(ps.executeUpdate()!=0)
				{
				
				System.out.print("\n\nPlayer with Jersey Number " + n + "  Deleted Successfully \n\n");
				}
				else
					System.out.println("\nNo Record Found");
				
				} catch (Exception e) {
				
				e.printStackTrace();
				
				// TODO: handle exception
			}
			
		}
	
	
	
	//java tested
	
}
