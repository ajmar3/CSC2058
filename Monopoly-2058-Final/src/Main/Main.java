package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    private static Scanner nS = new Scanner(System.in);
    private static List<Player> Players;
    private static List<Tile> Tiles;

    private static Dice currentRoll;

    public static void main(String[] args) {

        tiles.loadTiles(); // loading all tiles from board as they are needed to register players in
                           // m.loadmenu()

        m.loadMenu();

        for (int i = 0; i < RegisterPlayerTransaction.numTurns; i++) {
            game.turn();
        }

        System.out.println("Game Over");
    }

    //
    // the turn function has been commented out and is starting to be moved to the
    // Gametransactions class
    //

    // public static void turn(){

    // PlayerRollTransaction pR = new PlayerRollTransaction();

    // for(int i = 0; i < Players.size(); i++){

    // currentRoll = pR.roll(Players.get(i)).get(i);
    // Players.get(i).setCurrentTile(Tiles.get(0));

    // int temp = currentRoll.getDice1() + currentRoll.getDice2() +
    // getIndex(Players.get(i));

    // Players.get(i).setCurrentTile(Tiles.get(temp));
    // Players.get(i).getCurrentTile().printDetails();

    // if(Players.get(i).getCurrentTile().isBuyable()) {

    // System.out.println(Players.get(i).getName() + " do you want to invest in " +
    // Players.get(i).getCurrentTile().getName() + " for " +
    // Players.get(i).getCurrentTile().getCost() + " ?");
    // String ans = nS.nextLine();

    // if(ans.equalsIgnoreCase("Yes")){

    // if(Players.get(i).getBalance() >= Players.get(i).getCurrentTile().getCost()){
    // Players.get(i).addTile(Players.get(i).getCurrentTile());

    // System.out.println(Players.get(i).getName() + " has invested in the " +
    // Players.get(i).getCurrentTile().getName());
    // System.out.print("They own: ");

    // for(int y = 0; y < Players.get(i).getOwnedTiles().size(); y++){

    // if(Players.get(i).getOwnedTiles().get(y) != null) {

    // System.out.print(Players.get(i).getOwnedTiles().get(y).getName() + " | ");

    // }
    // }
    // System.out.println(Players.get(i).getName() + "'s current balance is " +
    // Players.get(i).getBalance());
    // System.out.println(Players.get(i).getName() + ", enter any button to end your
    // turn");
    // String in = nS.nextLine();
    // System.out.println("\n"+"#########################################################################"+"\n");
    // //adding row of #s after each turn [A.M]
    // }

    // else{

    // System.out.println("You do not have the funds to invest in this property.");
    // System.out.println(Players.get(i).getName() + ", enter any button to end your
    // turn");
    // String in = nS.nextLine();
    // System.out.println("\n"+"#########################################################################"+"\n");
    // //adding row of #s after each turn [A.M]

    // }
    // }
    // else {
    // System.out.println(Players.get(i).getName() + " did not choose to invest in
    // this property.");
    // System.out.println(Players.get(i).getName() + ", enter any button to end your
    // turn");
    // String in = nS.nextLine();
    // System.out.println("\n"+"#########################################################################"+"\n");
    // //adding row of #s after each turn [A.M]
    // }
    // }
    // //check if tile has rent
    // else
    // {
    // if(Players.get(i).getOwnedTiles().contains(Players.get(i).getCurrentTile()))
    // {
    // System.out.println("You own this property");
    // System.out.println(Players.get(i).getName() + ", enter any button to end your
    // turn");
    // String in = nS.nextLine();
    // System.out.println("\n"+"#########################################################################"+"\n");
    // //adding row of #s after each turn [A.M]
    // }
    // else
    // {
    // if(Players.get(i).getCurrentTile().getRent() == 0)
    // {
    // System.out.println("this tile has no rent");
    // System.out.println(Players.get(i).getName() + ", enter any button to end your
    // turn");
    // String in = nS.nextLine();
    // System.out.println("\n"+"#########################################################################"+"\n");
    // //adding row of #s after each turn [A.M]
    // }

    // else
    // {
    // if(Players.get(i).getBalance() >= Players.get(i).getCurrentTile().getRent())
    // {
    // double tempBal = Players.get(i).getBalance();
    // double tempRent = Players.get(i).getCurrentTile().getRent();
    // double updateBal = tempBal - tempRent;

    // Players.get(i).setBalance(updateBal);
    // System.out.println(Players.get(i).getName() + ", enter any button to end your
    // turn");
    // String in = nS.nextLine();
    // System.out.println("\n"+"#########################################################################"+"\n");
    // //adding row of #s after each turn [A.M]

    // }

    // }
    // }
    // }
    // }
    // }

    // public static int getIndex(Player p){
    // for(int x = 0; x < Tiles.size(); x++){
    // if(Tiles.get(x).getName().equalsIgnoreCase(p.getCurrentTile().getName())){
    // return x;
    // }
    // }
    // return 0;
    // }

}
