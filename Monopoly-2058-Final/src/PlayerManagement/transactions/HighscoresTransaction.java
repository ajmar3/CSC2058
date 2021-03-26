package PlayerManagement.transactions;

import DataManagement.SQL;
import Main.transaction.MenuTransaction;

public class HighscoresTransaction {
	
	public static SQL sql = new SQL();
	static MenuTransaction m = new MenuTransaction();
	
	public void LoadHighscores() {
		System.out.println("Loading ...");
		sql.Storage();	
		sql.Retrieve();
		m.loadMenu();
	}

}
