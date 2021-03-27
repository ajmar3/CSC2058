package Main.transaction;

import java.util.Scanner;

public class HowToPlayTransaction {

	static MenuTransaction m = new MenuTransaction();

	private static Scanner input = new Scanner(System.in);

	public void ShowRules() {

		System.out.print("\nWelcome to EcoNopoly! \n"
				+ "This game is inspired by monopoly but with an environmental twist! The aim of the game is to make as much money as you can while you build conservation parks and safari parks! \n"
				+ "- Here is a quick explanation on how the game works: \n \n"
				+ "- Select 'start a new game' in the opening menu and follow the prompts. \n"
				+ "- Each player starts the game with £1500 and they all take turns on the keyboard \n"
				+ "- When it's your turn you can decide how you want to use your resources but becareful, you dont want to run out! \n"
				+ "- You can choose to invest in new conservation parks for animals and rainforests, and if you have enough conservation parks you can build a safari. \n"
				+ "- When you land on another players park or safari you have to pay them rent to stay there \n"
				+ "- If you are bad, you can be sent to jail! If this happens you can pay the fine to get out or try to roll doubles \n"
				+ "- Landing on a chance tile can be good or bad! You could win money, be sent to jail or anything in between, it just depends how lucky you are! \n \n"
				+ "- We hope you enjoy you game of EcoNopoly, always remember the most important rules: \n 1) Have fun \n 2) Protect the environment \n \n");

		System.out.println("Press enter to go back to main menu.");
		input.nextLine();
		m.loadMenu();

	}

}
