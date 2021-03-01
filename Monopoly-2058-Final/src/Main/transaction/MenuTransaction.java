package Main.transaction;

import Main.Menu;
import PlayerManagement.transactions.HighscoresTransaction;
import PlayerManagement.transactions.RegisterPlayerTransaction;

public class MenuTransaction {
	
	static RegisterPlayerTransaction RP = new RegisterPlayerTransaction();
	static HighscoresTransaction HS = new HighscoresTransaction();
	static HowToPlayTransaction HTP = new HowToPlayTransaction();
	
	public void loadMenu() {
		int choice = 0;
		String[] options = {"New Game", "Highscores", "How To Play", "Exit Application"};
		String title = "Actions:";
		Menu m = new Menu(title, options);
		m.display();
		choice = m.getChoice();
		if(choice != 4) {
			processChoice(choice);
			}
		else if (choice == 4) {
			System.out.println("game closing now");
			System.exit(0);;
		}
		// do {
		// 	m.display();
		// 	choice = m.getChoice();
		// 	if(choice != 4) {
		// 		processChoice(choice);
		// 	}
		// }
		// while(choice != 4);
		// System.out.println("(WARNING) Applicaiton is closing");
	}
	
	public static void processChoice(int choice) {
		switch(choice) {
		case 1:
			RP.RegisterPlayers();			
			break;
		case 2:
			HS.LoadHighscores();
			break;
		case 3:
			HTP.ShowRules();
			break;
		case 4:
			break;
		default:
			System.out.println("That is not a valid option. Please choose another number.");
			break;
		}
	}
}
