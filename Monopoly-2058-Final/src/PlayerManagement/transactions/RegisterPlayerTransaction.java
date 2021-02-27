package PlayerManagement.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import PlayerManagement.Player;

public class RegisterPlayerTransaction {
	
	public int amountPlayers = 0;
	public static List<Player> gamePlayers = new ArrayList<Player>();  //list of player objects

	public void RegisterPlayers() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the amount of players playing: ");    //getting the amount of players
		Scanner input = new Scanner(System.in);
		amountPlayers = input.nextInt();
		input.close();
		
		for (int i=1; i<amountPlayers; i++) {
			System.out.println("Enter the name of player " + i + ": ");
			Scanner nameInput = new Scanner(System.in);
			String playerName = nameInput.nextLine();
			gamePlayers.add(new Player(playerName, 1500));
			nameInput.close();
		}
	}

}
