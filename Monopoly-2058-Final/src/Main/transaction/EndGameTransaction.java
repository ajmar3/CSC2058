package Main.transaction;

import java.util.List;

import DataManagement.SQL;
import PlayerManagement.Player;

public class EndGameTransaction {
	
	static SQL sql = new SQL();
	static MenuTransaction m = new MenuTransaction();
	
	public void endGame(List<Player> gamePlayers)
	{
		double temp = 0;
		Player winner = null;
		
		double tbal = 0;
		
		for(int i = 0; i < gamePlayers.size(); i++) {

			double tproperty = 0;
			
			tbal = gamePlayers.get(i).getBalance();
			
			for(int x = 0; x < gamePlayers.get(i).getOwnedTiles().size(); x++) {				
				tproperty += gamePlayers.get(i).getOwnedTiles().get(x).getCost();
			}
			
			System.out.println("Value of Player " + gamePlayers.get(i).getName() + " | " + (tbal + tproperty));
			
			if(i == 0) {
				temp = tbal + tproperty;
				winner = gamePlayers.get(i);
			}
			else {
				if(temp < (tbal + tproperty)) {
					temp = tbal + tproperty;
					winner = gamePlayers.get(i);
				}
			}
			
			
			
		
		}
		
		System.out.println("The winner of the game is " + winner.getName() + " with a total value of £" + temp);
		dataManage(winner, temp);
		m.loadMenu();
		
		
	}
	
	public void dataManage(Player w, double t) {
		sql.Storage();
		
		if(sql.Search(w.getName())) {
			sql.Update(w.getName(), t);
		}
		else { sql.Write(w.getName(), t);
		}
	}
}
