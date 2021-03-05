package TileManagement;

import java.util.Random;
import PlayerManagement.Player;




public class ChanceTileImpl  implements ChanceTileInterface{
	
	
	

	public void drawCard(Player player, Tile tile) {
		
		
Random rand = new Random();	
int i = rand.nextInt(4);
String[] chances = new String[4];
 chances[0] = "Back to Go!";
 chances[1] = "Go to Jail!";
 chances[2] = "Back two spaces.";
 chances[3] = "You won $100!";
 chances[4] = "Pay $50 for NGOs on animal services.";



if(i == 0) {
	System.out.println(chances[0]);
	Tile current = LoadTileTransaction.gameTiles.get(0);
	player.setCurrentTile(current);
	}
else if(i == 1) {
	System.out.println(chances[1]);
	Tile current = LoadTileTransaction.gameTiles.get(30);
	player.setCurrentTile(current);
}
else if(i == 2) {
	System.out.println(chances[2]);
	int temp = LoadTileTransaction.gameTiles.indexOf(player.getCurrentTile()) -2;
	player.setCurrentTile(LoadTileTransaction.gameTiles.get(temp));

}
else if(i == 3) {
	System.out.println(chances[3]);
	double newBalance = player.getBalance() + 100.00;
	player.setBalance(newBalance);
	
}
else if(i == 4) {
	System.out.println(chances[4]);
	double newBalance = player.getBalance() - 50.00;
	player.setBalance(newBalance);
}
	
	
	}
	}





