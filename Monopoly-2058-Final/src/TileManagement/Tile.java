package TileManagement;

import PlayerManagement.Player;

public class Tile  implements TileInterface{
	
	public String location;
    public String name;
    public double cost;
    public double rent;
    public boolean buyable;
    public Player owner;
    public Tile(String _location, String _name, double _cost, double _rent){
        location = _location;
        name = _name;
        cost = _cost;
        rent = _rent;
    }
    public void printDetails(){
        System.out.println("Tile Name: " + name + " | " + "Tile Location: " + location);
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public double getRent() {
        return rent;
    }
    public void setRent(double rent) {
        this.rent = rent;
    }
    public boolean isBuyable() {
        return buyable;
    }
    public void setBuyable(boolean buyable) {
        this.buyable = buyable;
    }

}
