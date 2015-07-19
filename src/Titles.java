import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class Titles {
	
	Scanner s = new Scanner(System.in);
	int opt;
 
	private String title;
	private String year;
	
	public void title_in(int tid)
	{
		System.out.print("Title Secured : ");
		s.nextLine();
		title = s.nextLine();
		System.out.print("Secured in the year : ");
		year = s.nextLine();
		
try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
			PreparedStatement ps = connect.prepareStatement("insert into titles (tname, year,teamid) values (?,?,?)");
			ps.setString(1,title);
			ps.setString(2,year);
			ps.setInt(3,tid);
		
			
			
			ps.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
		System.out.println("\n\nRecord Inserted Successfully");
		System.out.println("------------------------------\n\n");
		
	}
	
	public void title_out(int opt)
	{
		int flag=0;
		
try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
			PreparedStatement ps = connect.prepareStatement("select tname,year from titles where teamid = ? order by year");
			ps.setInt(1,opt );
			
			ResultSet rs = ps.executeQuery();
			flag =0;
			while(rs.next())
			{
				flag++;
				title= rs.getString(1);
				year = rs.getString(2);
				break;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// TODO: handle exception
		}

	if(flag!=0)
	{
		
		System.out.println("Title Secured : " + title);
		System.out.println("Secured in the year : " + year);
	}
	else
		System.out.println("\n\nNo Record Found for the corresponding Team");

}
	
	
	public void titles_menu(int tid)
	{
		do
		{
			System.out.println("\n1.List All Titles\n2.Add a Title\n\n3.Previous Menu\n\nEnter Choice : ");
			opt = s.nextInt();
			
			switch(opt)
			{
			case 1 : this.title_out(tid);break;
			case 2 : this.title_in(tid);break;
			case 3 : break;
			default : System.out.print("\n\nSorry, You Are Trying To Access Something Inaccessible \n");
			}
			
		}while(opt!=3);
		
	}
}
