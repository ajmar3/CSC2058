package PlayerManagement.transactions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import PlayerManagement.Player;
import TileManagement.LoadTileTransaction;
import TileManagement.Tile;

class PlayerPayTransactionTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testPlayerBuy() {
		LoadTileTransaction tiles = new LoadTileTransaction();
		tiles.loadTiles();
		Tile t = new Tile("United Kingdom", "Red Squirrel", 60, 2, 200, 90, "propertyTile", true);
		Player p = new Player("Dave", 1500);	
		PlayerPayTransaction buy = new PlayerPayTransaction();
		buy.playerBuy(p, t);
		double expectedBal = 1440.0;
		double actual = p.getBalance();
		assertEquals(expectedBal, actual);
		
	}

	@Test
	void testPlayerBuySafariPark() {
		LoadTileTransaction tiles = new LoadTileTransaction();
		tiles.loadTiles();
		Tile t = new Tile("DRC", "Congolese Rainforest Conservation Park", 200, 25, 0, 0, "propertyTile", true);
		Player p = new Player("Dave", 1500);
		PlayerPayTransaction buy = new PlayerPayTransaction();
		buy.playerBuySafariPark(p, t);
		double excpectedBalance = 1500 - t.getCostOfSafari();
		double actual = p.getBalance();
		assertEquals(excpectedBalance,actual);
		
	}
	

	@Test
	void testPlayerSellTile() {
		LoadTileTransaction tiles = new LoadTileTransaction();
		tiles.loadTiles();
		Tile t = new Tile("United Kingdom", "Red Squirrel", 60, 2, 200, 90, "propertyTile", true);
		Player p = new Player("Dave", 1500);
		PlayerPayTransaction sell = new PlayerPayTransaction();
		sell.playerSellTile(p, t);
		double expectedBal = 1560.0;
		double actual = p.getBalance();		
		assertEquals(expectedBal,actual);		
	}

	@Test
	void testPlayerTradeTile() {
		LoadTileTransaction tiles = new LoadTileTransaction();
		tiles.loadTiles();
		Tile t = new Tile("United Kingdom", "Red Squirrel", 60, 2, 200, 90, "propertyTile", true);
		Tile t1 = new Tile("DRC", "Congolese Rainforest Conservation Park", 200, 25, 0, 0, "propertyTile", true);
		Player p = new Player("Dave", 1500);
		p.addTile(t);
		
		Player p1 = new Player("Rory", 1500);
		p1.addTile(t1);
		
		List<Tile> temp = new ArrayList<Tile>();
		temp = p.getOwnedTiles();	
		List<Tile> temp1 = new ArrayList<Tile>();
		temp1 = p1.getOwnedTiles();	
		
		PlayerPayTransaction trade = new PlayerPayTransaction();
		trade.playerTradeTile(p, p1, t, t1);
		
		List<Tile> tradetemp = new ArrayList<Tile>();
		tradetemp = p.getOwnedTiles();	
		List<Tile> tradetemp1 = new ArrayList<Tile>();
		tradetemp1 = p1.getOwnedTiles();	
		
		assertEquals(tradetemp1.get(0).getName(),temp1.get(0).getName());
		assertEquals(tradetemp.get(0).getName(),temp.get(0).getName());	
		
	}

}
