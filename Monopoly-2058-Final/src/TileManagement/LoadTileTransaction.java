package TileManagement;

import java.util.ArrayList;
import java.util.List;


public class LoadTileTransaction {	
	
    public static List<Tile> gameTiles = new ArrayList<Tile>();


    public List<Tile> loadTiles(){


        //all tiles on board
        Tile t0 = new Tile("N/A", "Go", 0, 0, 0, 0, "goTile");
        Tile t1 = new Tile("United Kingdom", "Red Squirrel", 60, 2, 200, 90, "propertyTile");
        Tile t2 = new Tile("N/A","Chance", 0, 0, 0, 0, "chanceTile");
        Tile t3 = new Tile("United Kingdom", "Water Vole", 60, 4, 200, 180, "propertyTile");
        Tile t4 = new Tile("N/A", "Park Maintenance", 0, 200, 0, 0, "taxTile");
        Tile t5 = new Tile("Brazil", "Amazon Rainforest Conservation Park", 200, 25, 0, 0, "propertyTile");
        Tile t6 = new Tile("North Europe", "Atlantic Puffin", 100, 6, 200, 270, "propertyTile");
        Tile t7 = new Tile("N/A","Chance", 0, 0, 0, 0, "chanceTile");
        Tile t8 = new Tile("North Europe", "Capercaillies", 100, 6, 200, 270, "propertyTile");
        Tile t9 = new Tile("North Europe", "Scottish Wildcat", 120, 8, 200, 300, "propertyTile");
        Tile t10 = new Tile("N/A", "Visiting Jail", 0, 0, 0, 0, "noActionTile");
        Tile t11 = new Tile("North America", "Giant Sea Bass", 140, 10, 400, 450, "propertyTile");
        Tile t12 = new Tile("N/A","Park Maintenance", 0, 200, 0, 0, "taxTile");
        Tile t13 = new Tile("North America", "Kemp Ridley Sea Turtle", 140, 10, 400, 450, "propertyTile");
        Tile t14 = new Tile("North America", "Red Wolf", 160, 12, 400, 500, "propertyTile");
        Tile t15 = new Tile("DRC", "Congolese Rainforest Conservation Park", 200, 25, 0, 0, "propertyTile");
        Tile t16 = new Tile("South America", "Galapagos Giant Tortoise", 180, 14, 400, 550, "propertyTile");
        Tile t17 = new Tile("N/A","Chance", 0, 0, 0, 0, "chanceTile");
        Tile t18 = new Tile("South America", "Waved Albatross", 180, 14, 400, 550, "propertyTile");
        Tile t19 = new Tile("South America", "Orinoco Crocadile", 180, 16, 400, 600, "propertyTile");
        Tile t20 = new Tile("N/A", "Free Parking", 0, 0, 0, 0, "noActionTile");
        Tile t21 = new Tile("North Asia", "Snow Leopard", 220, 18, 600, 700, "propertyTile");
        Tile t22 = new Tile("N/A","Chance", 0, 0, 0, 0, "chanceTile");
        Tile t23 = new Tile("North Asia", "Siberian Tiger", 220, 18, 600, 700, "propertyTile");
        Tile t24 = new Tile("North Asia", "Amur Leopard", 240, 20, 600, 750, "propertyTile");
        Tile t25 = new Tile("New Guinea", "New Guinea Rainforest Conservation Park", 200, 25, 0, 0, "propertyTile");
        Tile t26 = new Tile("South Asia", "Green Sea Turtle", 260, 22, 600, 800, "propertyTile");
        Tile t27 = new Tile("South Asia", "Orangutan", 260, 22, 600, 800, "propertyTile");
        Tile t28 = new Tile("N/A","Park Rangers Pay Raise", 0, 200, 0, 0, "taxTile");
        Tile t29 = new Tile("South Asia", "Giant Panda", 280, 24, 600, 850, "propertyTile");
        Tile t30 = new Tile("N/A", "Go To Jail", 0, 0, 0, 0, "goToJailTile");
        Tile t31 = new Tile("North Africa", "Mountain Gorilla", 300, 26, 800, 900, "propertyTile");
        Tile t32 = new Tile("North Africa", "Chimpanzee", 300, 26, 800, 900, "propertyTile");
        Tile t33 = new Tile("N/A","Chance", 0, 0, 0, 0, "chanceTile");
        Tile t34 = new Tile("North Africa", "Hippopotamus", 320, 28, 800, 1000, "propertyTile");
        Tile t35 = new Tile("Chile", "Valdivian Temperate Rainforest Conservation Park", 200, 25, 0, 0, "propertyTile");
        Tile t36 = new Tile("N/A","Chance", 0, 0, 0, 0, "chanceTile");
        Tile t37 = new Tile("South Africa", "Black Rhino", 350, 35, 800, 1100, "propertyTile");
        Tile t38 = new Tile("N/A","Chance", 0, 0, 0, 0, "chanceTile");
        Tile t39 = new Tile("South Africa", "White Lion", 400, 50, 800, 1400, "propertyTile");


        //adding tiles to list
        gameTiles.add(t0);
        gameTiles.add(t1);
        gameTiles.add(t2);
        gameTiles.add(t3);
        gameTiles.add(t4);
        gameTiles.add(t5);
        gameTiles.add(t6);
        gameTiles.add(t7);
        gameTiles.add(t8);
        gameTiles.add(t9);
        gameTiles.add(t10);
        gameTiles.add(t11);
        gameTiles.add(t12);
        gameTiles.add(t13);
        gameTiles.add(t14);
        gameTiles.add(t15);
        gameTiles.add(t16);
        gameTiles.add(t17);
        gameTiles.add(t18);
        gameTiles.add(t19);
        gameTiles.add(t20);
        gameTiles.add(t21);
        gameTiles.add(t22);
        gameTiles.add(t23);
        gameTiles.add(t24);
        gameTiles.add(t25);
        gameTiles.add(t26);
        gameTiles.add(t27);
        gameTiles.add(t28);
        gameTiles.add(t29);
        gameTiles.add(t30);
        gameTiles.add(t31);
        gameTiles.add(t32);
        gameTiles.add(t33);
        gameTiles.add(t34);
        gameTiles.add(t35);
        gameTiles.add(t36);
        gameTiles.add(t37);
        gameTiles.add(t38);
        gameTiles.add(t39);


        return gameTiles;
    }

}
