package PlayerManagement.transactions;

import DataManagement.SQL;

public class HighscoresTransaction {
	
	public static SQL sql = new SQL();
	
	public void LoadHighscores() {
		sql.Retrieve();
	}

}
