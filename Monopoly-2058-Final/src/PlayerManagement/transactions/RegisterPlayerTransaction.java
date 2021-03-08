package PlayerManagement.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import PlayerManagement.Player;

public class RegisterPlayerTransaction {
	
	public int playerAmount = 0;
	public static List<Player> gamePlayers = new ArrayList<Player>();
	public Scanner nS = new Scanner(System.in);
    
    public static int numTurns;
	
	public List<Player> RegisterPlayers() {
	
		System.out.print("Enter the amount of players entering your game: ");
        int amount = nS.nextInt();
        nS.nextLine();
        String pname;
        
        //check if name has already been entered by player
        for(int i = 0; i < amount; i++){
            System.out.println("Enter the name of player " + (i+1) + ": ");
            pname = nS.nextLine();
            while(!nameDuplicationCheck(pname))
            {
            	System.out.print("This player name has already been entered! \n\n");
            	System.out.println("Enter the name of player " + (i+1) + ": ");
            	pname = nS.nextLine();
            	if(nameDuplicationCheck(pname))
            	{
            		continue;
            	}
            	else
            	{
            		continue;
            	}
            }
            
            gamePlayers.add(new Player(pname, 1500));
        }

        if(!securityCheck()){
            System.out.println("Some of the players entered are either null or not acceptable.");
        }
        
        else{
            printDetails();
            
            //getting the amount of turns the game should last for
            System.out.println("Enter how many turns do you want the game to continue for: "+ "\n");
            numTurns = nS.nextInt();

            System.out.println("Enter any button to start the game");
            String in = nS.nextLine();
            System.out.println("\n"+"#########################################################################"+"\n");   //adding row of #s after each turn  [A.M]
            return gamePlayers;
        }
        return null;
	}
	
	public boolean nameDuplicationCheck(String name)  //check if player name has already been used
	{
		for(int i = 0; i <gamePlayers.size(); i++)
		{
			if(gamePlayers.get(i).getName().contains(name))
			{
				return false;
			}
			else
			{
				continue;
			}
		}
		
		return true;
	}
	
	public boolean securityCheck(){
        for(int i = 0; i < gamePlayers.size(); i++){
            if(gamePlayers.get(i) != null) {
                return true;    
            }
            else
            {
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


    public List<Player> getPlayerList() {

        return gamePlayers;
    }

}
