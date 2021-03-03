package TileManagement;

import PlayerManagement.Player;

public interface TileInterface {

    //public Tile(String _location, String _name, double _cost, double _rent);

    public void printDetails();
    public String getLocation();
    public void setLocation(String location);
    public String getName();
    public void setName(String name);
    public double getCost();
    public void setCost(double cost);
    public double getRent();
    public void setRent(double rent);
    public boolean isBuyable();
    public void setBuyable(boolean buyable);

}
