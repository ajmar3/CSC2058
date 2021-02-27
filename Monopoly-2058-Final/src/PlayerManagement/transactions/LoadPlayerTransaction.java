package PlayerManagement.transactions;

import java.util.List;

import PlayerManagement.Player;

public class LoadPlayerTransaction{
	
	private static List<Player> Players;
	
	public void LoadPlayers() {
		
		RegisterPlayerTransaction lP = new RegisterPlayerTransaction();
        Players = lP.RegisterPlayers();
			
	}
	
	
	
}
