package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import DataManagement.SQL;
import Main.transaction.EndGameTransaction;
import Main.transaction.GameTransaction;
import Main.transaction.MenuTransaction;
import PlayerManagement.transactions.LoadPlayerTransaction;
import Dice.Dice;
import PlayerManagement.Player;
import PlayerManagement.transactions.PlayerRollTransaction;
import PlayerManagement.transactions.RegisterPlayerTransaction;
import TileManagement.LoadTileTransaction;
import TileManagement.Tile;

public class Main {

    static MenuTransaction m = new MenuTransaction();
    static PlayerRollTransaction pr = new PlayerRollTransaction();
    static LoadPlayerTransaction lp = new LoadPlayerTransaction();
    static GameTransaction game = new GameTransaction();
    static LoadTileTransaction tiles = new LoadTileTransaction();
    static EndGameTransaction egt = new EndGameTransaction();
    //private static SQL sql = new SQL();

    private static Scanner nS = new Scanner(System.in);
    private static List<Player> Players;
    private static List<Tile> Tiles;

    private static Dice currentRoll;

    public static void main(String[] args) {

        tiles.loadTiles(); // loading all tiles from board as they are needed to register players in
                           // m.loadmenu()
       // sql.Storage();
       // sql.Retrieve();
        
        m.loadMenu();
        
        Players = RegisterPlayerTransaction.gamePlayers;

        for (int i = 0; i <= RegisterPlayerTransaction.numTurns; i++) {
        	if(i == RegisterPlayerTransaction.numTurns) {
        		egt.endGame(Players);
        	}
        	else {
        		game.turn();
        	}
            
        }
    }

}
