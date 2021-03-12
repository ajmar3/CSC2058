package PlayerManagement.transactions;


import PlayerManagement.Player;
import TileManagement.Tile;

public class PlayerPayTransaction {

	public void playerBuy(Player player,Tile tile ){
	
			double newBalance = player.getBalance() - tile.getCost();
			player.setBalance(newBalance);
			player.addTile(tile);
			
			System.out.println("Player Balance = " + player.getBalance());
			System.out.println("You now own = " + tile.getName());
		

		
		
	}
	
	public void playerBuySafariPark(Player player, Tile tile) {
		if (!tile.buyable) return;
		if(tile.buyable) {
			double newBalance = player.getBalance() - tile.getCostOfSafari();
			player.setBalance(newBalance);
			player.addSafariTiles(tile);
			
			System.out.println("Player Balance = " + player.getBalance());
			System.out.println("You now own = " + tile.getName());
		}
	}
	
	
public void playerSellTile(Player player, Tile tile) {
		
		double newBalance = player.getBalance() + tile.getCost();
		player.setBalance(newBalance);
		player.removeTile(tile);
		
		System.out.println("Player Balance = " + player.getBalance());
		System.out.println("you have successfully sold " + " " + tile.getName());
		
	}

public void playerTradeTile(Player p1, Player p2, Tile tile1, Tile tile2) {
	p1.removeTile(tile1);
	p1.addTile(tile2);
	p2.removeTile(tile2);
	p2.addTile(tile1);
	
	System.out.println("Trade has been completed successfully");
	
	
	
}
}


