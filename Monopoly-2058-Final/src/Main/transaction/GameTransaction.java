package Main.transaction;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.InputMismatchException;
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
	private static EndGameTransaction egt = new EndGameTransaction();

	private static Dice currentRoll;

	private static Scanner input = new Scanner(System.in);

	public enum tradeAns {
		sell, trade, no
	}

	public enum yesNo {
		yes, no
	}

	boolean holder = false;

	public void turn() {

		PlayerRollTransaction playerRoll = new PlayerRollTransaction();

		for (int i = 0; i < gamePlayers.size(); i++) {

			currentRoll = playerRoll.roll(gamePlayers.get(i)).get(i);
			System.out.println(gamePlayers.get(i).getName() + " your balance is " + gamePlayers.get(i).getBalance());

			if (gamePlayers.get(i).isInJail()) {
				getOutJail(gamePlayers.get(i), currentRoll.getDice1(), currentRoll.getDice2());
				continue;
			}

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

			System.out.println(gamePlayers.get(i).getName() + " You have landed on:");
			gamePlayers.get(i).getCurrentTile().printDetails();

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
					holder = false;
					break;
				} else {
					payRent(gamePlayers.get(i), gamePlayers.get(i).getCurrentTile());
				}
			case "goToJailTile":
				goToJail(gamePlayers.get(i));
				break;
			case "goTile":
				// add Â£200
				break;
			}

			// getting list of player owned tiles
			List<Tile> tempList = gamePlayers.get(i).getOwnedTiles();
			boolean hold = false;

			// option to sell or tarde current properties.
			if (!tempList.isEmpty()) {

				while (hold == false) {
					System.out.println("would you like to sell or trade any of your properties  (sell/trade/No)");
					String res = input.nextLine();

					// tradeAns.valueOf(res);
					EnumSet<tradeAns> except = EnumSet.of(tradeAns.sell, tradeAns.trade, tradeAns.no);

					boolean valid;

					try {
						valid = !except.contains(tradeAns.valueOf(res));

						if (res.equalsIgnoreCase("sell")) {
							sellProperty(gamePlayers.get(i));
						}
						if (res.equalsIgnoreCase("trade")) {
							tradeProperty(gamePlayers.get(i));
						}
						if (res.equalsIgnoreCase("no")) {
							hold = true;
						}
						hold = true;
					} catch (IllegalArgumentException e) {
						valid = false;
					}

				}

			}

			// option to buy safari park
			if (checkSafariBuyable(gamePlayers.get(i))) {
				buySafariPark(gamePlayers.get(i));
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

		while (holder == false) {
			System.out.println("Are you interested in investing in the property?  (yes/no)");
			System.out.println("Cost: £" + tile.cost);
			String in = input.nextLine();

			boolean valid;

			EnumSet<yesNo> except = EnumSet.of(yesNo.yes, yesNo.no);

			try {
				valid = !except.contains(yesNo.valueOf(in));

				if (in.equalsIgnoreCase("Yes")) {
					pay.playerBuy(player, tile);
				}
				if (in.equalsIgnoreCase("no")) {
					return;
				}
				holder = true;

			} catch (IllegalArgumentException e) {
				valid = false;
				System.out.println("Please select one of the choices");
			}

		}

	}

	public void sellProperty(Player player) {
		PlayerPayTransaction sell = new PlayerPayTransaction();
		List<Tile> temp = new ArrayList<Tile>();
		temp = player.getOwnedTiles();
		boolean hol = false;
		while (hol == false) {
			try {
				System.out
						.println("Select the property you would like to sell from the list using the assigned number");

				for (int i = 0; i < temp.size(); i++) {
					System.out.println("\n" + i + " " + temp.get(i).getName() + " Amount: " + temp.get(i).getCost());
				}

				String response = input.nextLine();
				boolean numeric = true;

				try {
					Double num = Double.parseDouble(response);
				} catch (NumberFormatException e) {
					numeric = false;
				}

				if (numeric) {
					int ans = Integer.parseInt(response);
					String res = temp.get(ans).getName();
					Tile t = temp.get(ans);

					System.out.println("you have selected " + " " + res);
					sell.playerSellTile(player, t);
					hol = true;
				} else {
					System.out.println(response + " is not a number");
				}

			} catch (Exception e) {
				System.out.println("Not an integer");
				hol = false;

			}

		}

	}

	public void tradeProperty(Player player) {
		boolean numeric = true;
		boolean hol1 = false;
		while (hol1 == false) {

			try {

				System.out.println(
						"Choose the player who you would like to trade with from the list using the assigned number");

				for (int i = 0; i < gamePlayers.size(); i++) {

					if (gamePlayers.get(i) != player)
						System.out.println("\n" + i + " " + gamePlayers.get(i).getName());
				}

				String chosen = input.nextLine();

				try {
					Double num = Double.parseDouble(chosen);
				} catch (NumberFormatException e) {
					numeric = false;
				}

				if (numeric) {
					int ans = Integer.parseInt(chosen);

					PlayerPayTransaction Trade = new PlayerPayTransaction();
					List<Tile> temp = new ArrayList<Tile>();
					System.out.println(
							"Select which one of your properties you would like to trade from the list using the assigned number");
					temp = player.getOwnedTiles();
					for (int i = 0; i < temp.size(); i++) {
						System.out.println("\n" + i + " " + temp.get(i).getName());
					}
					String response = input.nextLine();
					boolean numeric1 = true;
					try {
						Double num1 = Double.parseDouble(response);
					} catch (NumberFormatException e) {
						numeric1 = false;
					}
					if (numeric1) {
						int ans1 = Integer.parseInt(response);
						String res = temp.get(ans1).getName();
						Tile t1 = temp.get(ans1);

						List<Tile> ptemp = new ArrayList<Tile>();
						System.out.println(
								"Select which property you would like to acquire from the other player from the list using the assigned number");
						Player playTemp = gamePlayers.get(ans);
						ptemp = playTemp.getOwnedTiles();
						for (int i = 0; i < ptemp.size(); i++) {
							System.out.println("\n" + i + " " + ptemp.get(i).getName());
						}
						String re = input.nextLine();
						boolean numeric2 = true;
						try {
							Double num1 = Double.parseDouble(chosen);
						} catch (NumberFormatException e) {
							numeric2 = false;
						}
						if (numeric2) {
							int ans2 = Integer.parseInt(re);
							Tile t2 = ptemp.get(ans2);

							boolean valid;
							EnumSet<yesNo> except = EnumSet.of(yesNo.yes, yesNo.no);

							System.out.println(gamePlayers.get(ans).getName() + " "
									+ "do you wish to accept this trade? (Yes/no)");
							String resp = input.nextLine();
							try {
								valid = !except.contains(yesNo.valueOf(resp));

								if (resp.equalsIgnoreCase("Yes")) {
									Trade.playerTradeTile(player, playTemp, t1, t2);
									hol1 = true;
								}
								if (resp.equalsIgnoreCase("no")) {
									System.out.println("Trade has been declined");
									return;
								}

							} catch (IllegalArgumentException e) {
								valid = false;
								System.out.println("Please select one of the choices");
							}

						}

					}
				}
			} catch (Exception e) {
				System.out.println("not valid");
			}

		}

	}

	public void buySafariPark(Player player) {
		boolean holder = false;
		while (holder == false) {
			System.out.println(player.getName()
					+ ", are you interested in building a safari park on one of your conservation parks?  (yes/no)");
			String playerChoice = input.nextLine();
			playerChoice = playerChoice.toLowerCase();
			boolean valid;
			EnumSet<yesNo> except = EnumSet.of(yesNo.yes, yesNo.no);

			try {
				valid = !except.contains(yesNo.valueOf(playerChoice));

				if (playerChoice.equalsIgnoreCase("Yes")) {
					System.out.println("Here are all the tiles you can currently build a safari park on: \n");
					List<Tile> potentialLoactions = this.getSafariParkPotentialLocations(player);
					for (int k = 0; k < potentialLoactions.size(); k++) {
						System.out.println(k + " " + potentialLoactions.get(k).getName() + " Price: £"
								+ potentialLoactions.get(k).getCostOfSafari());
					}
					System.out.println("\n Please enter the index of the tile you want to build a safari park on:");
					String index = input.nextLine();

					boolean numeric = true;
					try {
						Double num1 = Double.parseDouble(index);
					} catch (NumberFormatException e) {
						numeric = false;
					}
					if (numeric) {
						int num = Integer.parseInt(index);
						potentialLoactions.get(num).setRent(potentialLoactions.get(num).getRentWithSafari());
						potentialLoactions.get(num).setSafariBuildable(false);
						player.setBalance(player.getBalance() - potentialLoactions.get(num).getCostOfSafari());
						System.out.println("Safari park purchased for tile: " + potentialLoactions.get(num).name);
						System.out.println(player.getName() + " now has a balance of £" + player.getBalance());
						holder = true;
					}

				}
				if (playerChoice.equalsIgnoreCase("no")) {
					return;
				}

			}

			catch (Exception e) {
				valid = false;
				System.out.println("Please select one of the choices");
				holder = false;
			}

		}

	}

	public void payRent(Player player, Tile tile) {

		List<Tile> temp = new ArrayList<Tile>();
		temp = player.getOwnedTiles();

		if (player.getBalance() >= player.getCurrentTile().getRent()) {
			System.out.println("Player " + tile.getOwner().getName() + " owns this tile!");
			System.out.println(player.getName() + "  you must pay £" + player.getCurrentTile().getRent());
			player.setBalance(player.getBalance() - player.getCurrentTile().getRent());
			System.out.println("Your new balance is £" + player.getBalance());
			Player x = tile.getOwner();
			x.setBalance(x.getBalance() + tile.getRent());
		} else if (!temp.isEmpty()) {
			System.out.println(
					"You do not have sufficient funds to pay the rent you are being forced to liquidate your assets");
			liquidate(player);
		} else {
			System.out.println("You have gone bust and have lost");
			removePlayer(player);
		}
	}

	public void removePlayer(Player player) {
		gamePlayers.remove(player);
		System.out.println("You have been removed from the game");
		if (gamePlayers.size() <= 1) {
			egt.endGame(gamePlayers);
		}
	}

	public void liquidate(Player player) {
		Tile t;
		PlayerPayTransaction sell = new PlayerPayTransaction();
		List<Tile> temp = new ArrayList<Tile>();
		temp = player.getOwnedTiles();
		for (int i = 0; i < temp.size(); i++) {
			t = temp.get(i);
			sell.playerSellTile(player, t);
		}
	}

	public void takeChanceCard(Player player, Tile tile) {
		// array of different chance cards
		// randomly select one
		ChanceTileImpl chance = new ChanceTileImpl();
		chance.drawCard(player, tile);

	}

	public void payTax(Player player, Tile tile) {
		List<Tile> temp = new ArrayList<Tile>();
		temp = player.getOwnedTiles();
		boolean hol = false;

		if (player.getBalance() >= player.getCurrentTile().getRent()) {
			System.out.println(player.getName() + "  you must pay £" + player.getCurrentTile().getRent());
			player.setBalance(player.getBalance() - player.getCurrentTile().getRent());
			System.out.println("Your new balance is £" + player.getBalance());
		} else {
			System.out.println("You do not have sufficient funds to pay the tax and have been sent to jail");
			goToJail(player);
		}
	}

	public void goToJail(Player player) {
		player.setIsInJail(true);
		player.setCurrentTile(LoadTileTransaction.gameTiles.get(10));
	}

	public void getOutJail(Player player, int dice1, int dice2) {
		boolean holder = false;
		while (holder == false) {
			try {
				System.out.println(player.getName()
						+ ", you are currently in jail! To get out you can \n  1. pay the £200 jail fee. \n 2. attempt to roll a double. \n Enter your choice: ");
				String choice = input.nextLine();
				boolean numeric = true;
				try {
					Double num1 = Double.parseDouble(choice);
				} catch (NumberFormatException e) {
					numeric = false;
				}
				if (numeric) {
					int ans = Integer.parseInt(choice);
					switch (ans) {
					case 1:
						if (player.getBalance() >= 200) {
							player.setBalance(player.getBalance() - 200);
							player.setIsInJail(false);
							player.setCurrentTile(LoadTileTransaction.gameTiles.get(10));
							System.out.println(player.getName()
									+ ", you are now free from jail and are on the visiting jail tile. You may continue on your next turn");
							holder = true;
						} else {
							System.out.println(
									"You have insufficient funds! You must choose another option on your next turn!");
						}
						break;

					case 2:
						System.out.println("press enter to make your roll");
						if (dice1 == dice2) {
							System.out.println("You have rolled " + String.valueOf(dice1) + String.valueOf(dice2));
							player.setIsInJail(false);
							player.setCurrentTile(LoadTileTransaction.gameTiles.get(10));
							System.out.println(player.getName()
									+ ", you are now free from jail and are on the visiting jail tile. You may continue on your next turn");
							holder = true;
						} else {
							System.out.println("You have rolled " + String.valueOf(dice1) + String.valueOf(dice2));
							System.out
									.println("You have not rolled doubles and must stay in jail until your next turn!");
							holder = true;
						}
						break;
					}
				}

			} catch (Exception e) {
				System.out.println("Invalid ");
				holder = false;
			}

		}
	}

	public boolean checkSafariBuyable(Player player) {

		for (int k = 0; k < player.getOwnedTiles().size(); k++) {
			String thisTilelocation = player.getOwnedTiles().get(k).location;
			int numOwnedFromThisLocation = 0;
			int totalFromThisLocation = 0;
			for (int m = 0; m < LoadTileTransaction.gameTiles.size(); m++) {
				if (LoadTileTransaction.gameTiles.get(m).location == thisTilelocation) {
					totalFromThisLocation++;
				}
			}
			for (int y = 0; y < player.getOwnedTiles().size(); y++) {
				if (player.getOwnedTiles().get(y).location == thisTilelocation) {
					numOwnedFromThisLocation++;
				}
			}

			if ((totalFromThisLocation == numOwnedFromThisLocation)
					&& (player.getOwnedTiles().get(k).getSafariBuildable() == true)) {
				return true;
			}
		}
		return false;
	}

	public List<Tile> getSafariParkPotentialLocations(Player player) {

		List<Tile> potentialLocations = new ArrayList<Tile>();

		for (int k = 0; k < player.getOwnedTiles().size(); k++) {
			String thisTilelocation = player.getOwnedTiles().get(k).location;
			int numOwnedFromThisLocation = 0;
			int totalFromThisLocation = 0;
			for (int m = 0; m < LoadTileTransaction.gameTiles.size(); m++) {
				if (LoadTileTransaction.gameTiles.get(m).location == thisTilelocation) {
					totalFromThisLocation++;
				}
			}
			for (int y = 0; y < player.getOwnedTiles().size(); y++) {
				if (player.getOwnedTiles().get(y).location == thisTilelocation) {
					numOwnedFromThisLocation++;
				}
			}

			if ((totalFromThisLocation == numOwnedFromThisLocation)
					&& (player.getOwnedTiles().get(k).getSafariBuildable() == true)) {
				potentialLocations.add(player.getOwnedTiles().get(k));
			}
		}

		return potentialLocations;

	}
}
