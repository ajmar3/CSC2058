package TileManagement;

public interface TileInterface {

    void printDetails();

	public String getLocation();

	void setLocation(String Location);

	public String getName();

	void setName(String name);

	double getCost();

	void setCost(double cost);

	double getRent();

	void setRent(double rent);

	boolean isBuyable();

	void setBuyable(boolean buyable);
}
