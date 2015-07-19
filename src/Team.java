import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class Team {

	Scanner s = new Scanner(System.in);
	int opt,opt_team=0;
	Player p = new Player();
	private int tid=-1;
	private String tname;
	
	
	Titles t = new Titles();
	Match m = new Match();
	
	
	public void team_in()
	{
		System.out.print("\n\nEnter Team Name : ");
		s.nextLine();
		tname = s.nextLine();
	
		
try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
			PreparedStatement ps = connect.prepareStatement("insert into teams (tname) values (?)");
			ps.setString(1,tname);
			
			ps.executeUpdate();
			
			System.out.println("\nTeam Added\n----------\n\n\n");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// TODO: handle exception
		}}
	
	public void team_out()
	{
		System.out.println("Name of the team : " + tname);
		System.out.println("Team ID : " +tid);
	}

	
	public void team_main()
	{
		int opt1;
	
		
		System.out.println("\n\n1. Select A Team\n\n2.Add a Team ");
		opt1 = s.nextInt();
		if(opt1==1)
		{
			System.out.print("TEAMS ENROLLED \n");
			System.out.println("----------------");
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
				PreparedStatement ps = connect.prepareStatement("select * from teams order by tid ");
				
				
				
				ResultSet rs = ps.executeQuery();
				
				int flag=-1;
				while(rs.next())
				{
					flag++;
					tid= rs.getInt(1);
					tname = rs.getString(2);
					System.out.println(tid + "." +tname);
					
			
				}
				
				if(flag==-1)
				{
					System.out.println("\n\n\nNo Teams Record in the DataBase. Start Adding your Teams NOW !!! ");
				}
				

				
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				// TODO: handle exception
			}
			
			System.out.print("\n\nEnter Team ID to Select : ");
			tid = s.nextInt();
			
			
			do
			{
			
			
			System.out.println("\n\nTeam "+ tid + " Selected ");
			System.out.println("---------------------");
			System.out.println("\n\n1.Player Database\n2.Match History\n3.Title Secured\n4.Previous Menu\n\nEnter Choice : ");
			
			opt_team=s.nextInt();
			switch(opt_team)
			{
			case 1 : p.player_menu(tid); break;
			case 2 : m.match_menu(tid);break;
			case 3 : t.titles_menu(tid);break;
			case 4 : break;
			default : System.out.println("\nWrong Input, Try Again.");
			}
			
			}while(opt_team!=4);
		}
		if(opt1==2)
			this.team_in();
		}

	}
	

