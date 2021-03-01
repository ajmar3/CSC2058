package Main.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import PlayerManagement.Player;
import PlayerManagement.transactions.RegisterPlayerTransaction;
import PlayerManagement.transactions.PlayerRollTransaction;
import Dice.Dice;
import TileManagement.LoadTileTransaction;

public class GameTransaction {
	
	public List<Player> gamePlayers = RegisterPlayerTransaction.gamePlayers; //getting player list from registerPlayerTransactions

	private static Dice currentRoll;

	private static Scanner input = new Scanner(System.in);
	

	public void turn() {
		
		PlayerRollTransaction playerRoll = new PlayerRollTransaction();

		for (int i=0; i<gamePlayers.size(); i++) {

			//need clarification on how the dice system works. does each player have their own set of dice??****
			currentRoll = playerRoll.roll(gamePlayers.get(i)).get(i);
			
			//getting a temp value for the index the player is moved to
			int temp = currentRoll.getDice1() + currentRoll.getDice2() + LoadTileTransaction.gameTiles.indexOf(gamePlayers.get(i).getCurrentTile()); 

			//setting new currentTile for given player
			gamePlayers.get(i).setCurrentTile(LoadTileTransaction.gameTiles.get(temp));

			System.out.println(gamePlayers.get(i).getName() + " has landed on " + gamePlayers.get(i).getCurrentTile().name); //output show what tile player now on

			//ending turn sequence
			System.out.println(gamePlayers.get(i).getName() + ", press the enter button to end your turn");
            input.nextLine();
            System.out.println("\n"+"#########################################################################"+"\n");
		}

	}
	
	public void BuyProperty() {
		
	}
	public void SellProperty() {
		
	}
	public void TradeProperty() {
		
	}
	public void buySafariPark() {

	}
	public void payRent() {

	}

}
