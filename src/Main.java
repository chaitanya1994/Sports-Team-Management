import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
	
	Player p = new Player(); 
	Match m = new Match();
	Team t = new Team();
	Titles ti = new Titles();
	int opt=0;
	Scanner s = new Scanner(System.in);
		do {
			System.out.print("SPORTS CLUB DATABASE\n ");
			System.out.print("--------------------\n\n");
			System.out.println("1.Get Started\n\n2.Be Back Later");
			opt = s.nextInt();
			switch (opt) {
			case 1:
				t.team_main();
				break;
			case 2:
				System.out.println("\n\n\nHOPE YOU HAD A GOOD TIME. BYE.");
				System.exit(0);
				break;
			default:
				System.out
						.print("\n\nOOPS, You are trying to opt for an invalid option\n\tPlease Try Again");
			}
		} while(opt!=2);
	}
}
