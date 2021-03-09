package Main.transaction;

import java.util.List;
import java.util.Scanner;

import PlayerManagement.Player;
import PlayerManagement.transactions.RegisterPlayerTransaction;
import PlayerManagement.transactions.PlayerPayTransaction;
import PlayerManagement.transactions.PlayerRollTransaction;
import Dice.Dice;
import TileManagement.ChanceTileImpl;
import TileManagement.LoadTileTransaction;
import TileManagement.Tile;

public class GameTransaction {

	public List<Player> gamePlayers = RegisterPlayerTransaction.gamePlayers; // getting player list from
																				// registerPlayerTransactions

	private static Dice currentRoll;

	private static Scanner input = new Scanner(System.in);

	public void turn() {

		PlayerRollTransaction playerRoll = new PlayerRollTransaction();

		for (int i = 0; i < gamePlayers.size(); i++) {

			currentRoll = playerRoll.roll(gamePlayers.get(i)).get(i);

			if (gamePlayers.get(i).isInJail()) {
				getOutJail(gamePlayers.get(i), currentRoll.getDice1(), currentRoll.getDice2());
				continue;
			}

			// need clarification on how the dice system works. does each player have their
			// own set of dice??****

			// getting a temp value for the index the player is moved to
			int temp = currentRoll.getDice1() + currentRoll.getDice2()
					+ LoadTileTransaction.gameTiles.indexOf(gamePlayers.get(i).getCurrentTile());

			if (temp > 39) {
				temp = temp - 40;
				gamePlayers.get(i).setBalance(gamePlayers.get(i).getBalance() + 200);
				System.out.println(gamePlayers.get(i).getName() + " has passed go and received £200!");
			}

			// setting new currentTile for given player
			gamePlayers.get(i).setCurrentTile(LoadTileTransaction.gameTiles.get(temp));

			System.out.println(
					gamePlayers.get(i).getName() + " has landed on " + gamePlayers.get(i).getCurrentTile().name); // output
																													// show
																													// what
																													// tile
																													// player
																													// now
																													// on

			switch (gamePlayers.get(i).getCurrentTile().getTileType()) {

			case "noActionTile":
				break;
			case "chanceTile":

				takeChanceCard(gamePlayers.get(i), gamePlayers.get(i).getCurrentTile());
				break;

			case "taxTile":
				payTax(gamePlayers.get(i), gamePlayers.get(i).getCurrentTile());
				break;
			case "propertyTile":
				if (gamePlayers.get(i).getCurrentTile().isBuyable()) {
					buyProperty(gamePlayers.get(i), gamePlayers.get(i).getCurrentTile());
					break;
				} else {
					payRent(gamePlayers.get(i), gamePlayers.get(i).getCurrentTile());
				}
			case "goToJailTile":
				goToJail(gamePlayers.get(i));
			case "goTile":
				// add Â£200
			}

			// ending turn sequence
			System.out.println(gamePlayers.get(i).getName() + ", press the enter button to end your turn");
			input.nextLine();
			System.out
					.println("\n" + "#########################################################################" + "\n");

		}

	}

	public void buyProperty(Player player, Tile tile) {

		PlayerPayTransaction pay = new PlayerPayTransaction();
		// here you have option to buy

		System.out.println("Are you interested in investing in the property?");
		String in = input.nextLine();

		if (in.equalsIgnoreCase("Yes")) {
			pay.playerBuy(player, tile);
		}

	}

	public void sellProperty() {

	}

	public void tradeProperty() {

	}

	public void buySafariPark(Player player, Tile tile) {
		PlayerPayTransaction pay = new PlayerPayTransaction();
		// only possible if all tiles from given location are owned
		if (player.getOwnedTiles() == tile) {
			System.out.println("Are you interested in investing in a Safari Park?");
			String in = input.nextLine();
			if (in.equalsIgnoreCase("Yes")) {
				pay.playerBuySafariPark(player, tile);
			}

		}

	}

	public void payRent(Player player, Tile tile) {

	}

	public void takeChanceCard(Player player, Tile tile) {
		// array of different chance cards
		// randomly select one
		ChanceTileImpl chance = new ChanceTileImpl();
		chance.drawCard(player, tile);

	}

	public void payTax(Player player, Tile tile) {
		System.out.println(player.getName() + "  you must pay £" + player.getCurrentTile().getRent());
		player.setBalance(player.getBalance() - player.getCurrentTile().getRent());
		System.out.println("Your new balance is £" + player.getBalance());
	}

	public void goToJail(Player player) {
		player.setIsInJail(true);
	}

	public void getOutJail(Player player, int dice1, int dice2) {
		System.out.println(player.getName()
				+ ", you are currently in jail! To get out you can \n  1. pay the £200 jail fee. \n 2. attempt to roll a double. \n Enter your choice: ");
		int choice = input.nextInt();

		switch (choice) {
		case 1:
			if (player.getBalance() >= 200) {
				player.setBalance(player.getBalance() - 200);
				player.setIsInJail(false);
				player.setCurrentTile(LoadTileTransaction.gameTiles.get(10));
				System.out.println(player.getName()
						+ ", you are now free from jail and are on the visiting jail tile. You may continue on your next turn");
			} else {
				System.out.println("You have insufficient funds! You must choose anothe roption on your next turn!");
			}

		case 2:
			System.out.println("press enter to make your roll");
			if (dice1 == dice2) {
				System.out.println("You have rolled " + String.valueOf(dice1) + String.valueOf(dice2));
				player.setIsInJail(false);
				player.setCurrentTile(LoadTileTransaction.gameTiles.get(10));
				System.out.println(player.getName()
						+ ", you are now free from jail and are on the visiting jail tile. You may continue on your next turn");
			} else {
				System.out.println("You have rolled " + String.valueOf(dice1) + String.valueOf(dice2));
				System.out.println("You have not rolled doubles and must stay in jail until your next turn!");
			}

		}
	}
}
