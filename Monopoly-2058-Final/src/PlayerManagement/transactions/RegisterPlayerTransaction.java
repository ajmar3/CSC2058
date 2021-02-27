package PlayerManagement.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import PlayerManagement.Player;

public class RegisterPlayerTransaction {
	
	public int playerAmount = 0;
	public static List<Player> gamePlayers = new ArrayList<Player>();
	public Scanner nS = new Scanner(System.in);
	
	public List<Player> RegisterPlayers() {
	
		System.out.print("Enter the amount of players entering your game: ");
        int amount = nS.nextInt();
        nS.nextLine();

        for(int i = 0; i < amount; i++){
            System.out.println("Enter the name of player " + (i+1));
            gamePlayers.add(new Player(nS.nextLine(), 1500));
        }

        if(!securityCheck()){
            System.out.println("Some of the players entered are either null or not acceptable.");
        }
        else{
            printDetails();
            System.out.println("enter any button to start the game");
            String in = nS.nextLine();
            System.out.println("\n"+"#########################################################################"+"\n");   //adding row of #s after each turn  [A.M]
            return gamePlayers;
        }
        return null;
	}
	
	public boolean securityCheck(){
        for(int i = 0; i < gamePlayers.size(); i++){
            if(gamePlayers.get(i) != null) {
                return true;
            }else{
                return false;
            }
        }
        return true;
    }
	
    public static void printDetails(){
        System.out.println("Here are the following players for the current game: ");
        for(int x = 0; x < gamePlayers.size(); x++) {
            if (gamePlayers.get(x) != null) {
                gamePlayers.get(x).printDetails();
            }
        }
    }
}
