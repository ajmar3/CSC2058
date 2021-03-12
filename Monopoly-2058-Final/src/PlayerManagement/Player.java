package PlayerManagement;

import java.util.ArrayList;
import java.util.List;

import TileManagement.Tile;
import TileManagement.LoadTileTransaction;

public class Player {
    private String name;
    private Tile currentTile;
    private double balance;
    private boolean inJail;
    private List<Tile> ownedTiles = new ArrayList<Tile>();
    private List<Tile> SafariTiles = new ArrayList<Tile>();

    public Player(String _name, double _balance) {
        name = _name;
        balance = _balance;
        currentTile = LoadTileTransaction.gameTiles.get(0);
        inJail = false;
    }

    public void printDetails() {
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

    public List<Tile> getSafariTiles() {
        return SafariTiles;
    }

    public void setSafariTiles(List<Tile> ownedTiles) {
        this.SafariTiles = ownedTiles;
    }

    public void addTile(Tile tile) {
        ownedTiles.add(tile);
        tile.setBuyable(false);
    }

    public void addSafariTiles(Tile tile) {
        /*
         * SafariTiles.add(tile); balance = balance - tile.getCost();
         * tile.setBuyable(false);
         */
    }

    public void removeTile(Tile tile) {
        ownedTiles.remove(tile);
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setIsInJail(boolean status) {
        this.inJail = status;
    }
}
