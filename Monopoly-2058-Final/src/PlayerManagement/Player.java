package PlayerManagement;

import java.util.ArrayList;
import java.util.List;

import TileManagement.Tile;

public class Player {
	private String name;
    private Tile currentTile;
    private double balance;
    private List<Tile> ownedTiles = new ArrayList<Tile>();

    public Player(String _name, double _balance) {
        name = _name;
        balance = _balance;
    }

    public void printDetails(){
        System.out.println("Name: " + name + " | " + "Balance: £" + balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Tile> getOwnedTiles() {
        return ownedTiles;
    }

    public void setOwnedTiles(List<Tile> ownedTiles) {
        this.ownedTiles = ownedTiles;
    }

}
