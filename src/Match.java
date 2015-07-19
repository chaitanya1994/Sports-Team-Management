import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class Match {

	Scanner s = new Scanner(System.in);
	
	int opt=0,flag=0;
	private String date;
	private String opponent;
	private String result;
	private String score;
	
	
	public void  match_in(int tid)
	{
		System.out.print("Match was played against : ");
		s.nextLine();
		opponent = s.nextLine();
		System.out.print("Match was played on (DD/MM/YY): ");
		date = s.nextLine();
		System.out.print("Result of the Match (Lose/Win/Draw) : ");
		result = s.nextLine();
		System.out.print("Final Score of the Match : ");
		score = s.nextLine();
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
			PreparedStatement ps = connect.prepareStatement("insert into matches (dated, opponent, result, score,teamid) values (?,?,?,?,?)");
			ps.setString(1,date);
			ps.setString(2,opponent);
			ps.setString(3,result);
			ps.setString(4,score);
			ps.setInt(5, tid);
			
			
			ps.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
	}
	
	public void match_out()
	{
		
try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","","");
			PreparedStatement ps = connect.prepareStatement("select dated,opponent, result, score from matches where teamid = ? order by matchid");
			ps.setInt(1,opt );
			
			ResultSet rs = ps.executeQuery();
			flag =0;
			while(rs.next())
			{
				flag++;
				date = rs.getString(1);
				opponent = rs.getString(2);
				result = rs.getString(3);
				score = rs.getString(4);
				break;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// TODO: handle exception
		}

	if(flag==1)
	{
		System.out.println("\n\n\nMatch was played against : " + opponent);
		System.out.println("Match was played on : " + date);
		System.out.println("Result of the Match (Lose/Win/Draw) : " + result);
		System.out.println("Final Score of the Match : " + score);
	}
	else
		System.out.println("\nNo Records Match the Search constraints\n");
}
	
	
	public void match_menu(int tid)
	{
		do
		{
			System.out.println("\n\n1.List History\n2.Played A Match\n\n3.Previous Menu \n\n\nEnter Choice : ");
			opt=s.nextInt();
			
			switch(opt)
			{
			case 1 : this.match_out();break;
			case 2 : this.match_in(tid);break;
			case 3 :break;
			default: System.out.println("\n\nSorry....Wrong Input\n");
			}
			}while(opt!=3);

	
	}
}