package TileManagement;

import java.util.Random;
import PlayerManagement.Player;

public class ChanceTileImpl implements ChanceTileInterface {

	public void drawCard(Player player, Tile tile) {

		Random rand = new Random();
		int i = rand.nextInt(9);
		double newBalance;
		Tile current;
		int temp;

		String[] chances = { "Some trees have fallen onto the road, go back to Go!",
				"You started a fire in the area, go to Jail!",
				"Lightning has suddenly struck, you get pushed back 2 steps!",
				"You make the last safari ride of the day, move forward 1 step!",
				"You invest in the Amazon Rainforest Conservation Park and your invest pays off, pick up £200!",
				"You help cleaning your local park, pick up £100 as a reward!",
				"You stay to help the zookeeper feed the pandas bamboo, he pays you £100!",
				"You are caught pulling faces at the orangutan by the zookeeper, pay £50!",
				"A massive storm floods your tent, pay £100 to replace it!",
				"You are found leaving rubbish at the Congolese Rainforest Conservation Park, pay £200 as a fine!" };
		

		
		switch(i) {
		case 0 :
			System.out.println(chances[0]);
			current = LoadTileTransaction.gameTiles.get(0);
			player.setCurrentTile(current);
			System.out.println("You have landed on " + player.getCurrentTile().name);
			break;
		case 1:
			System.out.println(chances[1]);
			current = LoadTileTransaction.gameTiles.get(30);
			player.setCurrentTile(current);
			System.out.println("You have landed on " + player.getCurrentTile().name);
			break;
		case 2 :
			System.out.println(chances[2]);
			temp = LoadTileTransaction.gameTiles.indexOf(player.getCurrentTile()) - 2;
			player.setCurrentTile(LoadTileTransaction.gameTiles.get(temp));
			System.out.println("You have landed on " + player.getCurrentTile().name);
			break;
		case 3 :
			System.out.println(chances[3]);
			temp = LoadTileTransaction.gameTiles.indexOf(player.getCurrentTile()) + 1;
			player.setCurrentTile(LoadTileTransaction.gameTiles.get(temp));
			System.out.println("You have landed on " + player.getCurrentTile().name);
			break;
		case 4 :
			System.out.println(chances[4]);
			newBalance = player.getBalance() + 200.00;
			player.setBalance(newBalance);
			System.out.println("Your new balance : " + player.getBalance());
			break;
		case 5 : 
			System.out.println(chances[5]);
			newBalance = player.getBalance() + 100.00;
			player.setBalance(newBalance);
			System.out.println("Your new balance : " + player.getBalance());
			break;
		case 6 :
			System.out.println(chances[6]);
			newBalance = player.getBalance() + 100.00;
			player.setBalance(newBalance);
			System.out.println("Your new balance : " + player.getBalance());
		break;
		case 7 :
			System.out.println(chances[7]);
			newBalance = player.getBalance() - 50.00;
			player.setBalance(newBalance);
			System.out.println("Your new balance : " + player.getBalance());
			break;
		case 8 :
			System.out.println(chances[8]);
			newBalance = player.getBalance() - 100.00;
			player.setBalance(newBalance);
			System.out.println("Your new balance : " + player.getBalance());
			break;
		case 9 :
			System.out.println(chances[9]);
			newBalance = player.getBalance() - 200.00;
			player.setBalance(newBalance);
			System.out.println("Your new balance : " + player.getBalance());
			break;
		}
		

	}
}
